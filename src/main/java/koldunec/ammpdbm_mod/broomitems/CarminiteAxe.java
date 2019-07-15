package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import koldunec.ammpdbm_mod.toolmaterials.magicCarminite;
import net.minecraft.item.ItemStack;
import twilightforest.block.*;
import twilightforest.enums.WoodVariant;

public class CarminiteAxe extends ItemAxe {
    public CarminiteAxe() {
        super(magicCarminite.magicCarminite, 8.5F, -3.1F);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setRegistryName("carminite_axe");
        this.setUnlocalizedName("carminite_axe");
        this.setMaxStackSize(1);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(ammpdbm_mod.isLoadedTwilight){
            if(state.getBlock() instanceof BlockTFTowerWood ||
               state.getBlock() instanceof BlockTFTowerDevice ||
               state.getBlock() instanceof BlockTFDarkLeaves ){
                    //|| (state.getBlock() instanceof BlockTFLog && state.getBlock().getMetaFromState(state)==3)){
                return 48F;
            }
        }
        return super.getDestroySpeed(stack, state);
    }
}
