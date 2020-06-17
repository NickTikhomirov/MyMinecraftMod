package koldunec.vint.world.nether.LightPlacers;

import koldunec.vint.world.nether.NetherLightGenerator;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class PumpkinPlacer extends NetherLightGenerator.LightPlacer {
    public void place(Random r, World w, BlockPos bp){
        IBlockState pumpstate = Blocks.LIT_PUMPKIN.getDefaultState();
        EnumFacing e = EnumFacing.HORIZONTALS[r.nextInt(4)];
        pumpstate = pumpstate.withProperty(BlockPumpkin.FACING,e);
        w.setBlockState(bp,pumpstate);
    }
}
