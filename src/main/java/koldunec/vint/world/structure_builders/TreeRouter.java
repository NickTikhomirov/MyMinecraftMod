package koldunec.vint.world.structure_builders;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.OtherIntegration.RandomThingsModule;
import koldunec.vint.compatibility.natura.NaturaModuleClass;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class TreeRouter {
    private static ArrayList<GenInfo> collectContents = new ArrayList<GenInfo>();
    private static TreeMap<Double, SingleTreeGen> selectContents = new TreeMap<>();

    public static final GenInfo DEFAULT = new GenInfo((World w, BlockPos f, Random r) -> TreeBuilder.BuildProperTree(w, f), 100);

    public static void init(){
        collectContents.add(DEFAULT);
        if(IntegrationHelper.isLoadedNatura)
            collectContents.add(new GenInfo(NaturaModuleClass.getGenerator(), 10));
        if(IntegrationHelper.isLoadedRandomThings)
            collectContents.add(new GenInfo(RandomThingsModule.getTreeGen(), 5));
    }

    public static void postInit(){
        double sum = 0;
        // we need to change summarised weight =1
        for(GenInfo g: collectContents)
            sum+=g.weight;
        for(GenInfo g: collectContents)
            g.weight/=sum;
        sum = 0;
        // fill sum fields
        for(GenInfo g: collectContents){
            g.sum = sum;
            sum +=g.weight;
        }
        // move to new house
        for(GenInfo g: collectContents)
            selectContents.put(g.sum, g.gen);
        collectContents = null;
    }


    public static void execute(World w, BlockPos firstBlock, Random random){
        selectContents.lowerEntry(random.nextDouble()).getValue().generate(w, firstBlock, random);
    }


    public interface SingleTreeGen{
        void generate(World w, BlockPos firstBlock, Random random);
    }

    public static class GenInfo{
        public SingleTreeGen gen;
        public double weight;
        public double sum;
        public GenInfo(SingleTreeGen i, double w){
            gen = i;
            weight = w;
        }
    }
}
