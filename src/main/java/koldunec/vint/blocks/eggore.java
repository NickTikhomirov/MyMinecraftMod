package koldunec.vint.blocks;

import koldunec.vint.vint;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class eggore extends BlockOre
{
    public eggore(String name, int stackMAX)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setHarvestLevel("pickaxe",1);
        this.setHardness(1F);
        this.setResistance(50.0F);
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        return Items.EGG;
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {

        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int i = random.nextInt(fortune + 2) - 1;
            if (i < 0)
                i = 0;
            return this.quantityDropped(random) * (i + 1);
        } else return this.quantityDropped(random)+500;
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