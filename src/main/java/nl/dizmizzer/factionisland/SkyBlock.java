package nl.dizmizzer.factionisland;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import nl.dizmizzer.factionisland.Listeners.*;
import nl.dizmizzer.factionisland.commands.IslandCommand;
import nl.dizmizzer.factionisland.island.IslandManager;
import nl.dizmizzer.factionisland.task.ChunkLoadTask;
import nl.dizmizzer.factionisland.tpa.TpaManager;
import nl.dizmizzer.factionisland.utils.InviteManager;
import nl.dizmizzer.factionisland.world.SkyBlockGen;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class SkyBlock extends JavaPlugin {

    private static SkyBlock skyBlock;
    public World world;
    public WorldEditPlugin worldedit;
    public HashMap<UUID, Integer> inQueue = new HashMap<>();
    public HashMap<UUID, Integer[]> inQueueChunk = new HashMap<>();
    public HashMap<UUID, String> inviting = new HashMap<>();
    String worldName = "world_skyblock";
    private boolean legacyFactionsEnabled;

    public static SkyBlock getSkyBlock() {
        return skyBlock;
    }

    @Override
    public void onEnable() {

        skyBlock = this;
        this.worldedit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (Bukkit.getPluginManager().isPluginEnabled("LegacyFactions")) {
            this.legacyFactionsEnabled = true;
        }
        new ChunkLoadTask().runTaskTimer(this, 0, 1);
        InviteManager.get().setup();
        new BukkitRunnable() {
            @Override
            public void run() {
                IslandManager.getIslandManager().saveFiles();
                InviteManager.get().saveConfig();
            }
        }.runTaskTimerAsynchronously(this, 60 * 20, 60 * 20);
        if (worldedit == null) {
            sendMessage("ERROR, You must have worldedit on your server!");

        } else {
            makeWorld();
        }
            try {
                IslandManager.getIslandManager().onEnable();
            } catch (IOException e) {
                e.printStackTrace();

            } catch (DataException e) {
                e.printStackTrace();
            }

            new TpaManager();
            registerPermissions();
            registerCommands();
            registerListeners();
        }


    private void registerPermissions() {
        PluginManager pm = Bukkit.getPluginManager();
        Permission p = new Permission("factionisland.admin");
        p.setDefault(PermissionDefault.OP);
        pm.addPermission(p);
    }

    @Override
    public void onDisable() {
        IslandManager.getIslandManager().onDisable();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.performCommand("/server fallback");
        }
    }

    private void registerCommands() {
        getCommand("factionisland").setExecutor(new IslandCommand());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerRespawn(), this);
        pm.registerEvents(new PlayerCommand(), this);
    }

    private void makeWorld() {
        if (Bukkit.getWorld(worldName) == null) {
            sendMessage("Generating world: '" + worldName + "'");
            WorldCreator wc = new WorldCreator(worldName);
            wc.generateStructures(false);
            wc.generator(new SkyBlockGen());
            Bukkit.getServer().createWorld(wc);
        }

        sendMessage("Loaded world: '" + worldName + "'");
        world = Bukkit.getWorld(worldName);
        world.setDifficulty(Difficulty.PEACEFUL);
        for (int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                Bukkit.getWorld(worldName).loadChunk(x * 16, z * 16);
            }
        }

    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "(CrystalWars) " + ChatColor.AQUA + message);
    }
}