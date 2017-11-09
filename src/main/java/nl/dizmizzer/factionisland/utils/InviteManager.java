package nl.dizmizzer.factionisland.utils;

import nl.dizmizzer.factionisland.SkyBlock;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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
public class InviteManager {

    private static InviteManager instance = new InviteManager();
    File inviteFile;
    FileConfiguration inviteConfig;

    private InviteManager() {
    }

    public static InviteManager get() {
        return instance;
    }

    public void setup() {
        if (!SkyBlock.getSkyBlock().getDataFolder().exists()) {
            SkyBlock.getSkyBlock().getDataFolder().mkdir();
        }

        inviteFile = new File(SkyBlock.getSkyBlock().getDataFolder(), "invited.yml");
        if (!inviteFile.exists()) {
            try {
                inviteFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        inviteConfig = YamlConfiguration.loadConfiguration(inviteFile);
    }

    public void setInviting(String UUID, String FactionID) {
        inviteConfig.set(UUID, FactionID);
    }

    public void removeInviting(String UUID) {
        if (inviteConfig.contains(UUID)) inviteConfig.set(UUID, null);
    }

    public FileConfiguration getConfig() {

        return inviteConfig;
    }

    public void saveConfig() {
        try {
            inviteConfig.save(inviteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
