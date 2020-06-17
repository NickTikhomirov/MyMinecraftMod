package koldunec.vint.world.nether.LightPlacers;

import koldunec.vint.world.nether.NetherLightGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Crystalplacer extends NetherLightGenerator.LightPlacer {
    @Override
    public void place(Random r, World w, BlockPos bp) {
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

    private void placeBlackWhite(World w, BlockPos bp){
        IBlockState[] crystals = new IBlockState[]{
            Block.getBlockFromName("quark:crystal").getStateFromMeta(0),
            Block.getBlockFromName("quark:crystal").getStateFromMeta(8)
        };
        for(int ix=0; ix<2; ++ix)
            for(int iz=0; iz<2; ++iz)
                for(int iy=0; iy<6; ++iy)
                    w.setBlockState(bp.add(ix,iy,iz),crystals[iy%2]);
    }

    private void placeRainbow(World w, BlockPos bp){
        Block crystal = Block.getBlockFromName("quark:crystal");
        for(int ix=0; ix<2; ++ix)
            for(int iz=0; iz<2; ++iz)
                for(int iy=0; iy<6; ++iy)
                    w.setBlockState(bp.add(ix,iy,iz),crystal.getStateFromMeta((iy==5)?7:(iy+1)));
    }
}
