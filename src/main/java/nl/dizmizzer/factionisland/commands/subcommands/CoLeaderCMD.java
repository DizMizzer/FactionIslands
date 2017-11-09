package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.FactionColl;
import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.interfaces.TextFormat;
import nl.dizmizzer.factionisland.utils.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
public class CoLeaderCMD implements SubCommand, TextFormat {

    @Override
    public void execute(Player player, String[] args) {
        if (!CheckUtil.isInFaction(player)) {
            player.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return;
        }
        if (!CheckUtil.isOwner(player, true)) return;
        if (args.length < 2) {
            player.sendMessage(prefix + errorColor + "You didn't specify a player!");
            return;
        }
        player.sendMessage(prefix + chatColor + "Trying to find " + args[1] + ".");
        new BukkitRunnable() {
            @Override
            public void run() {
                OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
                if (op == null) {
                    player.sendMessage(prefix + errorColor + "Can't find " + args[1]);
                    cancel();
                    return;
                }
                FPlayerColl.get(op).setRole(Role.COLEADER);
                if (!FactionColl.get(FPlayerColl.get(player)).getMembers().contains(FPlayerColl.get(op))) {
                    FactionColl.get(FPlayerColl.get(player)).addFPlayer(FPlayerColl.get(op));
                }
                FPlayerColl.get(op).setFaction(FPlayerColl.get(player).getFaction());
            }
        }.runTaskAsynchronously(SkyBlock.getSkyBlock());

    }
}
