package koldunec.vint.slots;


import koldunec.vint.ammpdbm_mod;
import koldunec.vint.init.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import twilightforest.item.ItemTFTransformPowder;

public class Slot_TowerFuel_1 extends Slot {
    public Slot_TowerFuel_1(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        if(ammpdbm_mod.isLoadedTwilight){
            if(stack.getItem().equals(ItemRegister.TRANSFORMATION_DUST)) return true;
        }
        return false;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 64;
    }

}
