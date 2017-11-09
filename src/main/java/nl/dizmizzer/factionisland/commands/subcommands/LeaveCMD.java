package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
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
public class LeaveCMD implements SubCommand {
    @Override
    public void execute(Player p, String[] args) {
        if (!CheckUtil.isInFaction(p)) {
            p.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return;
        }

        if (FPlayerColl.get(p).getFaction().getMembers().size() < 2) {
            p.sendMessage(prefix + errorColor + "You are the last member in the faction, please do /fi delete");
            return;
        }
        FPlayerColl.get(p).setFaction(FactionColl.get("0"));
        FPlayerColl.get(p).setRole(Role.NORMAL);
        p.sendMessage(prefix + chatColor + "You have left the faction!");
    }
}
