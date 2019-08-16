package koldunec.vint.tileentities;

import koldunec.vint.init.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.item.ItemTFTransformPowder;

// 0 - input
// 1 - output
// 2 - catalyst
// 3 - fuel_1
// 4 - fuel_9


public class TileEntityTowerFurnace extends TileEntity implements ITickable, ISidedInventory {

    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
    private String customName;
    private ItemStack result = ItemStack.EMPTY;

    private int ProcessingTime;
    private int TotalProcessingTime = 100;

    public String getGuiID()
    {
        return "vint:twilightreactor";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName!=null && !this.customName.isEmpty();
    }

    @Override
    public String getName() {
        return this.hasCustomName()?this.customName:"container.twilightreactor";
    }


    public void setCustomName(String cn){
        this.customName = cn;
    }

    @Override
    public ITextComponent getDisplayName(){
        return this.hasCustomName()? new TextComponentString(this.getName()):new TextComponentTranslation(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack i: this.inventory){
            if(!i.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return (ItemStack)this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory,index);
    }




    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack i = (ItemStack)this.inventory.get(index);
        boolean f = !stack.isEmpty() && stack.isItemEqual(i) && ItemStack.areItemStackTagsEqual(stack, i);
        this.inventory.set(index, stack);

        if(stack.getCount()>this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());
        if(index==0&&!f){
            ItemStack i1 = (ItemStack) this.inventory.get(index+1);
            this.ProcessingTime = 0;
            this.markDirty();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);
        this.ProcessingTime = compound.getInteger("GhastingTime");
        this.ProcessingTime = compound.getInteger("TotalGhastingTime");

        if(compound.hasKey("CustomName",8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("GhastingTime", (short)this.ProcessingTime);
        compound.setInteger("TotalGhastingTime", (short)this.TotalProcessingTime);
        ItemStackHelper.saveAllItems(compound,this.inventory);

        if(this.hasCustomName()) compound.setString("CustomName",this.customName);
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning(){
        return this.ProcessingTime<TotalProcessingTime;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory i){
        return i.getField(0)>0;
    }

    public void update(){
        if(this.isBurning())
        {
            TowerFurnace.setState(true, world, pos);
        }

        if(!this.world.isRemote){
            if(!this.isBurning()){
                if(!inventory.get(0).isEmpty() && !output_clogged()){
                    if(this.canSmelt()){
                        short fuelrequired = TowerFurnaceRecipes.getInstance().getGhastingFuel(this.inventory.get(0),this.inventory.get(2));
                        if(consume_fuel(fuelrequired)){
                            result = TowerFurnaceRecipes.getInstance().getGhastingResult(inventory.get(0), Catalyst_plus_Fuel(fuelrequired));
                            ProcessingTime++;
                            inventory.get(0).shrink(1);
                        }
                    }
                }
            } else {
                ProcessingTime++;
                if(ProcessingTime==TotalProcessingTime){
                    if(inventory.get(1).getCount()>0)
                        inventory.get(1).grow(result.getCount());
                    else inventory.set(1,result);
                    result = ItemStack.EMPTY;
                    ProcessingTime = 0;
                }
            }

        }
    }


    public ItemStack Catalyst_plus_Fuel(short f){
        return new ItemStack(this.inventory.get(2).getItem(),f);
    }

    private boolean canSmelt()
    {
        if(this.inventory.get(0).isEmpty())
            return false;
        else
        {
            short f = 0;
            if(this.inventory.get(3).getItem().equals(ItemRegister.TRANSFORMATION_DUST)) f = 1;
            if(this.inventory.get(4).getItem() instanceof ItemTFTransformPowder) f = 9;
            ItemStack result = TowerFurnaceRecipes.getInstance().getGhastingResult((ItemStack)this.inventory.get(0), Catalyst_plus_Fuel(f));
            if(result.isEmpty()) return false;
            else
            {
                ItemStack output = (ItemStack)this.inventory.get(1);
                if(output.isEmpty()) return true;
                if(!output.isItemEqual(result)) return false;
                int res = output.getCount() + result.getCount();
                return res <= 64 && res <= output.getMaxStackSize();
            }
        }
    }



    public boolean hasFuel(){
        if(inventory.get(3).getItem().equals(ItemRegister.TRANSFORMATION_DUST)) return true;
        return inventory.get(4).getItem() instanceof ItemTFTransformPowder;
    }

    private boolean consume_dust(short amount){
        ItemStack r = inventory.get(3);
        if(!r.getItem().equals(ItemRegister.TRANSFORMATION_DUST)) return false;
        if(r.getCount()<amount) return false;
        inventory.get(3).shrink(amount);
        return true;
    }

    private boolean consume_powder(short amount){
        ItemStack r = inventory.get(4);
        if(!(r.getItem()instanceof ItemTFTransformPowder)) return false;
        if(9*r.getCount()<amount) return false;
        inventory.get(4).shrink(amount);
        if(amount%9!=0){
            if(inventory.get(3).getCount()+9-amount>inventory.get(3).getMaxStackSize())
                inventory.get(3).setCount(inventory.get(3).getMaxStackSize());
            else inventory.get(3).setCount(inventory.get(3).getCount()+9-amount);
        }
        return true;
    }

    private boolean output_clogged(){
        return inventory.get(1).getCount()==inventory.get(1).getMaxStackSize();
    }


    public boolean consume_fuel(short amount){
        if(amount==0) return true;
        if(amount==1){
            if(!consume_dust(amount))
                return consume_powder(amount);
            else return true;
        } else
            if(!consume_powder(amount))
                return consume_dust(amount);
            else return true;
    }


    public boolean isUsableByPlayer(EntityPlayer p)
    {
        return (world.getTileEntity(pos) == this) && p.getDistanceSq((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) <= 64.0D;
    }


    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if(side==EnumFacing.DOWN)
            return new int[]{1};
        if(side==EnumFacing.UP)
            return new int[]{0};
        if(side==EnumFacing.NORTH || side==EnumFacing.SOUTH)
            return new int[]{2};
        return new int[]{3,4};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index,itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }


    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index==1) return false;
        if(index!=3 && index!=4) return true;
        if(index==3 && stack.getItem().equals(ItemRegister.TRANSFORMATION_DUST)) return true;
        if(index==4 && stack.getItem() instanceof ItemTFTransformPowder) return true;
        return false;
    }

    @Override
    public int getField(int id) {
        if(id==0) return this.ProcessingTime;
        if(id==1) return this.TotalProcessingTime;
        return 0;
    }

    @Override
    public void setField(int id, int value) {
      if(id==0) this.ProcessingTime = value;
      if(id==1) this.TotalProcessingTime = value;

    }

    @Override
    public int getFieldCount() {
        return 5;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }
}
