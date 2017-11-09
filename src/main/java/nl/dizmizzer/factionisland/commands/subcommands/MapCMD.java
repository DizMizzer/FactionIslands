package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.entity.Board;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
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
public class MapCMD implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(Board.get().getMap(FPlayerColl.get(player).getFaction(),
                FPlayerColl.get(player).getLastLocation(),
                FPlayerColl.get(player).getLastLocation().getYaw())
                .toArray(new String[
                        Board.get().getMap(
                                FPlayerColl.get(player).getFaction(),
                                FPlayerColl.get(player).getLastLocation(),
                                FPlayerColl.get(player).getLastLocation().getYaw()).size()]
                )
        );
    }
}
