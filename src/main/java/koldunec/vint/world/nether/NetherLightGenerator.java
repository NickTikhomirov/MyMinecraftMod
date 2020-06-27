package koldunec.vint.world.nether;

import koldunec.vint.ConfigHelper;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.world.DecorationGenerator;
import koldunec.vint.world.structure_builders.LightPlacer_Actions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import java.util.ArrayList;
import java.util.Random;

public class NetherLightGenerator extends DecorationGenerator {


    private static ArrayList<LightPlacer> PLACERS = new ArrayList<LightPlacer>(){{
        add(LightPlacer_Actions::placePumpkin);
        add(LightPlacer_Actions::placePumpkin);
        add(LightPlacer_Actions::placePumpkin);
    }};

    public NetherLightGenerator(){
        if(IntegrationHelper.isLoadedFuture)
            PLACERS.add(LightPlacer_Actions::placeFire);
        if(IntegrationHelper.isLoadedQuark)
            PLACERS.add(LightPlacer_Actions::placeCrystal);
    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!ConfigHelper.topHell)
            return;
        if(world.provider.getDimension()!=-1)
            return;
        if(random.nextInt(5)!=0)
            return;
        BlockPos place = getPos(world,random,chunkX,chunkZ);
        if(place==null)
            return;
        PLACERS.get(random.nextInt(PLACERS.size()))
                .place(random,world, place);
    }


    // interface with one method accepts any static method with same signature
    public interface LightPlacer{
        void place(Random r, World w, BlockPos bp);
    }
}
