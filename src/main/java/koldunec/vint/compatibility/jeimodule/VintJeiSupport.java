package koldunec.vint.compatibility.jeimodule;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.vint;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JEIPlugin
public class VintJeiSupport implements IModPlugin {

    public ReactorCategory SAFE;

    public static ResourceLocation reactor_texture = new ResourceLocation(vint.MODID, "textures/gui/reactor_jei.png");
    public static IDrawableAnimated BLUE_ARROW;
    public static IDrawableAnimated  RED_ARROW;

    public static void initArrows(IGuiHelper helper){
        BLUE_ARROW = helper.createAnimatedDrawable(helper.createDrawable(reactor_texture, 166, 0, 23+23, 16), 50, IDrawableAnimated.StartDirection.LEFT, false);
         RED_ARROW = helper.createAnimatedDrawable(helper.createDrawable(reactor_texture, 166, 18, 23+23, 16), 50, IDrawableAnimated.StartDirection.LEFT, false);
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        initArrows(helper);
        SAFE = new ReactorCategory(helper);
        registry.addRecipeCategories(SAFE);
    }

    @Override
    public void register(IModRegistry registry) {
        if(!IntegrationHelper.isLoadedTwilight)
            return;

        registry.addRecipes(RecipeLimbo.LIST_OF_SIMPLES, SAFE.getUid());
        registry.addRecipeCatalyst(new ItemStack(BlockRegister.TOWER_FURNACE), SAFE.getUid());

        registry.addIngredientInfo(new ItemStack(ItemRegister.BORER_REED), VanillaTypes.ITEM, "vint.jei.docs.borer");
    }
}
