package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.utils.CheckUtil;
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
public class InviteCMD implements SubCommand {

    @Override
    public void execute(Player p, String[] args) {
        if (!CheckUtil.canInvite(p)) {
            p.sendMessage(prefix + errorColor + "You aren't allowed to invite players!");
            return;
        }
        if (args.length < 2) {
            p.sendMessage(prefix + errorColor + "Please define a player!");
            return;
        }
        FPlayer player = FPlayerColl.get(args[1]);
        if (player == null) {
            p.sendMessage(prefix + errorColor + "Can't find " + args[1]);
            return;
        }
        int maxInvite = 3;

        for (int i = 3; i < 9; i++) {
            if (p.hasPermission("factionisland.invite." + i)) maxInvite = i;
        }

        FPlayerColl.get(p).getFaction().setPermanentPower(maxInvite);

        if (FPlayerColl.get(p).getFaction().getFPlayers().size() >= maxInvite) {
            p.sendMessage(prefix + errorColor + "Your faction is already at the limit of players to invite!");
            return;
        }

        if (CheckUtil.isInFaction(player)) {
            p.sendMessage(prefix + errorColor + args[1] + " is already in a faction!");
            return;
        }

        if (InviteManager.get().getConfig().contains(player.getId())) {
            p.sendMessage(prefix + errorColor + args[1] + " has already been invited!");
            return;
        }

        InviteManager.get().setInviting(FPlayerColl.get(args[1]).getId(), FPlayerColl.get(p).getFactionId());
        p.sendMessage(prefix + chatColor + "You have invited " + args[1] + "!");

    }
}
