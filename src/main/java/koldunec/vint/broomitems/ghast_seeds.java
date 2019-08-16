package koldunec.vint.broomitems;

import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ghast_seeds extends ItemSeeds {

    public ghast_seeds(){
        super(BlockRegister.GHAST_POD, Blocks.OBSIDIAN);
        setRegistryName("ghast_seeds");
        setUnlocalizedName("ghast_seeds");
        setCreativeTab(vint.magicTab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.getBlockState(pos).getBlock().equals(Blocks.OBSIDIAN)){
            ItemStack itemstack = player.getHeldItem(hand);
            if (facing == EnumFacing.DOWN || facing == EnumFacing.UP)
            {
                return EnumActionResult.FAIL;
            }
            pos = pos.offset(facing);

            if (worldIn.isAirBlock(pos))
            {
                IBlockState iblockstate1 = BlockRegister.GHAST_POD.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);
                worldIn.setBlockState(pos, iblockstate1, 10);

                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }
}
