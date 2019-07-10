package koldunec.ammpdbm_mod.broomitems.baseItems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;

public class basic_planks extends Block
{
    public basic_planks(String name, float hardness, float hardness_expl)
    {
        super(Material.WOOD);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setHardness(hardness);
        this.setResistance(hardness_expl);
        this.blockSoundType = SoundType.WOOD;
        this.fullBlock = true;
        this.lightOpacity = 0;
    }



}