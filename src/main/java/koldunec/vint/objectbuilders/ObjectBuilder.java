package koldunec.vint.objectbuilders;

import koldunec.vint.init.others.MaterialRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ObjectBuilder {

    public static class SimpleBlock extends Block{
        private boolean flammable = false;
        private boolean anytool = false;

        public SimpleBlock(String name, Material blockMaterialIn, SoundType soundType) {
            super(blockMaterialIn);
            setSoundType(soundType);
            setCreativeTab(vint.magicTab);
            setRegistryName(name);
            setUnlocalizedName(name);
        }

        @Override
        public boolean isToolEffective(String type, IBlockState state) {
            if(anytool)
                return true;
            return super.isToolEffective(type, state);
        }

        @Override
        public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
            return flammable?20:0;
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

    public static Block BuildShroomLight(){
        Block result = new SimpleBlock("shroomlight",Material.PLANTS, SoundType.SLIME);
        result.setLightLevel(1);
        result.setHardness(0.5F);
        result.setResistance(10F);
        return result;
    }

    public static Block BuildDefaultPlanks(String name, boolean flammableParam){
        SimpleBlock block = new SimpleBlock(name, Material.WOOD, SoundType.WOOD);
        block.setHardness(2.0F);
        block.setResistance(5.0F);
        block.flammable = flammableParam;
        return block;
    }

    public static Block BuildFake(String name){
        SimpleBlock block = new SimpleBlock(name, Material.IRON, SoundType.METAL);
        block.anytool = true;
        block.setHardness(0.8F);
        return block;
    }

    public static Block BuildRock(String s){
        SimpleBlock result = new SimpleBlock(s, Material.ROCK, SoundType.STONE);
        result.setHardness(2F);
        result.setResistance(10F);
        return result;
    }

    //---------------
    // ____Items_____
    //---------------

    public static Item BuildBambooHoe(){
        ItemHoe i = new ItemHoe(MaterialRegister.BAMBOO_MATERIAL);
        i.setMaxStackSize(1);
        i.setMaxDamage(100);
        i.setUnlocalizedName("bamboo_hoe");
        i.setRegistryName("bamboo_hoe");
        i.setCreativeTab(vint.magicTab);
        return i;
    }


}
