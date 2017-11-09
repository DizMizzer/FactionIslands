package nl.dizmizzer.factionisland.Listeners;

import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.entity.FactionColl;
import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.interfaces.TextFormat;
import nl.dizmizzer.factionisland.island.IslandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener, TextFormat {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        IslandManager.getIslandManager().loadPlayer(e.getPlayer());
        if (SkyBlock.getSkyBlock().inviting.containsKey(e.getPlayer().getUniqueId())) {
            Faction f = FactionColl.get(SkyBlock.getSkyBlock().inviting.get(e.getPlayer().getUniqueId()));
            if (f == null) {
                return;
            }
            e.getPlayer().sendMessage(prefix + chatColor + "You are invited by " + f.getTag());
        }
    }

}