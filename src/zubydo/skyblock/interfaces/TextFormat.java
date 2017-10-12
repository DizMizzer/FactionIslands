package zubydo.skyblock.interfaces;

import org.bukkit.ChatColor;

public interface TextFormat {

	public String name = ChatColor.translateAlternateColorCodes('&', "&5CrystalWars"),
			textColor = ChatColor.translateAlternateColorCodes('&', "&e"),
			numberColor = ChatColor.translateAlternateColorCodes('&', "&3"),
			infoColor = ChatColor.translateAlternateColorCodes('&', "&b"),
			boldColor = ChatColor.translateAlternateColorCodes('&', "&8"),
			linkColor = ChatColor.translateAlternateColorCodes('&', "&9"),
			chatColor = ChatColor.translateAlternateColorCodes('&', "&5"),
			
			prefix = ChatColor.translateAlternateColorCodes('&', "&5CrystalWars> &7"), whiteColor = ChatColor.WHITE + "",
			helpMessage = prefix + boldColor + "/" + chatColor + "island " + boldColor + "<" 
					 + chatColor + "create:" + boldColor + ">" + textColor + " Create an island/faction. "
					 		+ chatColor + "delete:" + textColor + " Deletes an island with the faction. " + chatColor + "home:" + textColor + " Teleports you to your faction home. " + chatColor + "tpa:" + textColor + " Tpa to another player.",
			opHelpMessage = prefix + boldColor + "/" + chatColor + "island " + boldColor + "<" 
					 + chatColor + "create:" + boldColor + ">" + textColor + " Create an island/faction. "
				 		+ chatColor + "delete:" + textColor + " Deletes an island with the faction. " + chatColor + "home:" + textColor + " Teleports you to your faction home. " + chatColor + "tpa:" + textColor + " Tpa to another player." + chatColor + " tp:" + textColor + " Tp to another player.",
			permissionErrorMessage = prefix + textColor
					+ "Sorry, but you don't have permission to perform this action. Please contact an administrator if you think this is an error",
			commandError = prefix + textColor + "Error while executing command, try" + boldColor + ": /" + textColor
					+ "island ",
			playerErrorMessage = prefix + textColor + "Unable to find player, ";

}

                         