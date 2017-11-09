package nl.dizmizzer.factionisland.commands;

import nl.dizmizzer.factionisland.SkyBlock;
import nl.dizmizzer.factionisland.commands.subcommands.*;
import nl.dizmizzer.factionisland.interfaces.SubCommand;
import nl.dizmizzer.factionisland.interfaces.TextFormat;
import nl.dizmizzer.factionisland.island.IslandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class IslandCommand implements CommandExecutor, TextFormat {
    HashMap<String, SubCommand> classes = new HashMap<>();

    public IslandCommand() {

        //Working classes!
        classes.put("create", new CreateCMD());
        classes.put("delete", new DeleteCMD());
        classes.put("leave", new LeaveCMD());
        classes.put("home", new HomeCMD());
        classes.put("sethome", new SethomeCMD());
        classes.put("coleader", new CoLeaderCMD());
        classes.put("desc", new DescCMD());
        classes.put("description", new DescCMD());
        classes.put("map", new MapCMD());
        classes.put("ally", new AllyCMD());
        classes.put("enemy", new EnemyCMD());

        //TODO Test The following classes!
        classes.put("invite", new InviteCMD());
        classes.put("accept", new AcceptCMD());
        classes.put("deny", new DenyCMD());

        //TODO These classes below
        classes.put("promote", new PromoteCMD());
        classes.put("info", new InfoCMD());
        classes.put("demote", new DemoteCMD());
        classes.put("tpa", new TpaCMD()); //TODO Request, Accept, deny
        classes.put("list", new ListCMD());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (SkyBlock.getSkyBlock().inQueue.containsKey(p.getUniqueId())) {
                p.sendMessage(prefix + errorColor + "Your island is still being loaded. Please wait!");
                return true;
            }
            if (args.length == 0) {
                p.sendMessage(helpMessage);
                return true;
            }
            IslandManager im = IslandManager.getIslandManager();
            if (args.length > 0) {
                if (classes.containsKey(args[0])) {
                    classes.get(args[0]).execute(p, args);
                    return true;
                } else {
                    p.sendMessage(helpMessage);
                }

            }
            p.sendMessage(helpMessage);
            return true;
        }

        return true;
    }

}