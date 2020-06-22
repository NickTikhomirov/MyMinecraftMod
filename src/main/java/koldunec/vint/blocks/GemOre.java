package koldunec.vint.blocks;

import koldunec.vint.utils.GemHelper;
import koldunec.vint.objectbuilders.ObjectBuilder;
import koldunec.vint.vint;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class GemOre extends ObjectBuilder.SimpleBlock {
    public GemOre() {
        super("gem_ore", Material.ROCK, SoundType.STONE);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        if(!GemHelper.isAllowed())
            return;
        Random random;
        if(world instanceof World)
            random = ((World) world).rand;
        else
            random = vint.random;
        drops.addAll(GemHelper.getDrops(random));
    }
}
