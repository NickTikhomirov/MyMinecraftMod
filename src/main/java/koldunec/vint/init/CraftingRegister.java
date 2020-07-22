package koldunec.vint.init;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.recipes.BrewRegister;
import koldunec.vint.recipes.TwilightTransmutations.TransmutationsRegister;
import net.minecraft.init.Items;
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
        if(IntegrationHelper.isLoadedTwilight)
            TransmutationsRegister.init();
    }


    private static void initCoreCrafting(){
        registerRecipes("core/broom");
        if (IntegrationHelper.isLoadedTwilight) {
            registerRecipes("twilight/trans_1");
        }
    }


    private static void initSmelting(){
        GameRegistry.addSmelting(ItemRegister.POTION_MIX, new ItemStack(ItemRegister.NETHER_CRYSTAL), 1F);
        GameRegistry.addSmelting(BlockRegister.FRESH_DEBRIS, new ItemStack(BlockRegister.FAKE_NETHERITE), 1F);
        GameRegistry.addSmelting(ItemRegister.SOUL_FRUIT, new ItemStack(Items.GHAST_TEAR), 1F);
        if(IntegrationHelper.isLoadedChisel){
            GameRegistry.addSmelting(Item.getByNameOrId("chisel:chisel_diamond"), new ItemStack(ItemRegister.CHLESIS), 10F);
        }
        if(IntegrationHelper.isLoadedFuture){
            GameRegistry.addSmelting(
                    Item.getByNameOrId(IntegrationHelper.idFuture+":honey_block"),
                    new ItemStack(ItemRegister.HONEY_CRYSTAL), 1F
            );
        }
    }

    private static void registerRecipes(String name) {
        CraftingHelper.register(new ResourceLocation("vint", name), (IRecipeFactory) (context, json) -> getRecipe(json, context));
    }
}