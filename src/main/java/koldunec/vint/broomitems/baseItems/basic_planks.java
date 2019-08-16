package koldunec.vint.broomitems.baseItems;

import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class basic_planks extends Block
{
    public basic_planks(String name, float hardness, float hardness_expl)
    {
        super(Material.WOOD);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setHardness(hardness);
        this.setResistance(hardness_expl);
        this.blockSoundType = SoundType.WOOD;
        this.fullBlock = true;
        this.lightOpacity = 255;
    }



}