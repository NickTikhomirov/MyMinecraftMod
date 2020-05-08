package koldunec.vint.world.nether;

import koldunec.vint.helpers.ConfigHelper;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.common.IWorldGenerator;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.worldgen.SlimeTreeGenerator;

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
                int h2 = (int)(14*perlovka.getValue((x+i)/60.0,(z+j)/60.0));
                int m = Math.max(h1,h2-6);
                if(h2>0)
                    fillColumnNatura2(ch,new BlockPos(i,133+m,j));
                else
                    fillColumnNatura(ch,new BlockPos(i,133+h1,j));
        }

        if(vint.random.nextInt(125)==0)
            generateTower(ch,random);
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

    void fillSlice(Chunk ch, int y, IBlockState state){
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


    void fillPoint4(Chunk ch, Vec3i offset, int x, int z, IBlockState state){
        ch.setBlockState(new BlockPos(x,0,z).add(offset), state);
        ch.setBlockState(new BlockPos(-x,0,z).add(offset), state);
        ch.setBlockState(new BlockPos(x,0,-z).add(offset), state);
        ch.setBlockState(new BlockPos(-x,0,-z).add(offset), state);
    }


    void fillCirle(Chunk ch, int y, IBlockState state, IBlockState state2){
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


    void generateTower(Chunk ch, Random random){
        int top = 150;
        if(IntegrationHelper.isLoadedTinkers && random.nextInt(1)==0)
            top = 131;
        for(int y=1; y<top; ++y)
            fillSlice(ch,y, BlockRegister.FRESH_DEBRIS.getDefaultState());
        if(top==131){
            IBlockState temp = Block.getBlockFromName(IntegrationHelper.idTinker+":slime_dirt").getStateFromMeta(3);
            for(int y=top; y<149;++y) {
                fillCirle(ch, y, BlockRegister.FRESH_DEBRIS.getDefaultState(), temp);
                if(y==top+2)
                    temp = Block.getBlockFromName(IntegrationHelper.idTinker+":slime_grass").getStateFromMeta(14);
                if(y==top+3)
                    temp = Blocks.AIR.getDefaultState();
            }
            top = 150;
            fillSlice(ch,149,BlockRegister.FRESH_DEBRIS.getDefaultState());
            int x = random.nextBoolean()?6:8;
            int z = random.nextBoolean()?6:8;
            SlimeTreeGenerator gen = new SlimeTreeGenerator(
                    5,
                    4,
                    TinkerCommons.blockSlimeCongealed.func_176203_a(4),
                    TinkerWorld.slimeLeaves.func_176203_a(2),
                    null);
            gen.generateTree(random,ch.getWorld(),new BlockPos((ch.x<<4)+x, 135,(ch.z<<4)+z));
        }
        if(IntegrationHelper.isLoadedQuark){
            if(random.nextInt(4)==0)
                for(int y=top;y<154; ++y)
                    fillSlice(ch,y,Block.getBlockFromName("quark:blaze_lantern").getDefaultState());
        }
    }

}
