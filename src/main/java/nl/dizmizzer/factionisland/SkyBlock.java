package nl.dizmizzer.factionisland;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import nl.dizmizzer.factionisland.Listeners.PlayerJoin;
import nl.dizmizzer.factionisland.Listeners.PlayerMove;
import nl.dizmizzer.factionisland.Listeners.PlayerQuit;
import nl.dizmizzer.factionisland.Listeners.PlayerRespawn;
import nl.dizmizzer.factionisland.commands.IslandCommand;
import nl.dizmizzer.factionisland.island.IslandManager;
import nl.dizmizzer.factionisland.tpa.TpaManager;
import nl.dizmizzer.factionisland.world.SkyBlockGen;
import org.bukkit.*;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class SkyBlock extends JavaPlugin {

    private static SkyBlock skyBlock;
    public World world;
    public WorldEditPlugin worldedit;
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

        new BukkitRunnable() {
            @Override
            public void run() {
                IslandManager.getIslandManager().saveFiles();
            }
        }.runTaskTimer(this, 30 * 20, 30 * 20);
        if (worldedit == null) {
            sendMessage("ERROR, You must have worldedit on your server!");

        } else {
            makeWorld();
            try {
                new IslandManager();
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
        world.setDifficulty(Difficulty.NORMAL);
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "(CrystalWars) " + ChatColor.AQUA + message);
    }
}