package zubydo.skyblock.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import zubydo.skyblock.SkyBlock;
import zubydo.skyblock.island.Island;
import zubydo.skyblock.island.IslandManager;

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