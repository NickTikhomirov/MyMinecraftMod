package koldunec.vint.world.nether;

import koldunec.vint.helpers.ConfigHelper;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.world.DecorationGenerator;
import koldunec.vint.world.nether.LightPlacers.Crystalplacer;
import koldunec.vint.world.nether.LightPlacers.Fireplacer;
import koldunec.vint.world.nether.LightPlacers.PumpkinPlacer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;

import java.util.ArrayList;
import java.util.Random;

public class NetherLightGenerator extends DecorationGenerator {

    static LightPlacer Pumpkiner = new PumpkinPlacer();

    private static ArrayList<LightPlacer> PLACERS = new ArrayList<LightPlacer>(){{
        add(Pumpkiner);
        add(Pumpkiner);
        add(Pumpkiner);
        add(new EmptyPlacer());
    }};

    public NetherLightGenerator(){
        if(IntegrationHelper.isLoadedFuture)
            PLACERS.add(new Fireplacer());
        if(IntegrationHelper.isLoadedQuark)
            PLACERS.add(new Crystalplacer());
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


    abstract public static class LightPlacer{
        abstract public void place(Random r, World w, BlockPos bp);
    }

    public static class EmptyPlacer extends LightPlacer{
        @Override
        public void place(Random r, World w, BlockPos bp) {}
    }
}
