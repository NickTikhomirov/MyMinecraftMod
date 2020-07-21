package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.recipes.TwilightTransmutations.RecipeInput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RepairRecipe;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.ITooltipCallback;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import static koldunec.vint.compatibility.jeimodule.VintJeiSupport.*;

public class RecipeLimbo {

    // Filled in Defaulter class
    public static List<DefaultRecipe> LIST_OF_SIMPLES = new ArrayList<>();


    public interface ICertainTooltipHandler extends ITooltipCallback<ItemStack>{}


    /**
     *  Default recipe pattern
     */

    public static class DefaultRecipe implements IRecipeWrapper, ICertainTooltipHandler {
        ItemStack base = ItemStack.EMPTY;
        ItemStack catalyst = ItemStack.EMPTY;
        ItemStack result = ItemStack.EMPTY;
        String message = "";
        String time_msg = "vint.jei.time.simple";
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
            drawStringCentered(minecraft.fontRenderer,
                    TextFormatting.DARK_GRAY + "" + (time/20.0) + " " + I18n.format(time_msg),
                    60, -1);
            if(!message.equals(""))
                drawStringCentered(minecraft.fontRenderer, TextFormatting.BLACK + getLocalizedMessage(), 60, 31 );
        }

        protected String getLocalizedMessage(){
            return I18n.format(message);
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

    /**
     *  Pattern for any-damage catalyst recipes
     */

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


    /**
     *  Pattern for consuming recipes
     */

    public static class ConsumeRecipe extends DefaultRecipe{

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

    /**
     *  Pattern for repair recipes
     */

    public static class RepairRecipeJEI extends DefaultRecipe {

        protected RepairRecipeJEI(){}

        public RepairRecipeJEI(RepairRecipe recipe){
            base = recipe.material.BuildStack();
            result = recipe.result;
            message = "" + recipe.step;
            time = 50; // const for simple repair recipes
            catalyst = getHalfDamaged(recipe.repairable);
            time_msg = "vint.jei.time.partial";
            LIST_OF_SIMPLES.add(this);
        }

        @Override
        protected String getLocalizedMessage() {
            return I18n.format("vint.jei.repair_msg", message);
        }

        @Override
        protected IDrawableAnimated getArrow() {
            return BORER_ARROW;
        }

        @Override
        public void onTooltip(int i, boolean b, ItemStack itemStack, List<String> list) {
            if(i!=1)
                return;
            list.add("");
            list.add(TextFormatting.AQUA + getLocalizedMessage());
            list.add(TextFormatting.GRAY + I18n.format("vint.jei.tooltip.catalyst.notfull"));
        }

        // this method is overloaded, because I want JEI to know that repaired item is kinda result of the recipe
        @Override
        public void getIngredients(IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, new ArrayList<ItemStack>(){{add(base); add(catalyst);}});
            iIngredients.setOutputs(VanillaTypes.ITEM, new ArrayList<ItemStack>(){{add(result); add(catalyst);}});
        }

        protected ItemStack getHalfDamaged(Item i){
            if(!i.isDamageable())
                return new ItemStack(i);
            ItemStack temp = new ItemStack(i, 1);
            temp.setItemDamage(i.getMaxDamage(temp)/2);
            return temp;
        }
    }


    /**
     *  Pattern for transfer recipes
     */

    public static class ConvertRecipeJEI extends RepairRecipeJEI{
        boolean results_on_end;

        public ConvertRecipeJEI(Item _cata, Item _base, int _amount_to_cata, ItemStack _result, boolean onlyonce){
            base = new ItemStack(_base, 1, 32767);
            catalyst = getHalfDamaged(_cata);
            result = _result;
            message = ""+_amount_to_cata;
            time_msg = "vint.jei.time.partial";
            time = 50;
            results_on_end = onlyonce;
            LIST_OF_SIMPLES.add(this);
        }

        @Override
        public void onTooltip(int i, boolean b, ItemStack itemStack, List<String> list) {
            super.onTooltip(i, b, itemStack, list);
            if(i==0){
                list.add("");
                list.add(TextFormatting.GOLD + I18n.format("vint.jei.tooltip.catalyst.dmg", 1));
            } else if(results_on_end && i==2){
                list.add("");
                list.add(TextFormatting.GOLD + I18n.format("vint.jei.tooltip.result.onlyonce"));
            }
        }
    }
}
