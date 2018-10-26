package nl.dizmizzer.factionisland.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

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
public class PlayerCommand implements Listener {

    @EventHandler
    public void onChat(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().split(" ")[0].equalsIgnoreCase("/f") || e.getMessage().split(" ")[0].equalsIgnoreCase("/factions") || e.getMessage().split(" ")[0].equalsIgnoreCase("/legacyfactions")) {
            e.getPlayer().sendMessage("Unknown command. Type \"/help\" for help.");
            e.setCancelled(true);
        }
    }
}
