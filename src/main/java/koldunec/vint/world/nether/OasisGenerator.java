package koldunec.vint.world.nether;

import koldunec.vint.ConfigHelper;
import koldunec.vint.utils.NeighbourChecker;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.IntegrationHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OasisGenerator implements IWorldGenerator {

    NoiseGeneratorPerlin mainGenerator = null;

    public NoiseGeneratorPerlin getGen(Random r){
        if(mainGenerator==null)
            mainGenerator = new NoiseGeneratorPerlin(r,2);
        return mainGenerator;
    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!ConfigHelper.topHell)
            return;
        if(world.provider.getDimension()!=-1)
            return;
        int x = (chunkX<<4)+8;
        int z = (chunkZ<<4)+8;
        Block air = world.getBlockState(new BlockPos(x,145,z)).getBlock();
        if(!air.equals(Blocks.AIR))
            return;
        NoiseGeneratorPerlin perlovka = getGen(random);
        x -=8;
        z -=8;
        Chunk ch = chunkProvider.getLoadedChunk(chunkX,chunkZ);
        for(int i=0;i<16;++i)
            for(int j=0;j<16;++j) {
                double h = 1*perlovka.getValue((x+i)/50F,(z+j)/50F);
                if(h>2)
                    placeSoul(ch,new BlockPos(i,ch.getHeightValue(i,j)-1,j),random, h);
            }

    }



    void placeSoul(Chunk ch, BlockPos bp, Random r, double randomised){
        if(bp.getY()>135)
            return;
        ch.setBlockState(bp.down(),Blocks.SOUL_SAND.getDefaultState());
        ch.setBlockState(bp,Blocks.SOUL_SAND.getDefaultState());
        bp = bp.up();
        if(NeighbourChecker.checkHorizontal(ch,bp,NeighbourChecker::checkNonAir)==0 && r.nextInt(15)==0){
            ch.setBlockState(bp,BlockRegister.NETHER_CACTUS.getDefaultState());
            switch(r.nextInt(3)){
                case 0:
                    bp = bp.up();
                    ch.setBlockState(bp,BlockRegister.NETHER_CACTUS.getDefaultState());
                case 1:
                    bp = bp.up();
                    ch.setBlockState(bp,BlockRegister.NETHER_CACTUS.getDefaultState());
            }
        } else if(IntegrationHelper.isLoadedFuture && r.nextInt(100)==0){
            Block wrose;
            if(IntegrationHelper.idFuture.equals("futuremc"))
                wrose = Block.getBlockFromName("futuremc:wither_rose");
            else
                wrose = Block.getBlockFromName(IntegrationHelper.idFuture+":flowerblack");
            if(wrose==null)
                return;
            ch.setBlockState(bp, wrose.getDefaultState());


        }
    }
}
