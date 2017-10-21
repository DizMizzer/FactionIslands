package nl.dizmizzer.factionisland.Listeners;

import nl.dizmizzer.factionisland.island.IslandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        IslandManager.getIslandManager().loadPlayer(e.getPlayer());
    }

}