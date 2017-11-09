package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.entity.FactionColl;
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
public class AcceptCMD implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        if (!InviteManager.get().getConfig().contains(player.getUniqueId().toString())) {
            player.sendMessage(prefix + chatColor + "You weren't invited by a faction!");
            return;
        }

        Faction invitingFac = FactionColl.get(InviteManager.get().getConfig().get(player.getUniqueId().toString()));
        int max = invitingFac.getPermanentPower();
        if (invitingFac.getMembers().size() >= max) {
            player.sendMessage(prefix + errorColor + "The faction who invited you is full!");
            InviteManager.get().removeInviting(player.getUniqueId().toString());
            return;
        }

        invitingFac.addFPlayer(FPlayerColl.get(player));
        FPlayerColl.get(player).setFaction(invitingFac);
        FPlayerColl.get(player).setRole(Role.NORMAL);
        InviteManager.get().removeInviting(player.getUniqueId().toString());

        player.sendMessage(prefix + chatColor + "You have joined " + invitingFac.getTag() + "!");

        for (FPlayer fplayer : invitingFac.getFPlayers()) {
            if (fplayer.isOnline())
                fplayer.sendMessage(prefix + chatColor + player.getName() + " has joined the faction!");
        }
    }
}
