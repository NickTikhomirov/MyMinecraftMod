package koldunec.vint.world.structure_builders;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.world.structure_builders.TowerDecorators.TopDecorator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class NetherTowerBuilder {
    public static void generateTower(Chunk ch, Random random){
        int top = 131;
        for(int y=1; y<top; ++y)
            fillSlice(ch,y, BlockRegister.FRESH_DEBRIS.getDefaultState());
        TopDecorator.execute(random,top,ch);
    }



    public static void fillSlice(Chunk ch, int y, IBlockState state){
        Vec3i center = new Vec3i(7,y,7);
        int x=0;
        int z=0;
        for(x=0; x<7; ++x) {
            ch.setBlockState(new BlockPos(x, 0, z).add(center), state);
            ch.setBlockState(new BlockPos(-x, 0, z).add(center), state);
            ch.setBlockState(new BlockPos(z, 0, x).add(center), state);
            ch.setBlockState(new BlockPos(z, 0, -x).add(center), state);
        }
        for(x=1;x<7;++x)
            for(z=1; z<7; ++z)
                if(x+z<9 && (x!=2 || z!=6) && (z!=2 || x!=6))
                    fillPoint4(ch,center,x,z,state);
    }


    static void fillPoint4(Chunk ch, Vec3i offset, int x, int z, IBlockState state){
        ch.setBlockState(new BlockPos(x,0,z).add(offset), state);
        ch.setBlockState(new BlockPos(-x,0,z).add(offset), state);
        ch.setBlockState(new BlockPos(x,0,-z).add(offset), state);
        ch.setBlockState(new BlockPos(-x,0,-z).add(offset), state);
    }


    public static void fillCirle(Chunk ch, int y, IBlockState state, IBlockState state2){
        Vec3i center = new Vec3i(7,y,7);
        IBlockState temp = state2;
        int x;
        int z=0;
        for(x=0; x<7; ++x) {
            ch.setBlockState(new BlockPos(x, 0, z).add(center), temp);
            ch.setBlockState(new BlockPos(-x, 0, z).add(center), temp);
            ch.setBlockState(new BlockPos(z, 0, x).add(center), temp);
            ch.setBlockState(new BlockPos(z, 0, -x).add(center), temp);
            if(x==5)
                temp = state;
        }
        for(x=1;x<7;++x)
            for(z=1; z<7; ++z)
                if((x+z==8 && (x!=2 || z!=6) && (z!=2 || x!=6))
                        || (x+z==7 && x*z<12))
                    fillPoint4(ch,center,x,z,state);
                else if(x+z<8)
                    fillPoint4(ch,center,x,z,state2);
    }
}
