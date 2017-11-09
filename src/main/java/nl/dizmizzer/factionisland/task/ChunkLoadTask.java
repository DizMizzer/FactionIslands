package nl.dizmizzer.factionisland.task;

import nl.dizmizzer.factionisland.SkyBlock;
import org.bukkit.scheduler.BukkitRunnable;

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
public class ChunkLoadTask extends BukkitRunnable {

    @Override
    public void run() {
        if (SkyBlock.getSkyBlock().inQueue.size() == 0) return;
        UUID uuid = (UUID) SkyBlock.getSkyBlock().inQueue.keySet().toArray()[0];
        int startX = SkyBlock.getSkyBlock().inQueueChunk.get(uuid)[0] * 256;
        int startZ = SkyBlock.getSkyBlock().inQueueChunk.get(uuid)[1] * 256;
        for (int i = 0; i < 4; i++) {
            SkyBlock.getSkyBlock().world.loadChunk(startX + 1 + i * 16 + (SkyBlock.getSkyBlock().inQueue.get(uuid) * 64) % 256, startZ + (SkyBlock.getSkyBlock().inQueue.get(uuid) / 4) * 16);
        }
        if (SkyBlock.getSkyBlock().inQueue.get(uuid) == 64) {
            SkyBlock.getSkyBlock().inQueue.remove(uuid);
            SkyBlock.getSkyBlock().inQueueChunk.remove(uuid);
            return;
        }

        SkyBlock.getSkyBlock().inQueue.put(uuid, SkyBlock.getSkyBlock().inQueue.get(uuid) + 1);
    }
}
