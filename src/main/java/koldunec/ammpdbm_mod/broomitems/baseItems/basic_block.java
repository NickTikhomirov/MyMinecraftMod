package koldunec.ammpdbm_mod.broomitems.baseItems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;

public class basic_block extends Block
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public basic_block(String name, Material m, String toolCl, int lvlHarvest, float hardness, float hardness_expl, int opacity)
    {
        super(m);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setHarvestLevel(toolCl,lvlHarvest);
        this.setHardness(hardness);
        this.setResistance(hardness_expl);
        this.fullBlock = true;
        this.lightOpacity = opacity;
    }




}