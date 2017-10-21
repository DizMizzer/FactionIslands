package nl.dizmizzer.factionisland.Listeners;

import nl.dizmizzer.factionisland.island.IslandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        IslandManager.getIslandManager().unloadPlayer(e.getPlayer());
    }

}