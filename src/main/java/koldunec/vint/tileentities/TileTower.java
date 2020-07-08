package koldunec.vint.tileentities;

import koldunec.vint.blocks.TowerFurnace;
import koldunec.vint.recipes.TwilightTransmutations.FuelHelper;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.ITwilightResult;
import koldunec.vint.recipes.TwilightTransmutations.TransmutationsRegister;
import koldunec.vint.tileentities.containers.ContainerTower;
import koldunec.vint.utils.EnumSides;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileTower extends TileEntityLockable implements ITickable, ISidedInventory {
    /**
     * Indexes:
     *      0 - base
     *      1 - catalyst
     *      2 - fuel
     *      3 - result
     */


     private static final int[] SLOTS_TOP = new int[]{0};
     private static final int[] SLOTS_BOTTOM = new int[] {3};
     private static final int[] SLOTS_SIDES = new int[] {2};
     private static final int[] SLOTS_FACE = new int[]{1};
     private NonNullList<ItemStack> towerItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
     private int BurnTime = 0;
     private int currentBurnTime = 0;
     private int cookTime = 0;
     private int totalCookTime = 0;

     public boolean isBurning()
    {
        return this.BurnTime > 0;
    }

     @Override
     public void update() {
         boolean burning = isBurning();
         boolean makeDirty = false;
         ItemStack fuel = towerItemStacks.get(2);
         if(burning)
             --BurnTime;
         if(!world.isRemote) {
             if (isBurning() || !fuel.isEmpty() && !(towerItemStacks.get(0)).isEmpty()) {
                 if (!isBurning() && canOperate()) {
                     BurnTime = FuelHelper.CountFuelValue(fuel);
                     currentBurnTime = BurnTime;
                     if (isBurning()) {
                         makeDirty = true;
                         if (!fuel.isEmpty()) {
                             Item item = fuel.getItem();
                             fuel.shrink(1);
                             if (fuel.isEmpty()) {
                                 ItemStack item1 = item.getContainerItem(fuel);
                                 towerItemStacks.set(2, item1);
                             }
                         }
                     }
                 }
                 if (isBurning() && canOperate()) {
                     ++cookTime;
                     if (cookTime >= totalCookTime) {
                         cookTime = 0;
                         smeltItem();
                         makeDirty = true;
                     }
                 } else cookTime = 0;
             } else if (!isBurning() && cookTime > 0)
                 cookTime = MathHelper.clamp(cookTime - 2, 0, totalCookTime);
             if (burning != isBurning()) {
                 makeDirty = true;
                 TowerFurnace.setState(isBurning(), world, pos);
             }
         }

         if (makeDirty)
             markDirty();
     }


     public boolean canOperate(){
         ItemStack base = towerItemStacks.get(0);
         ItemStack catalyst = towerItemStacks.get(1);
         ItemStack existingResult = towerItemStacks.get(3);
         if(base.isEmpty() || catalyst.isEmpty())
             return false;
         ITwilightResult result = TransmutationsRegister.getResult(base, catalyst, this);
         if(result==null)
             return false;
         if(!result.canProcess(base, catalyst, this))
             return false;
         ItemStack newResult = result.getResultStack(base,catalyst,this);
         if(newResult.isEmpty())
             return false;
         if(!existingResult.isEmpty()){
             if(!ItemStack.areItemStackTagsEqual(existingResult, newResult) || !existingResult.isItemEqual(newResult) || existingResult.getMetadata()!=newResult.getMetadata())
                 return false;
             int totalCount = existingResult.getCount()+newResult.getCount();
             if(totalCount>64 || totalCount>newResult.getMaxStackSize())
                 return false;
         }
         totalCookTime = result.getProcessTime(base, catalyst, this);
         return true;
     }

     public void smeltItem(){
         if(!canOperate())
             return;
         ItemStack base = towerItemStacks.get(0).copy();
         ItemStack catalyst = towerItemStacks.get(1).copy();
         ItemStack oldResult = towerItemStacks.get(3);
         ITwilightResult result = TransmutationsRegister.getResult(base, catalyst, this);
         ItemStack stackResult = result.getResultStack(base,catalyst,this).copy();
         totalCookTime = result.getProcessTime(base, catalyst, this);
         towerItemStacks.set(0,result.getBaseStack(base,catalyst,this).copy());
         towerItemStacks.set(1,result.getCatalystAfterTransmutation(base,catalyst, this).copy());
         if(!result.emptyOutputChance(base, catalyst, this)) {
             if (oldResult.isEmpty())
                 towerItemStacks.set(3, stackResult);
             else
                 oldResult.grow(stackResult.getCount());
         }
         result.optionalActivity(base,catalyst,towerItemStacks.get(2).copy(),this);
     }


     protected EnumSides EvaluateSide(EnumFacing side){
         EnumFacing self = world.getBlockState(pos).getValue(TowerFurnace.FACING);
         return EnumSides.getSideFor(self,side);
     }

     @Override
     public int[] getSlotsForFace(EnumFacing side) {
         if(side.equals(EnumFacing.UP))
             return SLOTS_TOP;
         if(side.equals(EnumFacing.DOWN))
             return SLOTS_BOTTOM;
         switch(EvaluateSide(side)){
             case FRONT:
             case BACK:
                 return SLOTS_FACE;
             case LEFT:
             case RIGHT:
                 return SLOTS_SIDES;
         }
         return new int[0];
     }

     // todo
     @Override
     public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
         return false;
     }

     // todo
     @Override
     public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
         return false;
     }

     @Override
     public int getSizeInventory() {
         return towerItemStacks.size();
     }

     @Override
     public boolean isEmpty() {
         for (ItemStack itemstack : towerItemStacks)
             if (!itemstack.isEmpty())
                 return false;
         return true;
     }

     @Override
     public ItemStack getStackInSlot(int index) {
         if(index>=towerItemStacks.size())
             return ItemStack.EMPTY;
         return towerItemStacks.get(index);
     }

     @Override
     public ItemStack decrStackSize(int index, int count) {
         return ItemStackHelper.getAndSplit(towerItemStacks, index, count);
     }

     @Override
     public ItemStack removeStackFromSlot(int index) {
         return ItemStackHelper.getAndRemove(towerItemStacks, index);
     }

     // todo:check
     @Override
     public void setInventorySlotContents(int index, ItemStack stack) {
         towerItemStacks.set(index, stack);
         if (stack.getCount() > getInventoryStackLimit())
             stack.setCount(getInventoryStackLimit());
     }

     @Override
     public int getInventoryStackLimit() { return 64; }

     @Override
     public boolean isUsableByPlayer(EntityPlayer player) {
         if (this.world.getTileEntity(this.pos) != this)
             return false;
         return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;

     }

     @Override
     public void openInventory(EntityPlayer player) {}

     @Override
     public void closeInventory(EntityPlayer player) {}

     @Override
     public boolean isItemValidForSlot(int index, ItemStack stack) {
         switch(index){
             case 0:
                 return true;
             case 1:
                 return true;
             case 2:
                 return FuelHelper.IsValidFuel(stack);
             default:
                 return false;
         }
     }

     @Override
     public int getField(int id) {
         switch(id){
             case 0: return BurnTime;
             case 1: return currentBurnTime;
             case 2: return cookTime;
             case 3: return totalCookTime;
         }
         return 0;
     }

     @Override
     public void setField(int id, int value) {
         switch(id){
             case 0:
                 BurnTime = value;
                 return;
             case 1:
                 currentBurnTime = value;
                 return;
             case 2:
                 cookTime = value;
                 return;
             case 3:
                 totalCookTime = value;
                 return;
         }
     }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }   // BurnTime

     @Override
     public int getFieldCount() {
         return 4;
     }

     @Override
     public void clear() {
         for(int i=3; i>-1; --i)
             setInventorySlotContents(i,ItemStack.EMPTY);
         for(int i=0; i<getFieldCount(); ++i)
             setField(i,0);
     }


     @Override
     public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
         return new ContainerTower(playerInventory,this);
     }

     @Override
     public String getGuiID() {
         return "vint:twilight_reactor";
     }

     @Override
     public String getName() {
         return "container.twilight_reactor";
     }

     // todo (maybe)
     @Override
     public boolean hasCustomName() {
         return false;
     }


     @Override
     public NBTTagCompound writeToNBT(NBTTagCompound compound) {
         super.writeToNBT(compound);
         compound.setInteger("BurnTime", (short) BurnTime);
         compound.setInteger("CookTime", (short) cookTime);
         compound.setInteger("CookTimeTotal", (short) totalCookTime);
         ItemStackHelper.saveAllItems(compound, towerItemStacks);
         return compound;
     }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        towerItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, towerItemStacks);
        BurnTime = compound.getInteger("BurnTime");
        cookTime = compound.getInteger("CookTime");
        totalCookTime = compound.getInteger("CookTimeTotal");
    }


}
