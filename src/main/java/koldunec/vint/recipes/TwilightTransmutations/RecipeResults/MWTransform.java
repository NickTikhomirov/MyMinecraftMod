package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.jeimodule.RecipeLimbo;
import koldunec.vint.recipes.TwilightTransmutations.RecipeInput;
import koldunec.vint.tileentities.TileTower;
import koldunec.vint.utils.ItemWithMeta;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

import java.util.HashMap;

public class MWTransform implements ITwilightResult {

    public static HashMap<ItemWithMeta, ItemStack> recipes = new HashMap<ItemWithMeta, ItemStack>(){{
        put(new ItemWithMeta(Items.GLOWSTONE_DUST), new ItemStack(TFBlocks.firefly));
        put(new ItemWithMeta(Items.GUNPOWDER), new ItemStack(TFBlocks.cicada));
        put(new ItemWithMeta(Items.SUGAR), new ItemStack(TFBlocks.moonworm));
    }};

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        return (catalyst.getItem().equals(TFItems.moonworm_queen))
                && recipes.get(new ItemWithMeta(base))!=null;
    }

    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        ItemStack result = recipes.get(new ItemWithMeta(base));
        if(result!=null)
            return result.copy();
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) { return catalyst; }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile) {
        return 100;
    }

    public static void assignJEI(){
        if(!IntegrationHelper.isLoadedJEI)
            return;
        ItemWithMeta catalyst = new ItemWithMeta(TFItems.moonworm_queen, 32767);
        for(ItemWithMeta key: recipes.keySet()) {
            new RecipeLimbo.DefaultRecipeWithWildcardCatalyst(new RecipeInput(key, catalyst), new RecipeOutput(recipes.get(key), 100));
        }
    }
}
