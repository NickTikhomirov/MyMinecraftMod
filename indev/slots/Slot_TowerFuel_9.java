package koldunec.ammpdbm_mod.slots;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import twilightforest.item.ItemTFTransformPowder;

public class Slot_TowerFuel_9 extends Slot {
    public Slot_TowerFuel_9(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        if(ammpdbm_mod.isLoadedTwilight){
            if(stack.getItem() instanceof ItemTFTransformPowder) return true;
        }
        return false;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 64;
    }

}
