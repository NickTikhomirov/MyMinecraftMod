package koldunec.vint.world.structure_builders.TowerDecorators;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.ArrayList;

public class SideDecorator {

    public static ArrayList<IBlockState> VARIANTS = new ArrayList<IBlockState>(){{
        add(BlockRegister.FRESH_DEBRIS.getDefaultState());
        add(BlockRegister.BASALT_RAW.getDefaultState());
        add(Blocks.RED_NETHER_BRICK.getDefaultState());
    }};

    public static void init(){
        if(IntegrationHelper.isLoadedRandomThings){
            VARIANTS.add(Block.getBlockFromName("randomthings:luminousblock").getStateFromMeta(9));
        }
    }
}
