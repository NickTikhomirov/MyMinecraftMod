package koldunec.vint.world.structure_builders;

import koldunec.vint.IntegrationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class LightPlacer_Actions {

    public static void placeCrystal(Random r, World w, BlockPos bp) {
        int variant = r.nextInt(9);
        if(bp.getX()%16==15)
            bp.add(-1,0,0);
        if(bp.getZ()%16==15)
            bp.add(0,0,-1);
        if(variant!=0 && variant!=8){
            IBlockState crystal = Block.getBlockFromName("quark:crystal").getStateFromMeta(variant);
            for(int ix=0; ix<2; ++ix)
                for(int iz=0; iz<2; ++iz)
                    for(int iy=0; iy<6; ++iy)
                        w.setBlockState(bp.add(ix,iy,iz),crystal);
            return;
        }
        if(variant==0)
            placeBlackWhite(w,bp);
        else
            placeRainbow(w,bp);
    }


    public static void placeFire(Random r, World w, BlockPos bp) {
        w.setBlockState(bp, Block.getBlockFromName(IntegrationHelper.idFuture+":campfire").getStateFromMeta(4));
    }

    public static void placePumpkin(Random r, World w, BlockPos bp){
        IBlockState pumpstate = Blocks.LIT_PUMPKIN.getDefaultState();
        EnumFacing e = EnumFacing.HORIZONTALS[r.nextInt(4)];
        pumpstate = pumpstate.withProperty(BlockPumpkin.FACING,e);
        w.setBlockState(bp,pumpstate);
    }




    // tech methods
    private static void placeBlackWhite(World w, BlockPos bp){
        IBlockState[] crystals = new IBlockState[]{
                Block.getBlockFromName("quark:crystal").getStateFromMeta(0),
                Block.getBlockFromName("quark:crystal").getStateFromMeta(8)
        };
        for(int ix=0; ix<2; ++ix)
            for(int iz=0; iz<2; ++iz)
                for(int iy=0; iy<6; ++iy)
                    w.setBlockState(bp.add(ix,iy,iz),crystals[iy%2]);
    }

    private static void placeRainbow(World w, BlockPos bp){
        Block crystal = Block.getBlockFromName("quark:crystal");
        for(int ix=0; ix<2; ++ix)
            for(int iz=0; iz<2; ++iz)
                for(int iy=0; iy<6; ++iy)
                    w.setBlockState(bp.add(ix,iy,iz),crystal.getStateFromMeta((iy==5)?7:(iy+1)));
    }
}
