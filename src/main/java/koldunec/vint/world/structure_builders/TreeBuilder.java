package koldunec.vint.world.structure_builders;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.IntegrationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class TreeBuilder {

    //stages
    // 0: log and no leaves
    // 1: log and corner leaves
    // 2: log and full border
    // 3: top

    private static HashMap<Integer, Integer> STAGE_PARAMS = new HashMap<Integer, Integer>(){{
        put(0,2);   //+1 block prebuilt
        put(1,2);
        put(2,3);
        put(3,3);
    }};



    public static void BuildProperTree(World w, BlockPos firstBlock){
        IBlockState ground = w.getBlockState(firstBlock.down());
        if(ground.getBlock().equals(BlockRegister.RED_NYLIUM)) {
            if(!IntegrationHelper.isLoadedCharm || w.rand.nextInt(100)!=0)
                BuildNetherTree(w, firstBlock, BlockRegister.RED_NY_LOG, Blocks.NETHER_WART_BLOCK.getDefaultState());
            else
                BuildNetherTree(w, firstBlock, Blocks.BONE_BLOCK, Block.getBlockFromName("charm:rotten_flesh_block").getDefaultState());
        }
        if(ground.getBlock().equals(BlockRegister.BLUE_NYLIUM))
            BuildNetherTree(w,firstBlock,BlockRegister.BLUE_NY_LOG, BlockRegister.WARPED_WART.getDefaultState());
    }

    public static void BuildNetherTree(World w, BlockPos firstBlock, Block log, IBlockState leaves){
        IBlockState logstate = log.getDefaultState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.UP.getAxis());
        w.setBlockState(firstBlock,logstate);
        BlockPos offset = firstBlock.up();
        for(EnumFacing e: EnumFacing.HORIZONTALS)
            w.setBlockState(firstBlock.offset(e),logstate.withProperty(BlockRotatedPillar.AXIS,e.getAxis()));
        for(int stage=0; stage<4; ++stage){
            int stagemax = STAGE_PARAMS.get(stage);
            for(int i=1; i<=stagemax; ++i){
                switch(stage){
                    case 2:{
                        for(int ix=-2;ix<3; ++ix){
                            BlockPos startposition = new BlockPos(ix,0,-2);
                            if(ix==-2 || ix==2){
                                for(int iz=1; iz<4; ++iz)
                                    BuildWithOffset(w,startposition.add(0,0,iz), leaves, offset);
                            } else {
                                BlockPos endpos = startposition.add(0,0,4);
                                BuildWithOffset(w, startposition, leaves, offset);
                                BuildWithOffset(w, endpos, leaves, offset);
                            }
                        }
                    }
                    case 1:{
                        BlockPos zerozero = new BlockPos(-2,0,-2);
                        BuildWithOffset(w, zerozero, leaves, offset);
                        BuildWithOffset(w, zerozero.add(0,0,4), leaves, offset);
                        BuildWithOffset(w, zerozero.add(4,0,0), leaves, offset);
                        BuildWithOffset(w, zerozero.add(4,0,4), leaves, offset);
                    }
                    case 0:{
                        w.setBlockState(offset,logstate);
                        break;
                    }
                    case 3:{
                        for(int ix=-1; ix<2;++ix){
                            for(int iz=-1; iz<2;++iz){
                                BuildWithOffset(w,new BlockPos(ix,0,iz),leaves,offset);
                            }
                        }
                    }
                }
                offset=offset.up();
            }
        }
    }

    private static void BuildWithOffset(World w, BlockPos bp, IBlockState i, BlockPos offset){
        w.setBlockState(bp.add(offset.getX(),offset.getY(),offset.getZ()),i);
    }
}
