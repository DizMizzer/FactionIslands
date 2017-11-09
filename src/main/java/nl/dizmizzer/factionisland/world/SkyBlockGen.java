package nl.dizmizzer.factionisland.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class SkyBlockGen extends ChunkGenerator {


    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    public int xyzToByte(int x, int y, int z) {
        return (x * 16 + z) * 128 + y;
    }

    @Override
    public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
        byte[] result = new byte[32768];
        int y = 0;
        //This will set the floor of each chunk at bedrock level to bedrock
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                result[xyzToByte(x, y, z)] = (byte) Material.BEDROCK.getId();
            }
        }
        for (y = 1; y < 20; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    result[xyzToByte(x, y, z)] = (byte) Material.WATER.getId();
                }
            }

        }
        return result;
    }

}