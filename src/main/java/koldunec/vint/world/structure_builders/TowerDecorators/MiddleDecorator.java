package koldunec.vint.world.structure_builders.TowerDecorators;

import koldunec.vint.IntegrationHelper;
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
import java.util.Random;

public class MiddleDecorator {
    static int true_top = 150;

    public static ArrayList<TopDecorator.Decorator> Builders = new ArrayList<>();

    public static void init(){
        Builders.add(MiddleDecorator::Full);
        if(IntegrationHelper.isLoadedTinkers){
            Builders.add(MiddleDecorator::TinkersSlime);
        }
    }

    public static void Full(Random random, int top, Chunk ch){
        for(int i = top; i<true_top; ++i){
            NetherTowerBuilder.fillSlice(ch,i, BlockRegister.FRESH_DEBRIS.getDefaultState());
        }
    }


    public static void TinkersSlime(Random random, int top, Chunk ch) {
        IBlockState temp = Block.getBlockFromName(IntegrationHelper.idTinker + ":slime_dirt").getStateFromMeta(3);
        for (int y = top; y < 149; ++y) {
            NetherTowerBuilder.fillCirle(ch, y, BlockRegister.FRESH_DEBRIS.getDefaultState(), temp);
            if (y == top + 2)
                temp = Block.getBlockFromName(IntegrationHelper.idTinker + ":slime_grass").getStateFromMeta(14);
            if (y == top + 3)
                temp = Blocks.AIR.getDefaultState();
        }
        top = 150;
        NetherTowerBuilder.fillSlice(ch, 149, BlockRegister.FRESH_DEBRIS.getDefaultState());
        int x = random.nextBoolean() ? 6 : 8;
        int z = random.nextBoolean() ? 6 : 8;
        SlimeTreeGenerator gen = new SlimeTreeGenerator(
                5,
                4,
                TinkerCommons.blockSlimeCongealed.func_176203_a(4),
                TinkerWorld.slimeLeaves.func_176203_a(2),
                null);
        gen.generateTree(random, ch.getWorld(), new BlockPos((ch.x << 4) + x, 135, (ch.z << 4) + z));
    }
}
