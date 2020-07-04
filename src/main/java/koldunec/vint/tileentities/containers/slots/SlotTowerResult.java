package koldunec.vint.tileentities.containers.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTowerResult extends Slot {
    private final EntityPlayer player;
    private int removeCount;

    public SlotTowerResult(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    public ItemStack decrStackSize(int amount) {
        if(getHasStack())
            removeCount += Math.min(amount, getStack().getCount());
        return super.decrStackSize(amount);
    }


    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        onCrafting(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }

    protected void onCrafting(ItemStack stack, int amount) {
        removeCount += amount;
        stack.onCrafting(player.world, player, removeCount);
        removeCount = 0;
    }

    protected void onCrafting(ItemStack stack) {
        stack.onCrafting(player.world, player, removeCount);
        removeCount = 0;
    }
}
