package koldunec.vint.items;

import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class bitore extends BlockOre
{
    Item drops;
    int am;
    int aM;
    int AM;
    public bitore (String name,int amountMin, int amountMax, int amountMaxWithFortune)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setHarvestLevel("pickaxe",1);
        this.setHardness(1F);
        this.setResistance(50.0F);
        am = amountMin;
        aM = amountMax;
        AM = amountMaxWithFortune;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        if(this==BlockRegister.ORE_ALUMINUM)
            return ItemRegister.ALUMINUM;
        else if(this==BlockRegister.ORE_BIT)
            return ItemRegister.BITCOIN;
        return ItemRegister.FISHY;
    }

    @Override
    public int quantityDropped(Random random)
    {
        int f = am + random.nextInt(aM-am+1);
        return (f>aM)?aM:f;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {

        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)) {

            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {

                i = 0;

            }
            int f = this.quantityDropped(random) * (i + 1);
            return (f>AM)?AM:f;

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
            i = MathHelper.getInt(rand, 2, 4); //Количество опыта (от 0 до 2)
            return i;

        }

        return 0;

    }

}