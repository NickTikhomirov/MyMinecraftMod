package koldunec.vint.compatibility.OtherIntegration;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.world.structure_builders.TreeRouter;
import lumien.randomthings.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class RandomThingsModule {

    public static IBlockState LieToRandom(World w, BlockPos pos){
        IBlockState state = w.getBlockState(pos);
        w.setBlockState(pos, Blocks.DIRT.getDefaultState());
        return state;
    }

    public static TreeRouter.SingleTreeGen getTreeGen(){
        return (World w, BlockPos pos, Random r)-> {
            if(w.getBlockState(pos.down()).getBlock().equals(BlockRegister.RED_NYLIUM)){
                TreeRouter.DEFAULT.gen.generate(w, pos, r);
                return;
            }
            IBlockState state = LieToRandom(w, pos.down());
            ModBlocks.spectreSapling.generateTree(w, pos, ModBlocks.spectreSapling.getDefaultState(), r);
            w.setBlockState(pos.down(), state);
        };
    }
}
