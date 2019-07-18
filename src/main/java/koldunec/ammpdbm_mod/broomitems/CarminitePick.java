package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.toolmaterials.magicCarminite;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import twilightforest.block.BlockTFDarkLeaves;
import twilightforest.block.BlockTFMazestone;
import twilightforest.block.BlockTFTowerDevice;
import twilightforest.block.BlockTFTowerWood;
import twilightforest.item.TFItems;

public class CarminitePick extends ItemPickaxe {
    public CarminitePick() {
        super(magicCarminite.magicCarminite);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setRegistryName("carminite_pick");
        this.setUnlocalizedName("carminite_pick");
        this.setMaxStackSize(1);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(ammpdbm_mod.isLoadedTwilight){
            if(state.getBlock() instanceof BlockTFMazestone) {
                return 144F;
            }
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if(ammpdbm_mod.isLoadedTwilight)
            if(repair.getItem().equals(TFItems.carminite)) return true;
        return false;
    }
}
