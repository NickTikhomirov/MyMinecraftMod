package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.recipes.TwilightTransmutations.RecipeInput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.ITooltipCallback;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
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


    public interface ICertainTooltipHandler extends ITooltipCallback<ItemStack>{}

    public static class DefaultRecipe implements IRecipeWrapper, ICertainTooltipHandler {
        ItemStack base = ItemStack.EMPTY;
        ItemStack catalyst = ItemStack.EMPTY;
        ItemStack result = ItemStack.EMPTY;
        String message = "";
        int time = 0;

        public DefaultRecipe(){}

        public DefaultRecipe(RecipeInput input, RecipeOutput output){
            base = new ItemStack(input.getBase(), 1 , input.getBasemeta());
            catalyst = new ItemStack(input.getCatalyst(), 1 , input.getCatalystmeta());
            result = output.result;    // should work because we are talking about simpliest recipe
            time = output.time;
            LIST_OF_SIMPLES.add(this);
        }

        public DefaultRecipe(RecipeInput input, RecipeOutput output, String msg){
            this(input, output);
            message = msg;
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
            if(!message.equals(""))
                drawStringCentered(minecraft.fontRenderer, TextFormatting.BLACK + I18n.format(message), 60, 31 );
        }

        @SideOnly(Side.CLIENT)
        private void drawStringCentered(FontRenderer fontRenderer, String text, int x, int y) {
            fontRenderer.drawString(text, (x - fontRenderer.getStringWidth(text) / 2), y, 0);
        }

        protected IDrawableAnimated getArrow(){ return BLUE_ARROW; }

        @Override
        public void onTooltip(int i, boolean b, ItemStack itemStack, List<String> list) {
            if(i!=1)
                return;
            list.add("");
            list.add(TextFormatting.GREEN + I18n.format("vint.jei.tooltip.catalyst.safe"));
        }
    }

    public static class DefaultRecipeWithWildcardCatalyst extends DefaultRecipe {
        public DefaultRecipeWithWildcardCatalyst(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }

        @Override
        public void onTooltip(int i, boolean b, ItemStack itemStack, List<String> list) {
            if(i!=1)
                return;
            super.onTooltip(i, b, itemStack, list);
            list.add(TextFormatting.GOLD + I18n.format("vint.jei.tooltip.catalyst.anydur"));
        }
    }

    public static class ConsumeRecipe extends DefaultRecipe{
        public ConsumeRecipe(RecipeInput input, RecipeOutput output) {
            super(input, output);
        }

        public ConsumeRecipe(RecipeInput input, RecipeOutput output, String msg) {
            super(input, output);
            message = msg;
        }

        @Override
        protected IDrawableAnimated getArrow() {
            return RED_ARROW;
        }

        @Override
        public void onTooltip(int i, boolean b, ItemStack itemStack, List<String> list) {
            if(i!=1)
                return;
            list.add("");
            list.add(TextFormatting.RED + I18n.format("vint.jei.tooltip.catalyst.dang"));
        }
    }
}
