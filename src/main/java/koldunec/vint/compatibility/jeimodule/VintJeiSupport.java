package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class VintJeiSupport implements IModPlugin {

    public ReactorCategory SAFE;
    public ReactorConsumeCategory CONSUME;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        SAFE = new ReactorCategory(helper);
        CONSUME = new ReactorConsumeCategory(helper);
        registry.addRecipeCategories(SAFE);
        registry.addRecipeCategories(CONSUME);
    }

    @Override
    public void register(IModRegistry registry) {
        if(!IntegrationHelper.isLoadedTwilight)
            return;

        registry.addRecipes(RecipeLimbo.LIST_OF_SIMPLES, SAFE.getUid());
        registry.addRecipeCatalyst(new ItemStack(BlockRegister.TOWER_FURNACE), SAFE.getUid());
        registry.addRecipes(RecipeLimbo.LIST_OF_CONSUMES, CONSUME.getUid());
        registry.addRecipeCatalyst(new ItemStack(BlockRegister.TOWER_FURNACE), CONSUME.getUid());

        registry.addIngredientInfo(new ItemStack(ItemRegister.BORER_REED), VanillaTypes.ITEM, "vint.jei.docs.borer");
    }
}
