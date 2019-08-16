package koldunec.vint.broomitems;

import koldunec.vint.init.ItemRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class rainbow_ore extends Block
{
    Item drops;
    int am;
    int aM;
    int AM;
    public rainbow_ore(String name, int amountMin, int amountMax, int amountMaxWithFortune)
    {
        super(Material.GROUND);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setHardness(0.2F);
        this.setResistance(Blocks.DIRT.getExplosionResistance(null,null,null,null));
        this.setSoundType(SoundType.GROUND);
        am = amountMin;
        aM = amountMax;
        AM = amountMaxWithFortune;
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemRegister.ESSENCE_RAINBOW;
    }

    public int quantityDropped(Random random)
    {
        int f = am + random.nextInt(aM-am+1);
        return (f>aM)?aM:f;
    }


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
            i = MathHelper.getInt(rand, 0, 2); //Количество опыта (от 0 до 2)
            return i;

        }

        return 0;

    }

}