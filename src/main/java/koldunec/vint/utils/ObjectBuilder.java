package koldunec.vint.utils;

import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ObjectBuilder {

    public static class SimpleBlock extends Block{
        private boolean flammable = false;

        public SimpleBlock(String name, Material blockMaterialIn, SoundType soundType) {
            super(blockMaterialIn);
            setSoundType(soundType);
            setCreativeTab(vint.magicTab);
            setRegistryName(name);
            setUnlocalizedName(name);
        }

        @Override
        public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
            return flammable?20:0;
        }

        public static Block BuildDefaultPlanks(String name, boolean flammableParam){
            SimpleBlock block = new SimpleBlock(name, Material.WOOD, SoundType.WOOD);
            block.setHardness(2.0F);
            block.setResistance(5.0F);
            block.flammable = flammableParam;
            return block;
        }
    }

    public static Block BuildWarpedWart(){
        IBlockState mainstate = Blocks.NETHER_WART.getDefaultState();
        Block result = new Block(mainstate.getMaterial());
        result.setRegistryName("warped_wart");
        result.setUnlocalizedName("warped_wart");
        result.setCreativeTab(vint.magicTab);
        result.setHardness(0.5F);
        result.setResistance(10F);
        result.setLightOpacity(255);
        return result;
    }



}
