package nl.dizmizzer.factionisland.utils;

import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayer;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import nl.dizmizzer.factionisland.interfaces.TextFormat;
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
public class CheckUtil implements TextFormat {

    public static boolean isOwner(Player player, boolean message) {
        if (FPlayerColl.get(player).getFaction() == null || FPlayerColl.get(player).getFaction().getId().equalsIgnoreCase("0")) {
            if (message) player.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return false;
        }
        if (FPlayerColl.get(player).getRole() != Role.ADMIN) {
            if (message) player.sendMessage(prefix + errorColor + "You aren't the owner of the faction!");
            return false;
        }

        return true;
    }

    public static boolean isInFaction(Player p) {
        return !FPlayerColl.get(p).getFaction().getId().equalsIgnoreCase("0");
    }

    public static String getFactionOwnerId(Player player) {
        if (FPlayerColl.get(player).getFaction() == null) return null;
        if (FPlayerColl.get(player).getFaction().getOwner() == null) return null;

        return FPlayerColl.get(player).getFaction().getOwner().getId();
    }

    public static boolean canInvite(Player p) {
        return (FPlayerColl.get(p).getRole() != Role.NORMAL);
    }


    public static boolean isInFaction(FPlayer player) {
        return !player.getFaction().getId().equalsIgnoreCase("0");
    }
}
