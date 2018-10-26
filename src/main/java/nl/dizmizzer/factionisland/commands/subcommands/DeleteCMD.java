package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.entity.FactionColl;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.utils.CheckUtil;
import org.bukkit.block.Block;
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
public class DeleteCMD implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (!CheckUtil.isInFaction(player)) {
            player.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return;
        }

        if (!CheckUtil.isOwner(player, true)) return;

        Faction f = FPlayerColl.get(player).getFaction();
        for (FPlayer fPlayer : FPlayerColl.get(player).getFaction().getMembers()) {
            fPlayer.setFaction(FactionColl.get("0"));
            fPlayer.setRole(Role.NORMAL);
        }
        f.setTag("");
        f.setId("0");
        f.clearAllClaimOwnership();
        f.remove();
        player.sendMessage(prefix + chatColor + "Your faction has been deleted!");

    }
}
