package nl.dizmizzer.factionisland.interfaces;

import org.bukkit.ChatColor;

public interface TextFormat {

    public String name = ChatColor.translateAlternateColorCodes('&', "&5CrystalWars"),
            textColor = ChatColor.translateAlternateColorCodes('&', "&e"),
            numberColor = ChatColor.translateAlternateColorCodes('&', "&3"),
            infoColor = ChatColor.translateAlternateColorCodes('&', "&b"),
            boldColor = ChatColor.translateAlternateColorCodes('&', "&8"),
            linkColor = ChatColor.translateAlternateColorCodes('&', "&9"),
            chatColor = ChatColor.translateAlternateColorCodes('&', "&5"),
            errorColor = ChatColor.translateAlternateColorCodes('&', "&c"),
            prefix = ChatColor.translateAlternateColorCodes('&', "&5CrystalWars> &7"), whiteColor = ChatColor.WHITE + "",

    permissionErrorMessage = prefix + textColor
            + "Sorry, but you don't have permission to perform this action. Please contact an administrator if you think this is an error",
            commandError = prefix + errorColor + "Error while executing command, try" + boldColor + ": /"
                    + "fi ",
            playerErrorMessage = prefix + errorColor + "Unable to find player, ";
    public String[] helpMessage = {
            prefix + chatColor + "/fi create: " + ChatColor.GRAY + "Create your faction and Faction-island.",
            prefix + chatColor + "/fi delete: " + ChatColor.GRAY + "Delete your faction and island.",
            prefix + chatColor + "/fi home: " + ChatColor.GRAY + "Go to your faction home.",
            prefix + chatColor + "/fi tpa: " + ChatColor.GRAY + "Request a teleport to another player."
    },
            opHelpMessage = {
                    prefix + chatColor + "/fi create <Name>: " + ChatColor.GRAY + "Create your faction and Faction-island.",
                    prefix + chatColor + "/fi delete: " + ChatColor.GRAY + "Delete your faction and island.",
                    prefix + chatColor + "/fi home: " + ChatColor.GRAY + "Go to your faction home.",
                    prefix + chatColor + "/fi tpa <Player>: " + ChatColor.GRAY + "Request a teleport to another player.",
                    prefix + chatColor + "/fi tp <Player>: " + ChatColor.GRAY + "Teleport to a player."
            };

}

