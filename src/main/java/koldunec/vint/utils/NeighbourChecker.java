package koldunec.vint.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class NeighbourChecker {

    public static boolean checkIce(IBlockState i) {
        return i.getBlock().equals(Blocks.PACKED_ICE);
    }
    public static boolean checkLiquid(IBlockState i) {
        return i.getMaterial().isLiquid();
    }
    public static boolean checkNonAir(IBlockState i) {
        return !i.getBlock().equals(Blocks.AIR);
    }


    public interface INeighbourPredicate{
        boolean check(IBlockState i);
    }

    public static int checkHorizontal(World w, BlockPos bp, INeighbourPredicate checker){
        int i = 0;
        for(EnumFacing e: EnumFacing.HORIZONTALS){
            if(checker.check(w.getBlockState(bp.offset(e))))
                ++i;
        }
        return i;
    }

    public static int checkHorizontal(Chunk w, BlockPos bp, INeighbourPredicate checker){
        int i = 0;
        for(EnumFacing e: EnumFacing.HORIZONTALS){
            if(checker.check(w.getBlockState(bp.offset(e))))
                ++i;
        }
        return i;
    }

    public static int checkAll(World w, BlockPos bp, INeighbourPredicate checker){
        int i = 0;
        for(EnumFacing e: EnumFacing.VALUES){
            if(checker.check(w.getBlockState(bp.offset(e))))
                ++i;
        }
        return i;
    }
}
