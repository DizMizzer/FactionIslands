package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.FLocation;
import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.Board;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.island.IslandManager;
import nl.dizmizzer.factionisland.utils.CheckUtil;
import org.bukkit.Location;
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
public class SethomeCMD implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        if (!CheckUtil.isInFaction(player)) {
            player.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return;
        }
        if (FPlayerColl.get(player).getRole() == Role.ADMIN || FPlayerColl.get(player).getRole() == Role.COLEADER) {
            FLocation floc = FLocation.valueOf(player.getLocation());
            Location loc = null;
            if (Board.get().getFactionAt(
                    FPlayerColl.get(player)
                            .getLastLocation())
                    .getId().equalsIgnoreCase(
                            FPlayerColl.get(player)
                                    .getFactionId())) {
                loc = player.getLocation();
                IslandManager.getIslandManager().setSpawn(FPlayerColl.get(player).getFactionId(), loc);
                player.sendMessage(prefix + chatColor + "Succesfully changed home location!");

            } else {
                player.sendMessage(prefix + errorColor + "You aren't inside your faction land!");
            }
        } else {
            player.sendMessage(prefix + errorColor + "You aren't the owner of the faction!");
        }
    }
}
