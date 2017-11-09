package nl.dizmizzer.factionisland.island;


import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.schematic.SchematicFormat;
import net.redstoneore.legacyfactions.FLocation;
import net.redstoneore.legacyfactions.entity.Board;
import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.Faction;
import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.utils.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IslandManager {

    public static File islandFile;
    public static FileConfiguration islandConfig;
    public static FileConfiguration config;
    static CuboidClipboard island;
    static Integer[] totalIslands = {0, 0};
    static WorldEditPlugin we;
    private static IslandManager islandManager = new IslandManager();
    public Block block1;
    public Block block2;
    File file;
    SchematicFormat mcedit;

    public IslandManager() {
    }

    public static void createIsland(Player p, Faction f, Integer[] totalIslands) {
        int x = totalIslands[0] * 256;
        int z = totalIslands[1] * 256;
        Location loc = new Location(SkyBlock.getSkyBlock().world, x, 26, z);

        for (int xx = 0; xx < 256; xx = xx + 16) {
            for (int zz = 0; zz < 256; zz = zz + 16) {
                if (xx / 16 >= 4 && xx / 16 <= 7 && zz / 16 >= 4 && zz / 16 <= 7) {
                    FLocation fLocation = FLocation.valueOf(new Location(SkyBlock.getSkyBlock().world, x + xx + 1, 55, z + zz + 1));
                    Board.get().setFactionAt(f, fLocation);
                }
            }
        }
        int islandsizeZ = island.getLength() / 2;
        int islandsizeX = island.getWidth() / 2;

        Location playerLoc = loc.clone().add(128 - islandsizeX - 9, 0, 128 - islandsizeZ - 8);
        playerLoc.setYaw(-180);

        Island il = new Island(p, playerLoc, false);
        try {

            island.paste(we.getSession(p).createEditSession(we.wrapPlayer(p)), BukkitUtil.toVector(playerLoc), true);
        } catch (MaxChangedBlocksException e) {
            e.printStackTrace();
        }
        p.teleport(playerLoc);

        ConfigurationSection s = islandConfig.createSection(f.getId());
        s.set("name", p.getName());
        s.set("x", il.getLocation().getX());
        s.set("z", il.getLocation().getZ());
        s.set("spawn.x", il.getLocation().getX());
        s.set("spawn.y", il.getLocation().getY());
        s.set("spawn.z", il.getLocation().getZ());
    }

    public static void deleteIsland(Player p) {
        p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        islandConfig.set(p.getUniqueId().toString(), null);
    }

    public static IslandManager getIslandManager() {
        return islandManager;
    }

    static int abs(int a) {
        return (a > 0) ? a : -a;
    }

    public static Integer[] getNext(int x, int y) {

        //Corners
        if (x == y) {
            //Rechtsboven
            if (x > 0)
                return new Integer[]{x - 1, y};
            //linksonder
            if (x < 0)
                return new Integer[]{x + 1, y};
            //Midden
            if (x == 0)
                return new Integer[]{1, 0};
        }

        if (x == -y) {
            if (x < 0)
                return new Integer[]{x, y - 1};
            if (x > 0)
                return new Integer[]{x + 1, y};
        }

        //Rechtdoor
        if (x - y > 0) {
            if (abs(x) > abs(y)) {
                return new Integer[]{x, y + 1};
            } else {
                return new Integer[]{x + 1, y};
            }
        }
        if (x - y < 0) {
            if (abs(x) > abs(y)) {
                return new Integer[]{x, y - 1};
            } else {
                return new Integer[]{x - 1, y};
            }
        }
        return new Integer[]{0, 0};
    }

    private static void saveIslands() {
        try {
            islandConfig.save(islandFile);
        } catch (IOException e) {

        }
    }

    public void onEnable() throws IOException, DataException {
        registerFile();
        registerIslandFile();
        we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        mcedit = MCEditSchematicFormat.getFormat(new File(SkyBlock.getSkyBlock().getDataFolder(), "Start.schematic"));
        island = mcedit.load(new File(SkyBlock.getSkyBlock().getDataFolder(), "Start.schematic"));
        for (Player ps : Bukkit.getOnlinePlayers()) {
            loadPlayer(ps);
        }
        loadStats();

    }

    public Integer[] getLastIsland() {
        return totalIslands;
    }

    public void goNext() {
        totalIslands = getNext(totalIslands[0], totalIslands[1]);
    }

    private void registerFile() {
        if (!SkyBlock.getSkyBlock().getDataFolder().exists()) {
            SkyBlock.getSkyBlock().getDataFolder().mkdir();
        }

        file = new File(SkyBlock.getSkyBlock().getDataFolder(), "playerdata.yml");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    public Location getSpawn(String FactionID) {

        return new Location(Bukkit.getWorld(SkyBlock.getSkyBlock().world.getName()),
                islandConfig.getDouble(FactionID + ".spawn.x"),
                islandConfig.getDouble(FactionID + ".spawn.y"),
                islandConfig.getDouble(FactionID + ".spawn.z"));
    }

    private void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer[] fromString(String... a) {
        if (a == null) {
            return null;
        }
        ArrayList<Integer> c = new ArrayList<>();
        for (String b : a) {
            c.add(Integer.valueOf(b));
        }
        return c.toArray(new Integer[c.size()]);
    }

    private void loadStats() {
        totalIslands = fromString(islandConfig.getString("last-island").split("_"));
    }

    public void onDisable() {
        islandConfig.set("last-island", totalIslands[0] + "_" + totalIslands[1]);
        saveFiles();
    }

    public void addPlayer(Player player) {
        FPlayer fPlayer = FPlayerColl.get(player);
        String factionID = fPlayer.getFaction().getId();
        config.set(fPlayer.getId() + ".factionID", factionID);
        config.set(fPlayer.getId() + ".role", fPlayer.getRole().toString());
    }

    public void setSpawn(String FactionID, Location loc) {
        islandConfig.set(FactionID + ".spawn.x", loc.getX());
        islandConfig.set(FactionID + ".spawn.y", loc.getY());
        islandConfig.set(FactionID + ".spawn.z", loc.getZ());
    }

    private void registerIslandFile() {
        if (!SkyBlock.getSkyBlock().getDataFolder().exists()) {
            SkyBlock.getSkyBlock().getDataFolder().mkdir();
        }
        islandFile = new File(SkyBlock.getSkyBlock().getDataFolder(), "islands.yml");
        try {
            islandFile.createNewFile();
            islandConfig = YamlConfiguration.loadConfiguration(islandFile);
            islandConfig.set("last-island", "0_0");
            saveIslands();
        } catch (IOException e) {
            e.printStackTrace();
        }
        islandConfig = YamlConfiguration.loadConfiguration(islandFile);
        Bukkit.getLogger().severe("Island Config succesfully loaded!");
    }

    public void loadPlayer(Player p) {
        FPlayer fplayer = FPlayerColl.get(p);
        Faction faction = fplayer.getFaction();
        if (faction == null) return;
        FPlayer owner = faction.getOwner();
        if (owner == null) return;
        if (islandConfig == null) return;
        if (owner.getId() == null) return;
        if (islandConfig.contains(owner.getId())) {
            ConfigurationSection s = islandConfig.getConfigurationSection(p.getUniqueId().toString());
            double x = s.getDouble("x");
            double z = s.getDouble("z");
            Island i = new Island(p, new Location(SkyBlock.getSkyBlock().world, x, 72, z), false);

        }
    }

    public Island getIsland(Player p) {
        if (hasIsland(p)) {
            return new Island(p, new Location(SkyBlock.getSkyBlock().world,
                    islandConfig.getDouble(FPlayerColl.get(p).getFaction().getOwner().getId() + ".spawn.x"),
                    islandConfig.getDouble(FPlayerColl.get(p).getFaction().getOwner().getId() + ".spawn.y"),
                    islandConfig.getDouble(FPlayerColl.get(p).getFaction().getOwner().getId() + ".spawn.z")
            ), false
            );
        }
        return null;
    }

    public void sendHome(Player p) {
        p.teleport(getIsland(p).getLocation());
    }

    public boolean hasIsland(Player p) {
        if (!CheckUtil.isInFaction(p)) return false;
        if (FPlayerColl.get(p).getFaction().getOwner() == null) return false;
        return islandConfig.contains(FPlayerColl.get(p).getFaction().getOwner().getId());
    }

    public boolean hasIsland(String uuid) {
        if (uuid == null) return false;
        return islandConfig.contains(uuid);
    }

    public Integer[] getTotalIslands() {
        return totalIslands;
    }

    public int getTotalIslandsInUse() {
        return islandConfig.getKeys(false).size();
    }

    public void saveFiles() {
        saveFile();
        saveIslands();
    }
}