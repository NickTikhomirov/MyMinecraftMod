package koldunec.vint.items;

import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.objectbuilders.SimpleItems;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class Moss extends SimpleItems.SimpleItem {
    public static HashMap<IBlockState, IBlockState> transformations = new HashMap<IBlockState, IBlockState>(){{
        put(Blocks.COBBLESTONE.getDefaultState(),Blocks.MOSSY_COBBLESTONE.getDefaultState());
        put(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,BlockStoneBrick.EnumType.DEFAULT),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,BlockStoneBrick.EnumType.MOSSY));
        put(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,BlockStoneBrick.EnumType.CRACKED),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,BlockStoneBrick.EnumType.MOSSY));
    }};

    public static int getProbabilityBound(){
        return 100;
    }

    public Moss(){
        super("moss", 64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState state = worldIn.getBlockState(pos);
        IBlockState result = transformations.get(state);
        if(result==null)
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        worldIn.setBlockState(pos,result);
        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
