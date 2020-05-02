package koldunec.vint.world.nether;


import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import slimeknights.tconstruct.world.worldgen.MagmaSlimeIslandGenerator;

import java.util.Random;

public class TinkerIsleGen extends MagmaSlimeIslandGenerator {

    public TinkerIsleGen(){ super(); }
    static TinkerIsleGen stat = new TinkerIsleGen();

    @Override
    protected void generateIslandInChunk(long seed, World world, int chunkX, int chunkZ) {
        Random random = new Random(seed);
        int y = 134;
        int x = chunkX * 16 + 4 + random.nextInt(8);
        int z = chunkZ * 16 + 4 + random.nextInt(8);
        this.generateIsland(random, world, x, z, y, this.dirtMagma, this.grassMagma, null, this.lakeGenMagma, this.treeGenMagma, this.plantGenMagma);
    }

    static void addSomeStuff(Chunk ch, long ran){
        World w = ch.getWorld();
        boolean suitable = true;
        int x = ch.x<<4;
        int z = ch.z<<4;
        int y = 134;
        for(int i=-20; i<21; ++i) {
            for (int j = -20; j < 21; ++j)
                if (!w.getBlockState(new BlockPos(x + i, y, z + j)).getBlock().equals(Blocks.AIR)) {
                    suitable = false;
                    break;
                }
            if(!suitable)
                break;
        }
        if(!suitable)
            return;
        stat.generateIslandInChunk(ran,ch.getWorld(),ch.x,ch.z);
    }
}
