package koldunec.vint.tileentities;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.blocks.Dryer;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.tileentities.containers.ContainerDryer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/*
   MOSTLY COPY-PASTED FROM VANILLA FURNACE
   seriously, i need only recipe replacements
 */

public class TileEntityDryer extends TileEntityLockable implements ITickable, ISidedInventory {
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};
    /** The ItemStacks that hold the items currently being used in the dryer */
    private NonNullList<ItemStack> dryerItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    /** The number of ticks that the furnace will keep burning */
    private int dryerBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the dryer burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;

    public static ItemStack getResult(ItemStack i){
        if(IntegrationHelper.isLoadedTinkers)
            return TinkerIntegration.getDrying(i);
        return ItemStack.EMPTY;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return this.dryerItemStacks.size();
    }

    public boolean isEmpty() {
        for (ItemStack itemstack : this.dryerItemStacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.dryerItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.dryerItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.dryerItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.dryerItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.dryerItemStacks.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag) {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName() {
        return "vint.container.dryer";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName() {
        return false;
    }



    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        dryerItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, dryerItemStacks);
        dryerBurnTime = compound.getInteger("BurnTime");
        cookTime = compound.getInteger("CookTime");
        totalCookTime = compound.getInteger("CookTimeTotal");
        currentItemBurnTime = getItemBurnTime(dryerItemStacks.get(1));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.dryerBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.dryerItemStacks);
        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.dryerBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.getField(0) > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update() {
        if(!IntegrationHelper.isLoadedTinkers)
            return;
        boolean flag = isBurning();
        boolean flag1 = false;
        if (this.isBurning())
            --dryerBurnTime;
        if (!this.world.isRemote) {
            ItemStack itemstack = dryerItemStacks.get(1);
            if (isBurning() || !itemstack.isEmpty() && !(dryerItemStacks.get(0)).isEmpty()) {
                if (!isBurning() && canSmelt()) {
                    dryerBurnTime = getItemBurnTime(itemstack);
                    currentItemBurnTime = dryerBurnTime;
                    if (isBurning()) {
                        flag1 = true;
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                dryerItemStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (isBurning() && canSmelt()) {
                    ++cookTime;
                    if (cookTime == totalCookTime) {
                        cookTime = 0;
                        totalCookTime = getCookTime(dryerItemStacks.get(0));
                        smeltItem();
                        flag1 = true;
                    }
                } else cookTime = 0;
            } //else if (!isBurning() && cookTime > 0)
              //  cookTime = MathHelper.clamp(cookTime - 2, 0, totalCookTime);
            if (flag != isBurning()) {
                flag1 = true;
                Dryer.setState(isBurning(), world, pos);
            }
        }
        if (flag1)
            markDirty();

    }

    public int getCookTime(ItemStack stack) {
        if(!IntegrationHelper.isLoadedTinkers)
            return 0;
        int amount = TinkerIntegration.getTime(stack);
        amount /= 30;
        if(amount<10)
            amount = 10;
        return amount;
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt() {
        if (dryerItemStacks.get(0).isEmpty())
            return false;
        ItemStack itemstack = getResult(this.dryerItemStacks.get(0));
        if (itemstack.isEmpty())
            return false;
        ItemStack itemstack1 = this.dryerItemStacks.get(2);
        if (itemstack1.isEmpty())
            return true;
        if (!itemstack1.isItemEqual(itemstack))
            return false;
        if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
            return true;
        return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem() {
        if (canSmelt()) {
            ItemStack itemstack = dryerItemStacks.get(0);
            ItemStack itemstack1 = getResult(itemstack);
            ItemStack itemstack2 = dryerItemStacks.get(2);

            if (itemstack2.isEmpty())
                this.dryerItemStacks.set(2, itemstack1.copy());
            else if (itemstack2.getItem() == itemstack1.getItem())
                itemstack2.grow(itemstack1.getCount());


            itemstack.shrink(1);
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack);
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (world.getTileEntity(pos) != this)
            return false;
        return player.getDistanceSq((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) <= 64.0D;

    }

    public void openInventory(EntityPlayer player) { }

    public void closeInventory(EntityPlayer player) { }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2)
            return false;
        if (index != 1)
            return true;
        ItemStack itemstack = this.dryerItemStacks.get(1);
        return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;

    }

    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN)
            return SLOTS_BOTTOM;
        return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1) {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
                return false;
        }

        return true;
    }

    public String getGuiID() {
        return "vint:dryer";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerDryer(playerInventory, this);
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return this.dryerBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.dryerBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount() {
        return 4;
    }

    public void clear() {
        this.dryerItemStacks.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}