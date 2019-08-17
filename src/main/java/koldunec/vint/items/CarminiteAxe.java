package koldunec.vint.items;

import koldunec.vint.init.MaterialRegister;
import koldunec.vint.vint;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import twilightforest.block.*;
import twilightforest.item.TFItems;

public class CarminiteAxe extends ItemAxe {
    public CarminiteAxe() {
        super(MaterialRegister.magicCarminite, 8.5F, -3.1F);
        this.setCreativeTab(vint.magicTab);
        this.setRegistryName("carminite_axe");
        this.setUnlocalizedName("carminite_axe");
        this.setMaxStackSize(1);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(vint.isLoadedTwilight){
            if(state.getBlock() instanceof BlockTFTowerWood ||
               state.getBlock() instanceof BlockTFTowerDevice ||
               state.getBlock() instanceof BlockTFDarkLeaves ||
               state.getBlock() instanceof BlockTFHedge){
                    //|| (state.getBlock() instanceof BlockTFLog && state.getBlock().getMetaFromState(state)==3)){
                return 48F;
            }
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if(vint.isLoadedTwilight)
            if(repair.getItem().equals(TFItems.carminite)) return true;
        return false;
    }
}
