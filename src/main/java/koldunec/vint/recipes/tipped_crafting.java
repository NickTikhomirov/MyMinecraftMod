package koldunec.vint.recipes;

import koldunec.vint.init.ItemRegister;
import koldunec.vint.potions.PotionRegister;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class tipped_crafting extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        if (inv.getWidth() == 3 && inv.getHeight() == 3) {
            for (int i = 0; i < inv.getWidth(); ++i) {
                for (int j = 0; j < inv.getHeight(); ++j) {
                    ItemStack itemstack = inv.getStackInRowAndColumn(i, j);
                    if (itemstack.isEmpty()) {
                        return false;
                    }
                    Item item = itemstack.getItem();
                    if (i == 1 && j == 1) {
                        if (item != Items.TIPPED_ARROW){
                            return false;
                        }

                    }
                    else if (item != ItemRegister.CURING_GRASS)
                    {
                        return false;
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }









    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack itemstack = inv.getStackInRowAndColumn(1, 1);
        if (itemstack.getItem() != Items.TIPPED_ARROW) {
            return ItemStack.EMPTY;
        }
        else if(!PotionUtils.getEffectsFromStack(itemstack).equals(PotionRegister.MINDDEVOUR_TYPE_STANDARD.getEffects())){
            return ItemStack.EMPTY;
        }
        else {
            return new ItemStack(ItemRegister.SUPERCURING_GRASS,8);
        }
    }

    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        return NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
    }

    public boolean isDynamic(){
        return true;
    }


    public boolean canFit(int width, int height)
    {
        return width >= 2 && height >= 2;
    }
}
