package koldunec.vint.blocks;

import koldunec.vint.items.baseItems.basic_block;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BaseNylium extends Block implements IGrowable {
    public BaseNylium(String name){
        super(Material.GRASS);
        setTickRandomly(true);
        setCreativeTab(vint.magicTab);
        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.GROUND);
        setHarvestLevel("shovel",0);
        setHardness(0.5F);
        setResistance(10F);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        if(plantable instanceof Block)
            if(((Block)plantable).getRegistryName().getResourceDomain().equals("natura"))
                return true;
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {

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
