package nl.dizmizzer.factionisland.Listeners;


import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.interfaces.TextFormat;
import nl.dizmizzer.factionisland.island.Island;
import nl.dizmizzer.factionisland.island.IslandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


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