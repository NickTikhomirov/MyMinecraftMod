package koldunec.ammpdbm_mod.tileentities;


import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import twilightforest.item.ItemTFMoonwormQueen;
import twilightforest.item.ItemTFTransformPowder;

public class Slot_TowerCatalyst extends Slot {
    public Slot_TowerCatalyst(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest")){
            if(stack.getItem().getUnlocalizedName().equals("carminite")) return true;
            if(stack.getItem().getUnlocalizedName().equals("nagaScale")) return true;
            if(stack.getItem().equals(Items.GOLD_INGOT)) return true;
            if(stack.getItem().equals(Items.DIAMOND)) return true;
            if(stack.getItem().equals(Items.EMERALD)) return true;
            if(stack.getItem() instanceof ItemTFMoonwormQueen) return true;
            if(stack.getItem() instanceof ItemTFTransformPowder) return true;

        }
        return false;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 64;
    }

}
