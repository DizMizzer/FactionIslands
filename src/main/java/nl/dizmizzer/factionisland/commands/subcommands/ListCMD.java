package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.entity.FactionColl;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
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
public class ListCMD implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(chatColor + "----Factions----");
        for (int i = 0; i < 10; i++) {
            if (FactionColl.get().getAllFactions().size() <= i) break;

            player.sendMessage(chatColor + FactionColl.get().getAllFactions().get(i).getTag());
        }
    }
}
