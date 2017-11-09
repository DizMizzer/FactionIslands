package nl.dizmizzer.factionisland.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

/**
 * Created by DizMizzer.
 * Users don't have permission to release
 * the code unless stated by the Developer.
 * You are allowed to copy the source code
 * and edit it in any way, but not distribute
 * it. If you want to distribute addons,
 * please use the API. If you can't access
 * a certain thing in the API, please contact
 * the developer in contact.txt.
 */
public class PlayerTab implements Listener {

    @EventHandler
    public void onTab(PlayerChatTabCompleteEvent e) {
        if (e.getChatMessage().split(" ")[0].equalsIgnoreCase("/fi") || e.getChatMessage().split(" ")[0].equalsIgnoreCase("/factionsisland")) {
            String pre = (e.getChatMessage().split(" ").length > 1) ? e.getChatMessage().split(" ")[1] : null;
            if (pre == null) return;

            e.getTabCompletions().clear();

        }
    }
}
