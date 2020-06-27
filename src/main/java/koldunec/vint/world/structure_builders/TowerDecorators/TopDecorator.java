package koldunec.vint.world.structure_builders.TowerDecorators;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.world.structure_builders.NetherTowerBuilder;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Random;

public class TopDecorator {
    public static ArrayList<Decorator> Builders = new ArrayList<>();

    public static void init(){
        Builders.add(TopDecorator::Empty);
        if(IntegrationHelper.isLoadedQuark){
            Builders.add(TopDecorator::BlazeBeacon);
        }
    }

    public static void execute(Random random, int top, Chunk ch){
        Builders.get(random.nextInt(Builders.size())).decorate(random,top,ch);
    }

    public static void Empty(Random random, int top, Chunk ch){}

    public static void BlazeBeacon(Random random, int top, Chunk ch){
        for(int y=top;y<154; ++y)
            NetherTowerBuilder.fillSlice(ch,y, Block.getBlockFromName("quark:blaze_lantern").getDefaultState());
    }

    public interface Decorator{
        void decorate(Random random, int top, Chunk ch);
    }
}
