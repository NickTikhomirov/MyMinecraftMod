package koldunec.vint.recipes;

import koldunec.vint.potions.PotionRegister;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeTippedArrow;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;

public class dragonBreathFix extends RecipeTippedArrow {


    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        if (inv.getWidth() == 3 && inv.getHeight() == 3) {
            for (int i = 0; i < inv.getWidth(); ++i) {
                for (int j = 0; j < inv.getHeight(); ++j) {
                    ItemStack itemstack = inv.getStackInRowAndColumn(i, j);
                    if (itemstack.isEmpty()) return false;
                    Item item = itemstack.getItem();
                    if (i == 1 && j == 1) {
                        if (item != Items.POTIONITEM) return false;
                    } else if (item != Items.ARROW)
                        return false;
                }
            }
            return !PotionUtils.getPotionFromItem(inv.getStackInRowAndColumn(1, 1)).equals(PotionRegister.MINDDEVOUR_TYPE_STANDARD);
        } else {
            return false;
        }
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack itemstack = inv.getStackInRowAndColumn(1, 1);

        if (itemstack.getItem() != Items.POTIONITEM)
        {
            return ItemStack.EMPTY;
        }
        else
        {
            ItemStack itemstack1 = new ItemStack(Items.TIPPED_ARROW, 8);
            PotionUtils.addPotionToItemStack(itemstack1, PotionUtils.getPotionFromItem(itemstack));
            PotionUtils.appendEffects(itemstack1, PotionUtils.getFullEffectsFromItem(itemstack));
            return itemstack1;
        }
    }

}
