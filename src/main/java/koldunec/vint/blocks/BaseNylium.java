package koldunec.vint.blocks;

import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
}
