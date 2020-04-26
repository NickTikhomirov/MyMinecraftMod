package koldunec.vint.blocks;

import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ShroomLight extends Block {
    public ShroomLight(){
        super(Material.PLANTS);
        setCreativeTab(vint.magicTab);
        setRegistryName("shroomlight");
        setUnlocalizedName("shroomlight");
        lightValue=15;
        setHarvestLevel("axe",0);
        setHardness(0.5F);
        setResistance(10F);
        setSoundType(SoundType.SLIME);
    }
}
