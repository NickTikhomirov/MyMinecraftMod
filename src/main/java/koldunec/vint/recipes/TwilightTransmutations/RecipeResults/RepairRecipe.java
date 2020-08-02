package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.jeimodule.RecipeLimbo;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.TwilightTransmutations.TransmutationsRegister;
import koldunec.vint.tileentities.TileTower;
import koldunec.vint.utils.ItemWithMeta;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Repairing recipe that targets only one tool, that takes catalyst's place
 * The resulting item is trash (default: dust)
 */

public class RepairRecipe implements ITwilightResult {
    public ItemStack result = new ItemStack(ItemRegister.DUST);
    public Item repairable;
    public ItemWithMeta material;
    public int step;

    public RepairRecipe(Item tool, ItemStack _mat, int amount){
        repairable = tool;
        material = new ItemWithMeta(_mat);
        step = amount;
    }

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        return repairable.equals(catalyst.getItem()) && catalyst.getItemDamage()>0 && material.equalsStack(base);
    }

    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        return result;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) {
        ItemStack copy = catalyst.copy();
        copy.setItemDamage(catalyst.getItemDamage()-step);
        return copy;
    }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile) {
        return 50;
    }

    public static RepairRecipe BuildAndJEI(Item tool, ItemStack material, int amount){
        RepairRecipe result = new RepairRecipe(tool, material, amount);
        if(IntegrationHelper.isLoadedJEI)
            new RecipeLimbo.RepairRecipeJEI(result);
        return result;
    }

    public static void BuildAndJEI_AndSave(Item tool, ItemStack material, int amount){
        TransmutationsRegister.RECIPES_2.add(BuildAndJEI(tool, material, amount));
    }
}
