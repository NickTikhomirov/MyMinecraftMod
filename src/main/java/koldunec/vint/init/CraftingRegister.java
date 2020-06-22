package koldunec.vint.init;

import koldunec.vint.recipes.BrewRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.common.crafting.CraftingHelper.*;

public class CraftingRegister
{
    public static void register() {
        initCoreCrafting();
        initSmelting();
        BrewRegister.initBrewing();
    }


    private static void initCoreCrafting(){
        registerRecipes("core/broom");
        if (IntegrationHelper.isLoadedTwilight) {
            registerRecipes("twilight/trans_1");
        }
    }


    private static void initSmelting(){
        GameRegistry.addSmelting(BlockRegister.ORE_BIT, new ItemStack(ItemRegister.BITCOIN5000), 0.5F);
        GameRegistry.addSmelting(ItemRegister.POTION_MIX, new ItemStack(ItemRegister.NETHER_CRYSTAL), 1F);
        if(IntegrationHelper.isLoadedChisel){
            GameRegistry.addSmelting(Item.getByNameOrId("chisel:chisel_diamond"), new ItemStack(ItemRegister.CHLESIS), 10F);
        }
    }

    private static void registerRecipes(String name) {
        CraftingHelper.register(new ResourceLocation("vint", name), (IRecipeFactory) (context, json) -> getRecipe(json, context));
    }
}