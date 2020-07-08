package koldunec.vint.tileentities.containers;

import koldunec.vint.recipes.TwilightTransmutations.FuelHelper;
import koldunec.vint.tileentities.containers.slots.SlotTowerFuel;
import koldunec.vint.tileentities.containers.slots.SlotTowerResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

public class ContainerTower extends Container {
    private final IInventory tileTower;
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    public static HashMap<Item, Integer> CATALYSTS_FOR_TRANSFER = new HashMap<Item, Integer>(){{
        put(Items.DRAGON_BREATH, 0);
    }};

    public ContainerTower(InventoryPlayer playerInventory, IInventory furnaceInventory) {
        tileTower = furnaceInventory;
        addSlotToContainer(new Slot(furnaceInventory, 0, 56, 35));
        addSlotToContainer(new Slot(furnaceInventory, 1, 36, 35));
        addSlotToContainer(new SlotTowerFuel(furnaceInventory, 2, 150, 61));
        addSlotToContainer(new SlotTowerResult(playerInventory.player, furnaceInventory, 3, 116, 35));
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (int k = 0; k < 9; ++k)
            addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
    }

    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileTower);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);
            if (this.cookTime != this.tileTower.getField(2))
                icontainerlistener.sendWindowProperty(this, 2, this.tileTower.getField(2));
            if (this.furnaceBurnTime != this.tileTower.getField(0))
                icontainerlistener.sendWindowProperty(this, 0, this.tileTower.getField(0));
            if (this.currentItemBurnTime != this.tileTower.getField(1))
                icontainerlistener.sendWindowProperty(this, 1, this.tileTower.getField(1));
            if (this.totalCookTime != this.tileTower.getField(3))
                icontainerlistener.sendWindowProperty(this, 3, this.tileTower.getField(3));
        }
        cookTime = tileTower.getField(2);
        furnaceBurnTime = tileTower.getField(0);
        currentItemBurnTime = tileTower.getField(1);
        totalCookTime = tileTower.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) { tileTower.setField(id, data); }

    public boolean canInteractWith(EntityPlayer playerIn) { return tileTower.isUsableByPlayer(playerIn); }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 3) {
                if (!mergeItemStack(itemstack1, 4, 40, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0 && index != 2) {
                Integer result = CATALYSTS_FOR_TRANSFER.get(itemstack1.getItem());
                if(
                        result!=null &&
                        (result.equals(itemstack1.getMetadata()) || result.equals(-1))  &&
                        !mergeItemStack(itemstack1, 1, 2, false)
                )
                    return ItemStack.EMPTY;
                if (FuelHelper.IsValidFuel(itemstack1))
                    if (!mergeItemStack(itemstack1, 2, 3, false))
                        return ItemStack.EMPTY;
                if (!mergeItemStack(itemstack1, 0, 1, false))
                    return ItemStack.EMPTY;
                if (index < 31) {
                    if (!mergeItemStack(itemstack1, 31, 40, false))
                        return ItemStack.EMPTY;
                } else if (index < 40 && !mergeItemStack(itemstack1, 4, 31, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(itemstack1, 4, 40, false))
                return ItemStack.EMPTY;
            if (itemstack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }
}
