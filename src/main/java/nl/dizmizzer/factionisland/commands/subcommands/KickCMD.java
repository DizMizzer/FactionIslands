package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.FactionColl;
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
public class KickCMD implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        if (!CheckUtil.canInvite(player)) {
            player.sendMessage(prefix +errorColor + "You can't kick players from your faction!");
            return;
        }

        if (args.length < 2) {
            player.sendMessage(prefix + errorColor + "Please specify a player!");
            return;
        }
        FPlayer fplayer = FPlayerColl.get(args[1]);
        if (fplayer == null) {
            player.sendMessage(prefix + errorColor + "Cannot find the specified player!");
            return;
        }

        if (!fplayer.getFactionId().equalsIgnoreCase(FPlayerColl.get(player).getFactionId())) {
            player.sendMessage(prefix + errorColor + "The player isn't in your faction!");
            return;
        }

        if (FPlayerColl.get(player).getRole().compareTo(fplayer.getRole()) <= 0) {
            player.sendMessage(prefix +errorColor + "You can't kick the player because they are a higher rank than you!");
            return;
        }

        fplayer.setRole(Role.NORMAL);
        fplayer.setFaction(FactionColl.get(0));
        FPlayerColl.get(player).getFaction().removeFPlayer(fplayer);
    }
}
