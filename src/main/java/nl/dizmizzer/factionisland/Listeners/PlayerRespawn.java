package nl.dizmizzer.factionisland.Listeners;

import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.island.Island;
import nl.dizmizzer.factionisland.island.IslandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals(SkyBlock.getSkyBlock().world.getName())) {
            if (IslandManager.getIslandManager().hasIsland(p)) {
                Island i = IslandManager.getIslandManager().getIsland(p);
                e.setRespawnLocation(i.getSpawnLocation());
            }
        }
    }

}