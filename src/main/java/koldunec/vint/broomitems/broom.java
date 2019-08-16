package koldunec.vint.broomitems;


import koldunec.vint.init.ItemRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class broom extends Item {

    public broom(String name) {
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.maxStackSize = 1;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 600;
    }


    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        if(stack.getItem().equals(ItemRegister.BROOM))
            if(state.getBlock().equals(Blocks.ENDER_CHEST)|state.getBlock().equals(Blocks.WEB))
                return 150.0F;
            else if(state.getBlock().equals(Blocks.BOOKSHELF)|state.getBlock().equals(Blocks.ENCHANTING_TABLE))
                return 75.0F;
            return 1.0F;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state)
    {
        if(
                state.getBlock().equals(Blocks.ENCHANTING_TABLE)
               |state.getBlock().equals(Blocks.WEB)
               |state.getBlock().equals(Blocks.BOOKSHELF)
               |state.getBlock().equals(Blocks.ENDER_CHEST)
        )
            return true;
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR && block == Blocks.GRASS)
            {
                IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
                worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!worldIn.isRemote)
                    worldIn.setBlockState(pos, iblockstate1, 11);
                return EnumActionResult.SUCCESS;

            } else if (block == Blocks.MONSTER_EGG) {
                IBlockState iblockstate1 = iblockstate;
                if(block.getMetaFromState(iblockstate)==0)iblockstate1 = Blocks.STONE.getDefaultState();
                if(block.getMetaFromState(iblockstate)==1)iblockstate1 = Blocks.COBBLESTONE.getDefaultState();
                if(block.getMetaFromState(iblockstate)==2)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(0);
                if(block.getMetaFromState(iblockstate)==3)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(1);
                if(block.getMetaFromState(iblockstate)==4)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(2);
                if(block.getMetaFromState(iblockstate)==5)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(3);
                worldIn.playSound(player, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!worldIn.isRemote)
                    worldIn.setBlockState(pos, iblockstate1, 11);

                return EnumActionResult.SUCCESS;
            } else
            {
                return EnumActionResult.PASS;
            }
        }
    }

}
