package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.item.ItemStack;

public class ThunderRecipe extends RecipeOutput {
    public static String THUNDER_MESSAGE ="vint.jei.needstorm";
    public boolean consume = false;

    public ThunderRecipe(ItemStack res) {
        super(res, 100);
    }

    public ThunderRecipe(ItemStack res, boolean _consume) {
        this(res);
        consume = _consume;
    }

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        return tile.getWorld().isThundering();
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) {
        if(consume)
            return catalyst;
        ItemStack copy = catalyst.copy();
        copy.shrink(1);
        return copy;
    }
}
