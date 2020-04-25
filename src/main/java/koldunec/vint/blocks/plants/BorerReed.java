package koldunec.vint.blocks.plants;

import koldunec.vint.helpers.NeighbourChecker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BorerReed {





    public boolean isSuitableForGrowOnce(World w, BlockPos bp){
        BlockPos base = bp.down();
        if(bp.getY()<3)
            return false;
        return isSafeWood(w, base) && isFullWood(w,base.down());
    }

    public boolean isFullWood(World w, BlockPos bp){
        IBlockState s = w.getBlockState(bp);
        //minecraftforum says
        // isFullBlock is more about placement
        // isFullCuve is more about visual (shadows etc)
        // however it is not a strict law
        //this says a lot about our society
        if(!s.isFullBlock())
            return false;
        return s.getMaterial().equals(Material.WOOD);
    }

    public boolean isSafeWood(World w, BlockPos bp){
        if(!isFullWood(w,bp))
            return false;
        if(w.getTileEntity(bp)!=null)
            return false;
        return NeighbourChecker.checkHorizontal(w,bp,liquidChecker)==0;
    }

    public static LiquidChecker liquidChecker = new LiquidChecker();

    public static class LiquidChecker implements NeighbourChecker.INeighbourPredicate{
        @Override
        public boolean check(IBlockState i) {
            return i.getMaterial().isLiquid();
        }
    }
}
