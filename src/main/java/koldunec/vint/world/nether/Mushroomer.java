package koldunec.vint.world.nether;

import com.progwml6.natura.nether.NaturaNether;
import koldunec.vint.helpers.ConfigHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.world.DecorationGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import twilightforest.block.TFBlocks;

import java.util.ArrayList;
import java.util.Random;

public class Mushroomer extends DecorationGenerator {
    ArrayList<IBlockState> RedReplacers = new ArrayList<IBlockState>();
    ArrayList<IBlockState> BlueReplacers = new ArrayList<IBlockState>();

    public Mushroomer(){
        if(IntegrationHelper.isLoadedTwilight) {
            RedReplacers.add(TFBlocks.twilight_plant.getStateFromMeta(4));
            BlueReplacers.add(TFBlocks.twilight_plant.getStateFromMeta(4));
        }
        if(IntegrationHelper.isLoadedNatura){
            BlueReplacers.add(NaturaNether.netherGlowshroom.getStateFromMeta(0));
            BlueReplacers.add(NaturaNether.netherGlowshroom.getStateFromMeta(1));
            BlueReplacers.add(NaturaNether.netherGlowshroom.getStateFromMeta(2));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!ConfigHelper.topHell)
            return;
        if(world.provider.getDimension()!=-1)
            return;
        boolean isReplacedRed = random.nextInt(5)==0;
        boolean isReplacedBlue = random.nextBoolean();
        IBlockState stateRed;
        IBlockState stateBlue;
        if(isReplacedRed) stateRed = RedReplacers.get(random.nextInt(RedReplacers.size()));
        else stateRed = BlockRegister.RED_NETHER_MUSH.getDefaultState();
        if(isReplacedBlue) stateBlue = BlueReplacers.get(random.nextInt(BlueReplacers.size()));
        else stateBlue = BlockRegister.BLUE_NETHER_MUSH.getDefaultState();
        for(int i=0; i<12 ;++i){
            BlockPos pos = getPos(world,random,chunkX,chunkZ);
            if(pos==null)
                continue;
            IBlockState ground = world.getBlockState(pos.down());
            if(ground.getBlock().equals(BlockRegister.RED_NYLIUM))
                world.setBlockState(pos,stateRed);
            if(ground.getBlock().equals(BlockRegister.BLUE_NYLIUM))
                world.setBlockState(pos,stateBlue);
        }
    }

}
