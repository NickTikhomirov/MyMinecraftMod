package koldunec.vint.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NeighbourChecker {

    public interface INeighbourPredicate{
        boolean check(IBlockState i);
    }

    public static int checkHorizontal(World w, BlockPos bp, INeighbourPredicate checker){
        int i = 0;
        if(checker.check(w.getBlockState(bp.north())))
            ++i;
        if(checker.check(w.getBlockState(bp.south())))
            ++i;
        if(checker.check(w.getBlockState(bp.east())))
            ++i;
        if(checker.check(w.getBlockState(bp.west())))
            ++i;
        return i;
    }

    public static int checkAll(World w, BlockPos bp, INeighbourPredicate checker){
        int i = checkHorizontal(w,bp,checker);
        if(checker.check(w.getBlockState(bp.up())))
            ++i;
        if(checker.check(w.getBlockState(bp.down())))
            ++i;
        return i;
    }
}
