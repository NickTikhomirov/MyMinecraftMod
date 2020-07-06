package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class MWTransform implements ITwilightResult {

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        Item ibase = base.getItem();
        return (catalyst.getItem().equals(TFItems.moonworm_queen)) &&
                (ibase.equals(Items.GLOWSTONE_DUST)) || (ibase.equals(Items.GUNPOWDER)) || (ibase.equals(Items.SUGAR));
    }

    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        Item ibase = base.getItem();
        if(ibase.equals(Items.GLOWSTONE_DUST))
            return new ItemStack(TFBlocks.firefly, 1);
        if(ibase.equals(Items.GUNPOWDER))
            return new ItemStack(TFBlocks.cicada, 1);
        if(ibase.equals(Items.SUGAR))
            return new ItemStack(TFBlocks.moonworm, 1);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) { return catalyst; }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile) {
        return 100;
    }
}
