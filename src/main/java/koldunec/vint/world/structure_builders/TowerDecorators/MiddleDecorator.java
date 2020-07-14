package koldunec.vint.world.structure_builders.TowerDecorators;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.OtherIntegration.QuarkInt;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.world.structure_builders.NetherTowerBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.worldgen.SlimeTreeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MiddleDecorator {
    static ArrayList<IBlockState> tinker_defaults;
    static int true_top = 150;

    public static ArrayList<TopDecorator.Decorator> Builders = new ArrayList<>();


    public static void execute(Random random, int top, Chunk ch, IBlockState side){
        Builders.get(random.nextInt(Builders.size())).decorate(random,top,ch, side);
    }

    public static void init(){
        Builders.add(MiddleDecorator::Full);
        if(IntegrationHelper.isLoadedTinkers){
            tinker_defaults = new ArrayList<IBlockState>(){{
                add(Block.getBlockFromName(IntegrationHelper.idTinker + ":slime_grass_tall").getStateFromMeta(8));
                add(Block.getBlockFromName(IntegrationHelper.idTinker + ":slime_grass_tall").getStateFromMeta(9));
            }};
            tinker_defaults.add(tinker_defaults.get(0));
            Builders.add(MiddleDecorator::TinkersSlime);
        }
        if(IntegrationHelper.isLoadedQuark){
            Builders.add(MiddleDecorator::QuarkGlowGarden);
        }
    }

    public static void Full(Random random, int top, Chunk ch, IBlockState side){
        for(int i = top; i<true_top; ++i)
            NetherTowerBuilder.fillSlice(ch,i, side);
    }

    public static void FullFake(Random random, int top, Chunk ch, IBlockState side){
        IBlockState fake = random.nextBoolean()?BlockRegister.FAKE_DIAMOND.getDefaultState():BlockRegister.FAKE_GOLD.getDefaultState();
        for(int i = top; i<true_top; ++i)
            NetherTowerBuilder.fillCirle(ch,i, side, fake);
    }


    public static void TinkersSlime(Random random, int top, Chunk ch, IBlockState side) {
        IBlockState ground = Block.getBlockFromName(IntegrationHelper.idTinker + ":slime_dirt").getStateFromMeta(3);
        IBlockState grass = Block.getBlockFromName(IntegrationHelper.idTinker + ":slime_grass").getStateFromMeta(14);
        ProtoGarden(ch, top, ground, grass, side);
        int x = random.nextBoolean() ? 6 : 8;
        int z = random.nextBoolean() ? 6 : 8;
        SlimeTreeGenerator gen = new SlimeTreeGenerator(
                5,
                4,
                TinkerCommons.blockSlimeCongealed.func_176203_a(4),
                TinkerWorld.slimeLeaves.func_176203_a(2),
                null);
        gen.generateTree(random, ch.getWorld(), new BlockPos((ch.x << 4) + x, top+4, (ch.z << 4) + z));
        if(tinker_defaults!=null)
            Planter(ch, top+4, random, tinker_defaults, 12);
        Planter(ch, top+4, random, BlockRegister.SHROOMLIGHT.getDefaultState(), 1);
    }

    public static void QuarkGlowGarden(Random random, int top, Chunk ch, IBlockState side){
        IBlockState ground = Blocks.DIRT.getDefaultState();
        IBlockState grass = Block.getBlockFromName("quark:glowcelium").getDefaultState();
        IBlockState shroom = Block.getBlockFromName("quark:glowshroom").getDefaultState();
        ProtoGarden(ch, top, ground, grass, side);
        QuarkInt.ShroomBuilder(ch, 7, top+4, 7);
        Planter(ch, top+4, random, shroom, 6);
    }




    public static void ProtoGarden(Chunk ch, int top, IBlockState grassless_ground, IBlockState grass, IBlockState side){
        for (int y = top; y < true_top-1; ++y) {
            NetherTowerBuilder.fillCirle(ch, y, side, grassless_ground);
            if (y == top + 2)
                grassless_ground = grass;
            if (y == top + 3)
                grassless_ground = Blocks.AIR.getDefaultState();
        }
        top = true_top-1;
        NetherTowerBuilder.fillSlice(ch, top, side);
    }

    public static void Planter(Chunk ch, int height, Random random, List<IBlockState> variants, int amount){
        BlockPos offset = new BlockPos(7, height, 7);
        for(int i=0; i<amount; ++i){  //counter
            IBlockState variant = variants.get(random.nextInt(variants.size()));
            for(int j=0; j<3; ++j){  // attempts
                BlockPos pos = offset.add(random.nextInt(9)-4,0, random.nextInt(9)-4);
                if(!ch.getBlockState(pos).getBlock().equals(Blocks.AIR))
                    continue;
                ch.setBlockState(pos, variant);
                break;
            }
        }
    }

    public static void Planter(Chunk ch, int height, Random random, IBlockState variant, int amount){
        BlockPos offset = new BlockPos(7, height, 7);
        for(int i=0; i<amount; ++i){  //counter
            for(int j=0; j<3; ++j){  // attempts
                BlockPos pos = offset.add(random.nextInt(9)-4,0, random.nextInt(9)-4);
                if(!ch.getBlockState(pos).getBlock().equals(Blocks.AIR))
                    continue;
                ch.setBlockState(pos, variant);
                break;
            }
        }
    }
}
