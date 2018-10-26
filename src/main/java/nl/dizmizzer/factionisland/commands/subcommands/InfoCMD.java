package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.utils.CheckUtil;
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
public class InfoCMD implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        FPlayer fplayer = FPlayerColl.get(player);
        player.sendMessage(chatColor + "----------------------");
        if (!CheckUtil.isInFaction(player)) {
            player.sendMessage("Name: Factionless" );
            player.sendMessage(chatColor + "----------------------");
            return;
        }

        player.sendMessage(chatColor + "Name: " + fplayer.getFaction().getTag());
        player.sendMessage(chatColor + "Id: " + fplayer.getFaction().getId());
        player.sendMessage(chatColor + "Description: " + fplayer.getFaction().getDescription());
        player.sendMessage(chatColor + "Members: " + fplayer.getFaction().getMembers().size());
        player.sendMessage(chatColor + "Owner: " + fplayer.getFaction().getOwner().getName());
        player.sendMessage(chatColor + "----------------------");
    }
}
