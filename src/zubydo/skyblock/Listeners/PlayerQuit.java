package zubydo.skyblock.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import zubydo.skyblock.island.IslandManager;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		IslandManager.getIslandManager().unloadPlayer(e.getPlayer());
	}

}