package koldunec.vint.world.nether;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves2;
import com.progwml6.natura.nether.block.logs.BlockNetherLog;
import com.progwml6.natura.world.worldgen.trees.nether.DarkwoodTreeGenerator;
import com.progwml6.natura.world.worldgen.trees.nether.FusewoodTreeGenerator;
import com.progwml6.natura.world.worldgen.trees.nether.GhostwoodTreeGenerator;
import koldunec.vint.ConfigHelper;
import koldunec.vint.world.structure_builders.TreeBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NetherTreeGenerator implements IWorldGenerator {
    public DarkwoodTreeGenerator darkTreeGen;
    public FusewoodTreeGenerator fuseTreeGen;
    public GhostwoodTreeGenerator ghostTreeGen;

    public NetherTreeGenerator(){
        IBlockState netherLog = NaturaNether.netherLog.getDefaultState();
        IBlockState netherLog2 = NaturaNether.netherLog2.getDefaultState();
        IBlockState netherLeaves = NaturaNether.netherLeaves.getDefaultState();
        IBlockState netherLeaves2 = NaturaNether.netherLeaves2.getDefaultState();
        darkTreeGen = new DarkwoodTreeGenerator(
                3,
                netherLog.withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.DARKWOOD),
                netherLeaves2.withProperty(BlockNetherLeaves2.TYPE, BlockNetherLeaves2.LeavesType.DARKWOOD),
                netherLeaves2.withProperty(BlockNetherLeaves2.TYPE, BlockNetherLeaves2.LeavesType.DARKWOOD_FLOWERING),
                netherLeaves2.withProperty(BlockNetherLeaves2.TYPE, BlockNetherLeaves2.LeavesType.DARKWOOD_FRUIT));
        fuseTreeGen = new FusewoodTreeGenerator(
                3,
                netherLog.withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.FUSEWOOD),
                netherLeaves.withProperty(BlockNetherLeaves.TYPE, BlockNetherLeaves.LeavesType.FUSEWOOD));
        ghostTreeGen = new GhostwoodTreeGenerator(
                netherLog.withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.GHOSTWOOD),
                netherLeaves.withProperty(BlockNetherLeaves.TYPE, BlockNetherLeaves.LeavesType.GHOSTWOOD),
                true);
    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!ConfigHelper.topHell)
            return;
        if(world.provider.getDimension()!=-1)
            return;
        int x = 4+random.nextInt(9);
        int z = 4+random.nextInt(9);
        Chunk ch = chunkProvider.getLoadedChunk(chunkX,chunkZ);
        int y = ch.getHeightValue(x,z);         // temp
        x+=chunkX<<4;                           // temp
        z+=chunkZ<<4;                           // temp
        TreeBuilder.BuildProperTree(world,new BlockPos(x,y,z));    // temp
    }


    public void generateBlueNaturaTree(Random random, World w, BlockPos bp){
        if(random.nextInt(5)==0) {
            fuseTreeGen.generateTree(random,w,bp);
            return;
        }
        darkTreeGen.generateTree(random,w,bp);
    }

}
