package koldunec.vint.items;

import koldunec.vint.init.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class block_ghastpod extends BlockCocoa {
    public block_ghastpod(){
        super();
        this.setUnlocalizedName("block_ghastpod");
        this.setRegistryName("block_ghastpod");
        this.setHardness(0.2F);
        this.setResistance(15F);
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        pos = pos.offset((EnumFacing)state.getValue(FACING));
        return isObsidian(worldIn,pos);
        //return super.canBlockStay(worldIn, pos, state);
    }

    public static boolean isObsidian(World w, BlockPos p){
        Block maybe_obsidian = w.getBlockState(p).getBlock();
        if(!maybe_obsidian.equals(Blocks.OBSIDIAN)) return false;
        return true;
    }

    public static boolean niceBase(World w, BlockPos b){
        if(!w.isAreaLoaded(b,1)) return false;
        if(w.getBlockState(b.down(1)).getBlock().equals(Blocks.PORTAL)) return true;
        if(w.getBlockState(b.up(1)).getBlock().equals(Blocks.PORTAL)) return true;
        if(w.getBlockState(b.north(1)).getBlock().equals(Blocks.PORTAL)) return true;
        if(w.getBlockState(b.south(1)).getBlock().equals(Blocks.PORTAL)) return true;
        if(w.getBlockState(b.east(1)).getBlock().equals(Blocks.PORTAL)) return true;
        if(w.getBlockState(b.west(1)).getBlock().equals(Blocks.PORTAL)) return true;
        return false;
    }

    @Override
    public boolean canGrow(World w, BlockPos p, IBlockState state, boolean isClient) {
        return niceBase(w,p.offset(state.getValue(FACING))) && super.canGrow(w, p, state, isClient);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!this.canBlockStay(worldIn, pos, state)){
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            dropBlockAsItem(worldIn, pos, state, 0);
        } else {
            int i = state.getValue(AGE).intValue();
            if (canGrow(worldIn,pos,state, worldIn.isRemote) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
                worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ItemRegister.GHAST_SEEDS,1);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int i = state.getValue(AGE).intValue();
        drops.add(new ItemStack(ItemRegister.GHAST_SEEDS));
        if (i >= 2)
            drops.add(new ItemStack(ItemRegister.SOUL_FRUIT));
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

}
