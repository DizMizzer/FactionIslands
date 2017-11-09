package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.entity.FPlayerColl;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.island.IslandManager;
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
public class HomeCMD implements SubCommand {

    @Override
    public void execute(Player p, String[] args) {
        if (CheckUtil.isInFaction(p)) {
            p.sendMessage(prefix + textColor + "Teleporting; please allow up to 1 second.");
            p.teleport(IslandManager.getIslandManager().getSpawn(FPlayerColl.get(p).getFactionId()));
        } else {
            p.sendMessage(commandError + textColor + "create");
        }
    }
}
