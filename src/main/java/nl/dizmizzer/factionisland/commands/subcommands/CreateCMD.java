package nl.dizmizzer.factionisland.commands.subcommands;

import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.entity.FactionColl;
import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.island.IslandManager;
import nl.dizmizzer.factionisland.utils.CheckUtil;
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
public class CreateCMD implements SubCommand {

    @Override
    public void execute(Player p, String[] args) {
        if (CheckUtil.isOwner(p, false)) {
            p.sendMessage(commandError + "delete");
            return;
        }
        if (CheckUtil.isInFaction(p)) {
            p.sendMessage(prefix + errorColor + "You are already in a faction!");
            return;
        }
        if (args.length < 2) {
            p.sendMessage(prefix + errorColor + "Please define a faction name!");
            return;
        }

        if (!IslandManager.getIslandManager().hasIsland(CheckUtil.getFactionOwnerId(p))) {
            if (FactionColl.get().getByTag(args[1]) != null) {
                p.sendMessage(prefix + errorColor + "Faction already exists!");
                return;
            }

            p.sendMessage(prefix + textColor + "Creating island. It will be ready in " + (SkyBlock.getSkyBlock().inQueue.size() * 3.6) + " seconds!");
            SkyBlock.getSkyBlock().inQueue.put(p.getUniqueId(), 0);
            SkyBlock.getSkyBlock().inQueueChunk.put(p.getUniqueId(), IslandManager.getIslandManager().getTotalIslands());
            Integer[] a = IslandManager.getIslandManager().getLastIsland();
            IslandManager.getIslandManager().goNext();

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (SkyBlock.getSkyBlock().inQueue.containsKey(p.getUniqueId())) return;
                    Faction f = FactionColl.get().createFaction();
                    FPlayerColl.get(p).setFaction(f);

                    FPlayerColl.get(p).setRole(Role.ADMIN);
                    f.setTag(args[1]);

                    IslandManager.createIsland(p, FPlayerColl.get(p).getFaction(), a);
                    FPlayerColl.save();
                    FactionColl.get().forceSave();
                    IslandManager.getIslandManager().addPlayer(p);
                    f.getClaimOwnership().forEach((location, playerList) ->
                            playerList.forEach(playerId ->
                                    f.setPlayerAsOwner(FPlayerColl.get(playerId), location)
                            )
                    );

                    cancel();
                }
            }.runTaskTimer(SkyBlock.getSkyBlock(), 1, 1);

        } else {
            p.sendMessage(commandError + "delete");
        }

    }
}
