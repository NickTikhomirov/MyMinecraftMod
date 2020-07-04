package koldunec.vint.tileentities.containers.slots;

import koldunec.vint.recipes.TwilightTransmutations.FuelHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTowerFuel extends Slot {
    public SlotTowerFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return FuelHelper.IsValidFuel(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return stack.getMaxStackSize();
    }
}
