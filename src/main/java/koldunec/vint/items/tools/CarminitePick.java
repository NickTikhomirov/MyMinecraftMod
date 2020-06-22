package koldunec.vint.items.tools;

import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import koldunec.vint.init.others.MaterialRegister;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import twilightforest.block.BlockTFMazestone;
import twilightforest.item.TFItems;

public class CarminitePick extends ItemPickaxe {
    public CarminitePick() {
        super(MaterialRegister.magicCarminite);
        this.setCreativeTab(vint.magicTab);
        this.setRegistryName("carminite_pick");
        this.setUnlocalizedName("carminite_pick");
        this.setMaxStackSize(1);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(IntegrationHelper.isLoadedTwilight){
            if(state.getBlock() instanceof BlockTFMazestone) {
                return 144F;
            }
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if(IntegrationHelper.isLoadedTwilight)
            if(repair.getItem().equals(TFItems.carminite)) return true;
        return false;
    }
}
