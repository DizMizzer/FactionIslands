package nl.dizmizzer.factionisland.island;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class Island {

    public Location loc;
    int r = 420;
    private String ownerName;
    private Location spawn;

    public Island(Player owner, Location loc, boolean gen) {
        ownerName = owner.getName();
        this.loc = loc;
        spawn = loc.clone().add(1.5, 3, 5.5);
        spawn.setYaw(-180);
        if (gen) {
        }

    }


    public boolean isAt(Location location) {
        if (location == null) {
            return false;
        }
        int x = (int) Math.abs(location.getBlockX() - loc.getBlockX());
        int z = (int) Math.abs(location.getBlockZ() - loc.getBlockZ());
        return x < r && z < r;
    }

    public void setSpawnLocation(double x, double y, double z) {
        spawn = new Location(Bukkit.getWorld("world_skyblock"), x, y, z);
    }

    public Player getPlayer() {
        return Bukkit.getPlayerExact(ownerName);
    }

    public Location getLocation() {
        return loc;
    }

    public Location getSpawnLocation() {
        return spawn;
    }

}