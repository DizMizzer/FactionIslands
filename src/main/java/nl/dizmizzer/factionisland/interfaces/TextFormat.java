package nl.dizmizzer.factionisland.interfaces;

import org.bukkit.ChatColor;

public interface TextFormat {

    public String name = ChatColor.translateAlternateColorCodes('&', "&dCrystalWars"),
            textColor = ChatColor.translateAlternateColorCodes('&', "&e"),
            numberColor = ChatColor.translateAlternateColorCodes('&', "&3"),
            infoColor = ChatColor.translateAlternateColorCodes('&', "&b"),
            boldColor = ChatColor.translateAlternateColorCodes('&', "&8"),
            linkColor = ChatColor.translateAlternateColorCodes('&', "&9"),
            chatColor = ChatColor.translateAlternateColorCodes('&', "&d"),
            errorColor = ChatColor.translateAlternateColorCodes('&', "&c"),
            prefix = ChatColor.translateAlternateColorCodes('&', "&dCrystalWars> &7"), whiteColor = ChatColor.WHITE + "",
            a = prefix + chatColor,
    gray = ChatColor.GRAY.toString(),
    permissionErrorMessage = prefix + textColor
            + "Sorry, but you don't have permission to perform this action. Please contact an administrator if you think this is an error",
            commandError = prefix + errorColor + "Error while executing command, try" + boldColor + ": /"
                    + "fi ",
            playerErrorMessage = prefix + errorColor + "Unable to find player, ";
    public String[] helpMessage = {
            prefix + chatColor + "/fi create: " + ChatColor.GRAY + "Create your faction and Faction-island.",
            prefix + chatColor + "/fi delete: " + ChatColor.GRAY + "Delete your faction and island.",
            prefix + chatColor + "/fi home: " + ChatColor.GRAY + "Go to your faction home.",
            prefix + chatColor + "/fi tpa: " + ChatColor.GRAY + "Request a teleport to another player.",
            prefix + chatColor + "/fi invite: " + ChatColor.GRAY + "Invite a player to your faction.",
            a + "/fi accept: " + gray + "Accept your invite to a faction.",
            a + "/fi deny: " + gray + "Deny your invite to a faction.",
            a + "/fi kick: " + gray + "Kick a player from your faction.",
            a + "/fi desc: " + gray + "Set the description of your faction.",
            a + "/fi sethome: " + gray + "Set the home of your faction.",
            a + "/fi promote: " + gray + "Promote a player in your faction.",
            a + "/fi demote: " + gray + "Demote a player in your faction.",
            a + "/fi delete: " + gray + "Delete your faction.",
            a + "/fi coleader: " + gray + "Make someone coleader of your faction.",
            a + "/fi info: " + gray + "Get information about your faction.",
            a + "/fi ally: " + gray + "Ally a faction.",
            a + "/fi enemy: " + gray + "Make a faction your enemy."
    },
            opHelpMessage = {
                    prefix + chatColor + "/fi create <Name>: " + ChatColor.GRAY + "Create your faction and Faction-island.",
                    prefix + chatColor + "/fi delete: " + ChatColor.GRAY + "Delete your faction and island.",
                    prefix + chatColor + "/fi home: " + ChatColor.GRAY + "Go to your faction home.",
                    prefix + chatColor + "/fi tpa <Player>: " + ChatColor.GRAY + "Request a teleport to another player.",
                    prefix + chatColor + "/fi tp <Player>: " + ChatColor.GRAY + "Teleport to a player."
            };

}

