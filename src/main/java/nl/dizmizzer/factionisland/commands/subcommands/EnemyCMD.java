package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Relation;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.Faction;
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
public class EnemyCMD implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (!CheckUtil.isOwner(player, true)) return;

        if (args.length < 2) {
            player.sendMessage(prefix + errorColor + "Please add a faction id or name.");
            return;
        }

        Faction f = FactionColl.get(args[1]);
        if (f == null) {
            player.sendMessage(prefix + errorColor + "Couldn't find " + args[1] + "!");
            return;
        }

        Faction owned = FPlayerColl.get(player).getFaction();
        owned.setRelationWish(f, Relation.ENEMY);
        player.sendMessage(prefix + chatColor + "You are now enemies with " + f.getTag() + "!");

    }
}
