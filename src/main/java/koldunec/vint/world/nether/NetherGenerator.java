package koldunec.vint.world.nether;

import koldunec.vint.helpers.ConfigHelper;
import koldunec.vint.helpers.TechHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NetherGenerator implements IWorldGenerator {

    NoiseGeneratorPerlin mainGenerator = null;

    public NoiseGeneratorPerlin getGen(Random r){
        if(mainGenerator==null)
            mainGenerator = new NoiseGeneratorPerlin(r,2);
        return mainGenerator;
    }

    public NoiseGeneratorPerlin getGen(){
        return getGen(vint.random);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!ConfigHelper.topHell)
            return;
        if(world.provider.getDimension()!=-1)
            return;
        Chunk ch = chunkProvider.getLoadedChunk(chunkX,chunkZ);
        /*
        for(int y=0; y<5; ++y)
            for(int x=0; x<16; ++x)
                for(int z=0; z<16; ++z)
                    fillNatura(ch, new BlockPos(x,y+128,z));
                    */

        NoiseGeneratorPerlin perlovka = getGen(random);

        int x = chunkX*16;
        int z = chunkZ*16;

        for(int i=0; i<16; ++i)
            for(int j=0; j<16; ++j){
                int h = (int)(2*perlovka.getValue((x+i)/60.0,(z+j)/60.0));
                fillColumnNatura(ch,new BlockPos(i,133+h,j));
        }

        if(TechHelper.isPrime(chunkX%41)&&TechHelper.isPrime(chunkZ%41)) {

        } else if(vint.random.nextInt(125)==0){
            for(int y=1; y<150; ++y)
                fillSlice(ch,y);
        } else if(vint.random.nextInt(200)==0){
            for(int i=0; i<16; ++i)
                for(int j=0; j<16; ++j) {
                    double foo = -(i - 7.5)*(i - 7.5)/10F - (j - 7.5)*(j - 7.5)/10F + 6;
                    int h = (int)(foo+perlovka.getValue((x+i)/20F,(z+j)/20F));
                    fillColumnNatura2(ch,new BlockPos(i,133+h,j));
                }
        }
    }

    void fillColumnNatura(Chunk ch, BlockPos bp){
        BlockPos pos = new BlockPos(bp.getX(),128,bp.getZ());
        for(;pos.getY()<bp.getY();pos=pos.add(0,1,0))
            fillNatura(ch,pos);
        ch.setBlockState(bp,BlockRegister.RED_NYLIUM.getDefaultState());
    }

    void fillColumnNatura2(Chunk ch, BlockPos bp){
        BlockPos pos = new BlockPos(bp.getX(),128,bp.getZ());
        for(;pos.getY()<bp.getY();pos=pos.add(0,1,0))
            fillNatura(ch,pos);
        ch.setBlockState(bp,BlockRegister.BLUE_NYLIUM.getDefaultState());
    }


    void fillNatura(Chunk ch, BlockPos bp){
        ch.setBlockState(bp,Block.getBlockFromName("natura:nether_tainted_soil").getDefaultState());
    }

    void fillBasalt(Chunk ch, BlockPos bp){
        ch.setBlockState(bp,BlockRegister.BASALT_RAW.getDefaultState());
    }

    void fillSlice(Chunk ch, int y){
        Vec3i center = new Vec3i(7,y,7);
        int x=0;
        int z=0;
        for(x=0; x<7; ++x) {
            fillBasalt(ch, new BlockPos(x, 0, z).add(center));
            fillBasalt(ch, new BlockPos(-x, 0, z).add(center));
            fillBasalt(ch, new BlockPos(z, 0, x).add(center));
            fillBasalt(ch, new BlockPos(z, 0, -x).add(center));
        }
        for(x=1;x<7;++x)
            for(z=1; z<7; ++z)
                if(x+z<9 && (x!=2 || z!=6) && (z!=2 || x!=6))
                    fillPoint4(ch,center,x,z);
    }

    void fillPoint4(Chunk ch, Vec3i offset, int x, int z){
        fillBasalt(ch, new BlockPos(x,0,z).add(offset));
        fillBasalt(ch, new BlockPos(-x,0,z).add(offset));
        fillBasalt(ch, new BlockPos(x,0,-z).add(offset));
        fillBasalt(ch, new BlockPos(-x,0,-z).add(offset));
    }

}
