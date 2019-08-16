package koldunec.vint.slots;


import koldunec.vint.ammpdbm_mod;
import koldunec.vint.init.ItemRegister;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import twilightforest.item.ItemTFMoonwormQueen;
import twilightforest.item.ItemTFTransformPowder;

public class Slot_TowerCatalyst extends Slot {
    public Slot_TowerCatalyst(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }


    public boolean isItemValid(ItemStack stack)
    {
        if(ammpdbm_mod.isLoadedTwilight){
            if(stack.getItem().equals(ItemRegister.ESSENCE_RAINBOW)) return true;
            if(stack.getItem().equals(Items.GOLD_INGOT)) return true;
            if(stack.getItem().equals(Items.DIAMOND)) return true;
            if(stack.getItem().equals(Items.EMERALD)) return true;
            if(stack.getItem().getUnlocalizedName().equals("carminite")) return true;
            if(stack.getItem().getUnlocalizedName().equals("nagaScale")) return true;
            if(stack.getItem() instanceof ItemTFMoonwormQueen) return true;
            if(stack.getItem() instanceof ItemTFTransformPowder) return true;

        }
        return false;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 1;
    }

}
