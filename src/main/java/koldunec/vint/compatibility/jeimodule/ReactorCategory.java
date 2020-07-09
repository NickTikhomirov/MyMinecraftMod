package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.compatibility.jeimodule.TooltipCallbacks.CataSafe;
import koldunec.vint.vint;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;


public class ReactorCategory implements IRecipeCategory {

    protected String type(){
        return "no consume";
    }

    public static final String UID = "VINT_REACTOR";
    IDrawable back;

    public ReactorCategory(IGuiHelper i){
        back = i.createDrawable(
                new ResourceLocation(
                        vint.MODID,
                        "textures/gui/reactor_jei.png"),25, 20, 110, 33);
    }

    @Override
    public String getUid() {
        return UID+"_MAIN";
    }

    @Override
    public String getTitle() {
        return "Twilight Reactor ("+ type()+")";
    }

    @Override
    public String getModName() {
        return vint.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return back;
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IRecipeWrapper iRecipeWrapper, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();
        prepareTooltips(guiItemStacks, iRecipeWrapper);
        guiItemStacks.init(0, true, 24, 9);
        guiItemStacks.init(1, true, 4, 9);
        guiItemStacks.init(2, false, 83, 9);
        guiItemStacks.set(0, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
        guiItemStacks.set(1, iIngredients.getInputs(VanillaTypes.ITEM).get(1));
        guiItemStacks.set(2, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
    }

    protected void prepareTooltips(IGuiItemStackGroup group, IRecipeWrapper wrapper){
        if(wrapper instanceof RecipeLimbo.DefaultRecipeWithWildcardCatalyst)
            group.addTooltipCallback(new CataSafe(true, 1));
        else
            group.addTooltipCallback(CataSafe.getInstance(true));
    }
}
