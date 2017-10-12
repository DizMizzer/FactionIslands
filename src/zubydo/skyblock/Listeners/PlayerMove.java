package zubydo.skyblock.Listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import zubydo.skyblock.SkyBlock;
import zubydo.skyblock.interfaces.TextFormat;
import zubydo.skyblock.island.Island;
import zubydo.skyblock.island.IslandManager;


public class PlayerMove implements Listener, TextFormat {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals(SkyBlock.getSkyBlock().world.getName())) {
			Island i = IslandManager.getIslandManager().getIsland(p);
			if (i != null) {
				if (i.isAt(e.getFrom()) && !i.isAt(e.getTo())) {
					e.setCancelled(true);
					p.sendMessage(textColor + "You may not leave the bounds of your island.");
				}
			}
		}
	}

}