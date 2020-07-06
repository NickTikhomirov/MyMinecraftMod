package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.init.ItemRegister;
import koldunec.vint.tileentities.TileTower;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ROutputAurora extends RecipeOutput {

    public ROutputAurora() {
        super(Items.EMERALD, 100);
    }

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        return catalyst.getCount()==1;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tower) {
        return new ItemStack(ItemRegister.AURORA_CORE, 1);
    }
}
