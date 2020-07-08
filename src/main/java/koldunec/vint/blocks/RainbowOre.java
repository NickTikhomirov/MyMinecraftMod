package koldunec.vint.blocks;

import koldunec.vint.init.ItemRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class RainbowOre extends Block {
    public RainbowOre(String name) {
        super(Material.GROUND);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setHardness(0.2F);
        this.setSoundType(SoundType.GROUND);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(Items.DYE, 1));
        drops.add(new ItemStack(Items.DYE, 14));
        drops.add(new ItemStack(Items.DYE, 11));
        drops.add(new ItemStack(ItemRegister.ANOTHER_DYE, 1));
        drops.add(new ItemStack(Items.DYE, 12));
        drops.add(new ItemStack(ItemRegister.ANOTHER_DYE, 3));
        drops.add(new ItemStack(Items.DYE, 5));
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) { //Дроп опыта при добыче блока (если нужно)

        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {

            int i = 0;
            i = MathHelper.getInt(rand, 0, 2); //Количество опыта (от 0 до 2)
            return i;

        }

        return 0;

    }

}