package nl.dizmizzer.factionisland.commands.subcommands;

import nl.dizmizzer.factionisland.interfaces.SubCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

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
public class TpaCMD implements SubCommand {

    HashMap<UUID, UUID> sentInv = new HashMap<>();
    HashMap<UUID, UUID> receivedInv = new HashMap<>();

    @Override
    public void execute(Player player, String[] args) {

    }
}
