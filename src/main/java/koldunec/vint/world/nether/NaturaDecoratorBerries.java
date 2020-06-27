package koldunec.vint.world.nether;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.world.worldgen.berry.nether.NetherBerryBushGenerator;
import koldunec.vint.ConfigHelper;
import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NaturaDecoratorBerries implements IWorldGenerator {

    static class NiceGenerator extends NetherBerryBushGenerator{
        public NiceGenerator(IBlockState berryBush) {
            super(berryBush);
        }

        @Override
        public void generateBush(Random random, World world, BlockPos pos) {
            int size = random.nextInt(10);
            if (size == 9) {
                this.generateLargeNode(random, world, pos);
            } else if (size >= 7) {
                this.generateShrub(random, world, pos);
            } else if (size >= 3) {
                this.generateSmallNode(random, world, pos);
            } else {
                this.generateTinyNode(random, world, pos);
            }
            super.generateBush(random, world, pos);
        }
    }


    public NetherBerryBushGenerator blightberryBushGen;
    public NetherBerryBushGenerator duskberryBushGen;
    public NetherBerryBushGenerator skyberryBushGen;
    public NetherBerryBushGenerator stingberryBushGen;

    public NaturaDecoratorBerries(){
        blightberryBushGen = new NiceGenerator(NaturaNether.netherBerryBushBlightberry.getDefaultState());
        duskberryBushGen = new NiceGenerator(NaturaNether.netherBerryBushDuskberry.getDefaultState());
        skyberryBushGen = new NiceGenerator(NaturaNether.netherBerryBushSkyberry.getDefaultState());
        stingberryBushGen = new NiceGenerator(NaturaNether.netherBerryBushStingberry.getDefaultState());
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(world.provider.getDimension()!=-1)
            return;
        if(!ConfigHelper.topHell)
            return;
        if(random.nextInt(20)!=0)
            return;
        int amount = 1+random.nextInt(3);
        Chunk ch = chunkProvider.getLoadedChunk(chunkX,chunkZ);
        for(int i=0; i<amount;++i){
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = ch.getHeightValue(x,z)-1;
            Block b = ch.getBlockState(x,y,z).getBlock();
            NetherBerryBushGenerator gen = getGenerator(random,b);
            if(gen!=null)
                gen.generateBush(random,world,new BlockPos((chunkX<<4)+x,y+1,(chunkZ<<4)+z));
        }
    }



    public NetherBerryBushGenerator getGenerator(Random r, Block b){
        if(b==BlockRegister.BLUE_NYLIUM){
            if(r.nextBoolean())
                return skyberryBushGen;
            return duskberryBushGen;
        }
        if(b==BlockRegister.RED_NYLIUM){
            if(r.nextBoolean())
                return blightberryBushGen;
            return stingberryBushGen;
        }
        return null;
    }

}
