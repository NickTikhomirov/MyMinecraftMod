package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.item.ItemStack;


/**
 * All methods require two Itemstacks, because NBT support (why not)
 */

public interface ITwilightResult {

    // for anything you want to do
    default void optionalActivity(ItemStack base, ItemStack catalyst, ItemStack fuel, TileTower tile){ }

    default boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile){
        return true;
    }

    ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile);

    ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile);

    default ItemStack getBaseStack(ItemStack base, ItemStack catalyst, TileTower tile){
        base.shrink(1);
        return base;
    }

    int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile);
}
