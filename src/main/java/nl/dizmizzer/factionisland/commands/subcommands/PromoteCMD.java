package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
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
public class PromoteCMD implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (!CheckUtil.canPromote(player, true)) return;

        if (args.length < 2) {
            player.sendMessage(prefix + errorColor + "Please specify a player.");
            return;
        }

        FPlayer fplayer = FPlayerColl.get(args[1]);
        if (fplayer == null) {
            player.sendMessage(prefix + errorColor + "Cannot find " + args[1] + "!");
            return;
        }

        if (! fplayer.getFactionId().equalsIgnoreCase(FPlayerColl.get(player).getFactionId())) {
            player.sendMessage("That player isn't in your faction!");
            return;
        }

        if (fplayer.getRole().compareTo(Role.NORMAL) > 0) {
            player.sendMessage("The player is already promoted!");
            return;
        }

        fplayer.setRole(Role.MODERATOR);
        if (fplayer.isOnline()) fplayer.sendMessage(prefix + chatColor + "You have been promoted to officer!");
        player.sendMessage("You have promoted %player% to officer!".replace("%player%",fplayer.getAccountId()));
    }
}
