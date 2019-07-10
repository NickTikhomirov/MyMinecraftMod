package koldunec.ammpdbm_mod.tileentities;


import koldunec.ammpdbm_mod.proxy.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import twilightforest.item.ItemTFTransformPowder;

public class Slot_TowerFuel extends Slot {
    public Slot_TowerFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest")){
            if(stack.getItem() instanceof ItemTFTransformPowder) return true;
            if(stack.getItem().equals(ItemRegister.TRANSFORMATION_DUST)) return true;
        }
        return false;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 64;
    }

}
