package nl.dizmizzer.factionisland.commands;

import net.redstoneore.legacyfactions.FLocation;
import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.entity.FPlayerColl;
import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.entity.FactionColl;
import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.interfaces.TextFormat;
import nl.dizmizzer.factionisland.island.Island;
import nl.dizmizzer.factionisland.island.IslandManager;
import nl.dizmizzer.factionisland.tpa.TpaManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IslandCommand implements CommandExecutor, TextFormat {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(helpMessage);
                return true;
            }
            if (args.length > 0) {
                IslandManager im = IslandManager.getIslandManager();
                if (args[0].equalsIgnoreCase("debug")) {
                    p.sendMessage(new String[]{FPlayerColl.get(p).getFaction().getId(), FPlayerColl.get(p).getRole().toString()});
                    return true;
                }
                if (args[0].equalsIgnoreCase("create")) {
                    if (isOwner(p, false)) {
                        p.sendMessage(commandError + "delete");
                        return true;
                    }
                    if (isInFaction(p)) {
                        p.sendMessage(prefix + errorColor + "You are already in a faction!");
                        return true;
                    }
                    if (args.length < 2) {
                        p.sendMessage(prefix + errorColor + "Please define a faction name!");
                        return true;
                    }

                    if (!im.hasIsland(getFactionOwnerId(p))) {
                        if (FactionColl.get().getByTag(args[1]) != null) {
                            p.sendMessage(prefix + errorColor + "Faction already exists!");
                            return true;
                        }

                        p.sendMessage(prefix + textColor + "Creating island; please allow up to 1 minute.");
                        Faction f = FactionColl.get().createFaction();
                        FPlayerColl.get(p).setFaction(f);
                        p.sendMessage(f.getFPlayers().toString());
                        f.setPlayerAsOwner(FPlayerColl.get(p), FLocation.valueOf(p.getLocation()));
                        FPlayerColl.get(p).setRole(Role.ADMIN);
                        f.setTag(args[1]);
                        IslandManager.createIsland(p, FPlayerColl.get(p).getFaction());
                        FPlayerColl.save();
                        FactionColl.get().forceSave();
                        IslandManager.getIslandManager().addPlayer(p);
                    } else {
                        p.sendMessage(commandError + "delete");
                    }
                } else if (args[0].equalsIgnoreCase("delete")) {
                    if (!isOwner(p, true)) return true;

                    if (im.hasIsland(p)) {
                        p.sendMessage(prefix + textColor + "Deleting island; please allow up to 1 second.");
                        IslandManager.deleteIsland(p);
                    } else {
                        p.sendMessage(commandError + textColor + "create");
                    }
                } else if (args[0].equalsIgnoreCase("invite")) {
                    if (!canInvite(p)) {
                        p.sendMessage(prefix + errorColor + "You aren't allowed to invite players!");
                        return true;
                    }
                    if (args.length < 2) {
                        p.sendMessage(prefix + errorColor + "Please define a player!");
                        return true;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null) {
                        p.sendMessage(prefix + errorColor + "Can't find " + args[1]);
                        return true;
                    }

                } else if (args[0].equalsIgnoreCase("home")) {
                    if (im.hasIsland(p)) {
                        p.sendMessage(prefix + textColor + "Teleporting; please allow up to 1 second.");
                        p.teleport(im.getSpawn(FPlayerColl.get(p).getFactionId()));
                    } else {
                        p.sendMessage(commandError + textColor + "create");
                    }
                } else if (args[0].equalsIgnoreCase("sethome")) {
                    if (FPlayerColl.get(p).getRole().equals(Role.ADMIN)) {

                    }
                } else if (args[0].equalsIgnoreCase("tpa")) {
                    if (args.length < 2) {
                        p.sendMessage(commandError + "tpa " + boldColor + "<" + textColor + "player, accept, deny"
                                + boldColor + ">");
                        return false;
                    }
                    if (args[1].equalsIgnoreCase("accept")) {
                        TpaManager.getTpaManager().acceptRequest(p);
                        return false;
                    }
                    if (args[1].equalsIgnoreCase("deny")) {
                        TpaManager.getTpaManager().denyRequest(p);
                        return false;
                    }

                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage(playerErrorMessage + args[1]);
                    } else {
                        TpaManager.getTpaManager().request(p, target);
                    }
                } else if (p.hasPermission("factionisland.admin")) {
                    if (args[0].equalsIgnoreCase("info")) {
                        SkyBlock sb = SkyBlock.getSkyBlock();
                        p.sendMessage(prefix + boldColor + ")▔▔▔▔▔▔▔▔▔(" + infoColor + "CrystalWars " + boldColor
                                + ")▔▔▔▔▔▔▔▔(");
                        p.sendMessage(prefix + textColor + "Version" + whiteColor + ": " + numberColor + ""
                                + sb.getDescription().getVersion());
                        p.sendMessage(prefix + textColor + "CrystalWars World" + whiteColor + ": " + infoColor + ""
                                + sb.world.getName());
                        p.sendMessage(prefix + textColor + "Total Islands" + whiteColor + ": " + numberColor + ""
                                + im.getTotalIslands());
                        p.sendMessage(prefix + textColor + "Current Islands In Use" + whiteColor + ": " + numberColor
                                + "" + im.getTotalIslandsInUse());
                        p.sendMessage(prefix + boldColor + ")▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔(");
                    }

                    if (args[0].equalsIgnoreCase("tp")) {
                        if (!(args.length >= 2)) {
                            p.sendMessage(commandError + textColor + "tp " + boldColor + "<" + textColor + "player"
                                    + boldColor + ">");
                            return false;
                        }
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null) {
                            p.sendMessage(playerErrorMessage + boldColor + args[1]);
                            return false;
                        }
                        if (im.hasIsland(getFactionOwnerId(target))) {
                            Island i = im.getIsland(target);
                            p.sendMessage(prefix + textColor + "Teleporting...");
                            p.teleport(i.getLocation());
                        } else {
                            p.sendMessage(prefix + errorColor + "That isn't in a faction with an island!");
                        }
                    }
                } else {
                    p.sendMessage(helpMessage);
                }
            } else {
                if (p.hasPermission("factionisland.admin")) {
                    p.sendMessage(opHelpMessage);
                } else {
                    p.sendMessage(helpMessage);
                }
            }
        }
        return false;
    }

    private boolean canInvite(Player p) {
        return (FPlayerColl.get(p).getRole() != Role.NORMAL);
    }

    private boolean isInFaction(Player p) {
        if (!FPlayerColl.get(p).getFaction().getId().equalsIgnoreCase("0")) {
            return true;
        }
        return false;
    }


    private boolean isOwner(Player player, boolean message) {
        if (FPlayerColl.get(player).getFaction() == null || FPlayerColl.get(player).getFaction().getOwner() == null) {
            if (message) player.sendMessage(prefix + errorColor + "You aren't in a faction!");
            return false;
        }
        if (!FPlayerColl.get(player).getFaction().getOwner().getPlayer().getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())) {
            if (message) player.sendMessage(prefix + errorColor + "You aren't the owner of the faction!");
            return false;
        }

        return true;
    }

    private String getFactionOwnerId(Player player) {
        if (FPlayerColl.get(player).getFaction() == null) return null;
        if (FPlayerColl.get(player).getFaction().getOwner() == null) return null;

        return FPlayerColl.get(player).getFaction().getOwner().getId();
    }
}