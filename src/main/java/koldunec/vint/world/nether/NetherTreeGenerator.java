package koldunec.vint.world.nether;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves2;
import com.progwml6.natura.nether.block.logs.BlockNetherLog;
import com.progwml6.natura.world.worldgen.trees.nether.FusewoodTreeGenerator;
import com.progwml6.natura.world.worldgen.trees.nether.GhostwoodTreeGenerator;
import koldunec.vint.ConfigHelper;
import koldunec.vint.compatibility.natura.NaturaShroomBuilder;
import koldunec.vint.compatibility.natura.ProperDarkwoodGen;
import koldunec.vint.world.structure_builders.TreeRouter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NetherTreeGenerator implements IWorldGenerator {

    public NetherTreeGenerator(){ }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!ConfigHelper.topHell)
            return;
        if(world.provider.getDimension()!=-1)
            return;
        int x = 4+random.nextInt(9);
        int z = 4+random.nextInt(9);
        Chunk ch = chunkProvider.getLoadedChunk(chunkX,chunkZ);
        if(ch==null)
            return;
        int y = ch.getHeightValue(x,z);
        x+=chunkX<<4;
        z+=chunkZ<<4;
        TreeRouter.execute(world, new BlockPos(x, y, z ), world.rand);
    }


}
