package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.recipes.TwilightTransmutations.RecipeInput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import mezz.jei.api.gui.IDrawableAnimated;
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

import static koldunec.vint.compatibility.jeimodule.VintJeiSupport.BLUE_ARROW;
import static koldunec.vint.compatibility.jeimodule.VintJeiSupport.RED_ARROW;

public class RecipeLimbo {

    // Filled in Defaulter class
    public static List<DefaultRecipe> LIST_OF_SIMPLES = new ArrayList<>();
    public static List<DefaultRecipe> LIST_OF_CONSUMES = new ArrayList<>();

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
            getList().add(this);
        }

        public DefaultRecipe(RecipeInput input, RecipeOutput output){
            base = new ItemStack(input.getBase(), 1 , input.getBasemeta());
            catalyst = new ItemStack(input.getCatalyst(), 1 , input.getCatalystmeta());
            result = output.result;    // should work because we are talking about simpliest recipe
            time = output.time;
            getList().add(this);
        }

        public List<DefaultRecipe> getList(){
            return LIST_OF_SIMPLES;
        }

        @Override
        public void getIngredients(IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, new ArrayList<ItemStack>(){{add(base); add(catalyst);}});
            iIngredients.setOutput(VanillaTypes.ITEM, result);
        }

        @Override
        public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
            getArrow().draw(minecraft, 48, 10);
            drawStringCentered(minecraft.fontRenderer, TextFormatting.DARK_GRAY + "" + (time/20.0) + " sec", 60, -1);
        }

        @SideOnly(Side.CLIENT)
        private void drawStringCentered(FontRenderer fontRenderer, String text, int x, int y) {
            fontRenderer.drawString(text, (x - fontRenderer.getStringWidth(text) / 2), y, 0);
        }

        protected IDrawableAnimated getArrow(){ return BLUE_ARROW; }

    }

    public static class DefaultRecipeWithWildcardCatalyst extends DefaultRecipe {
        public DefaultRecipeWithWildcardCatalyst(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }
    }

    public static class ConsumeRecipe extends DefaultRecipe{
        public ConsumeRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }

        @Override
        public List<DefaultRecipe> getList() {
            return LIST_OF_SIMPLES;
        }

        @Override
        protected IDrawableAnimated getArrow() {
            return RED_ARROW;
        }
    }
}
