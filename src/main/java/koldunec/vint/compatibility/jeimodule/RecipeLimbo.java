package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.recipes.TwilightTransmutations.RecipeInput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class RecipeLimbo {

    // Filled in Defaulter class
    public static List<DefaultRecipe> LIST_OF_SIMPLES = new ArrayList<>();

    public static class DefaultRecipe implements IRecipeWrapper {
        ItemStack base;
        ItemStack catalyst;
        ItemStack result;
        int time;
        public DefaultRecipe(ItemStack _base, ItemStack _catalyst, ItemStack _result, int _time){
            base = _base;
            catalyst = _catalyst;
            result = _result;
            time = _time;
            LIST_OF_SIMPLES.add(this);
        }

        public DefaultRecipe(RecipeInput input, RecipeOutput output){
            base = new ItemStack(input.base, 1 ,input.basemeta);
            catalyst = new ItemStack(input.catalyst, 1 ,input.catalystmeta);
            result = output.result;    // should work because we are talking about simpliest recipe
            time = output.time;
            LIST_OF_SIMPLES.add(this);
        }

        @Override
        public void getIngredients(IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, new ArrayList<ItemStack>(){{add(base); add(catalyst);}});
            iIngredients.setOutput(VanillaTypes.ITEM, result);
        }

        @Override
        public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
            drawStringCentered(minecraft.fontRenderer, TextFormatting.DARK_RED + "" + (time/20.0) + " sec", 60, -1);
        }

        @SideOnly(Side.CLIENT)
        private void drawStringCentered(FontRenderer fontRenderer, String text, int x, int y) {
            fontRenderer.drawString(text, (x - fontRenderer.getStringWidth(text) / 2), y, 0);
        }
    }
}
