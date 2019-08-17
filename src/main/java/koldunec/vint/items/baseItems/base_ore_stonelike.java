package koldunec.vint.items.baseItems;

import koldunec.vint.vint;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class base_ore_stonelike extends BlockOre {
    Item drops;
    int am;
    int aM;
    int AM;
    public base_ore_stonelike (String name, int level, float hard, float resis,Item drop, int amountMin, int amountMax, int amountMaxWithFortune)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setHarvestLevel("pickaxe",level);
        this.setHardness(hard);
        this.setResistance(resis);
        drops = drop;
        am = amountMin;
        aM = amountMax;
        AM = amountMaxWithFortune;
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return drops;
    }

    public int quantityDropped(Random random)
    {
        int f = am + random.nextInt(aM-am+1);
        return Math.min(aM,f);
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int i = random.nextInt(fortune + 2) - 1;
            if (i < 0) {
                i = 0;
            }
            int f = this.quantityDropped(random) * (i + 1);
            return Math.min(AM,f);
        }
        else {
            return this.quantityDropped(random);
        }
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
