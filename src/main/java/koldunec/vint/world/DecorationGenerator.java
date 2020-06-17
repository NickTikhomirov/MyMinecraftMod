package koldunec.vint.world;

import koldunec.vint.blocks.BaseNylium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public abstract class DecorationGenerator implements IWorldGenerator {
    protected BlockPos getPos(World w, Random r, int chunkX, int chunkZ){
        int ix = chunkX<<4;
        int iz = chunkZ<<4;
        for(int i=0; i<10; ++i){
            int x = ix+r.nextInt(16);
            int z = iz+r.nextInt(16);
            int y = getUp(w, new BlockPos(x,128,z));
            if(y==-1) continue;
            return new BlockPos(x, y, z);
        }
        return null;
    }

    private int getUp(World w, BlockPos bp){
        for(int i=0; i<40; ++i){
            IBlockState state = w.getBlockState(bp);
            if(state.getBlock().equals(Blocks.AIR)){
                if(w.getBlockState(bp.down()).getBlock() instanceof BaseNylium)
                    return bp.getY();
            }
            bp = bp.up();
        }
        return -1;
    }
}
