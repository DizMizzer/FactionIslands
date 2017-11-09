package nl.dizmizzer.factionisland.commands.subcommands;

import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.utils.InviteManager;
import org.bukkit.entity.Player;

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
public class DenyCMD implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (!InviteManager.get().getConfig().contains(player.getUniqueId().toString())) {
            player.sendMessage(prefix + chatColor + "You weren't invited by a faction!");
            return;
        }

        player.sendMessage(prefix + chatColor + "You have denied the invites!");

        InviteManager.get().removeInviting(player.getUniqueId().toString());
    }
}
