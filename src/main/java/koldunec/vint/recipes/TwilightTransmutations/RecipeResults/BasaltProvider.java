package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BasaltProvider extends RecipeOutput {
    public BasaltProvider(Block i, int f) {
        super(i, f);
    }

    @Override
    public ItemStack getBaseStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        return base;
    }
}
