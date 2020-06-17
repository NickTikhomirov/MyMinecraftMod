package koldunec.vint.world.nether.LightPlacers;

import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.world.nether.NetherLightGenerator;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Fireplacer extends NetherLightGenerator.LightPlacer {

    @Override
    public void place(Random r, World w, BlockPos bp) {
        w.setBlockState(bp, Block.getBlockFromName(IntegrationHelper.idFuture+":campfire").getStateFromMeta(4));
    }
}
