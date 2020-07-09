package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.compatibility.jeimodule.TooltipCallbacks.CataSafe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.recipe.IRecipeWrapper;

public class ReactorConsumeCategory extends ReactorCategory {
    public ReactorConsumeCategory(IGuiHelper i) {
        super(i);
    }

    @Override
    public String getUid() {
        return UID+"_CONSUME";
    }

    @Override
    protected String type() {
        return "consume";
    }

    @Override
    protected void prepareTooltips(IGuiItemStackGroup group, IRecipeWrapper wrapper) {
        group.addTooltipCallback(CataSafe.getInstance(false)); // todo make durability-consuming recipes
    }
}
