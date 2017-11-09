package nl.dizmizzer.factionisland.commands.subcommands;

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

public class DescCMD implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {

        //Check Faction
        if (!CheckUtil.isInFaction(player)) {
            player.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return;
        }
        if (!CheckUtil.isOwner(player, true)) return;
        if (args.length < 2) {
            player.sendMessage(prefix + errorColor + "Please do /fi desc <description>");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i] + " ");
        }
        FPlayerColl.get(player).getFaction().setDescription(sb.toString());
        player.sendMessage(prefix + chatColor + "Succesfully changed description to: " + sb.toString());
    }
}
