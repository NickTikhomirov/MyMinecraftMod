package koldunec.vint.world.structure_builders.TowerDecorators;

import com.progwml6.natura.nether.NaturaNether;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.world.structure_builders.NetherTowerBuilder;
import koldunec.vint.world.structure_builders.TreeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Random;

public class TopDecorator {
    public static ArrayList<Decorator> Builders = new ArrayList<>();

    public static void init(){
        Builders.add(TopDecorator::Empty);
        Builders.add(TopDecorator::Garden);
        if(IntegrationHelper.isLoadedQuark){
            Builders.add(TopDecorator::BlazeBeacon);
        }
    }

    public static void execute(Random random, int top, Chunk ch, IBlockState side){
        Builders.get(random.nextInt(Builders.size())).decorate(random,top,ch, side);
    }

    public static void Empty(Random random, int top, Chunk ch, IBlockState side){}

    public static void BlazeBeacon(Random random, int top, Chunk ch, IBlockState side){
        for(int y=top;y<154; ++y)
            NetherTowerBuilder.fillSlice(ch,y, Block.getBlockFromName("quark:blaze_lantern").getDefaultState());
    }

    public static void Garden(Random random, int top, Chunk ch, IBlockState side){
        NetherTowerBuilder.fillCirle(ch, top, side, BlockRegister.GREEN_NYLIUM.getDefaultState());
        NetherTowerBuilder.fillCirle(ch, top+1, side, Blocks.AIR.getDefaultState());
        BlockPos pos = new BlockPos(
                (ch.x<<4)+random.nextInt(5)+4 ,
                top+1,
                (ch.z<<4)+random.nextInt(5)+4);
        TreeBuilder.BuildNetherTree(ch.getWorld(), pos, BlockRegister.GREEN_NY_LOG, BlockRegister.GREEN_WART.getDefaultState());
        MiddleDecorator.Planter(ch, top+1, random, NaturaNether.netherGlowshroom.getStateFromMeta(0), 6);
    }

    public interface Decorator{
        void decorate(Random random, int top, Chunk ch, IBlockState side);
    }
}
