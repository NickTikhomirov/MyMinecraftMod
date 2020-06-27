package koldunec.vint.world.nether;

import koldunec.vint.ConfigHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.vint;
import koldunec.vint.world.structure_builders.NetherTowerBuilder;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NetherGenerator implements IWorldGenerator {

    private NoiseGeneratorPerlin mainGenerator = null;
    private NoiseGeneratorPerlin secondGenerator = null;

    public NoiseGeneratorPerlin getGen(Random r){
        if(mainGenerator==null)
            mainGenerator = new NoiseGeneratorPerlin(r,2);
        return mainGenerator;
    }

    public NoiseGeneratorPerlin getGen2(Random r){
        if(secondGenerator==null)
            secondGenerator = new NoiseGeneratorPerlin(r,2);
        return secondGenerator;
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
        NoiseGeneratorPerlin perlovka = getGen(random);
        int x = chunkX*16;
        int z = chunkZ*16;

        for(int i=0; i<16; ++i)
            for(int j=0; j<16; ++j){
                int h1 = (int)(2*perlovka.getValue((x+i)/60.0,(z+j)/60.0));
                int h2 = (int)(10*perlovka.getValue((x+i)/120.0,(z+j)/120.0));
                int m = Math.max(h1,h2-4);
                if(h2>0)
                    fillColumnNatura2(ch,new BlockPos(i,133+m,j));
                else
                    fillColumnNatura(ch,new BlockPos(i,133+h1,j));
        }

        if(vint.random.nextInt(125)==0)
            NetherTowerBuilder.generateTower(ch,random);
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
}
