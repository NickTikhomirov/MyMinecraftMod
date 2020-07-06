package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.item.ItemStack;

public class RecipeOutputConsumeCatalyst extends RecipeOutput {

    public RecipeOutputConsumeCatalyst(ItemStack res, int f){ super(res, f); }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tower) {
        ItemStack copy = catalyst.copy();
        copy.shrink(1);
        return copy;
    }
}
