package koldunec.ammpdbm_mod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerTower extends Container
{
    private final IInventory tileTower;
    private int cookTime;
    private int totalCookTime;

    public ContainerTower(InventoryPlayer playerInventory, IInventory furnaceInventory)
    {
        this.tileTower = furnaceInventory;
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 54, 36));
        this.addSlotToContainer(new Slot_TowerFuel(furnaceInventory, 3, 56, 53));
        this.addSlotToContainer(new Slot_TowerFuel(furnaceInventory, 4, 151, 11));
        this.addSlotToContainer(new Slot_TowerCatalyst(furnaceInventory, 2, 151, 30));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, furnaceInventory, 1, 100, 32));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileTower);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileTower.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.tileTower.getField(0));
            }

            if (this.totalCookTime != this.tileTower.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.tileTower.getField(1));
            }
        }

        this.cookTime = this.tileTower.getField(0);
        this.totalCookTime = this.tileTower.getField(1);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileTower.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileTower.isUsableByPlayer(playerIn);
    }

}