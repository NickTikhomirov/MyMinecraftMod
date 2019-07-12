package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingRegister
{
    public static void register()
    {
        registerRecipes("broom");
        registerRecipes("charcoalblock");
        registerRecipes("charcoalblock_");
        registerRecipes("potatogp");
        registerRecipes("sparkles_to_rainbow");
        registerRecipes("lasercore");
        registerRecipes("flint_base");
        registerRecipes("flint_weak");
        registerRecipes("runic_stick");
        registerRecipes("curedeye");
        registerRecipes("curedchicken");
        registerRecipes("curedpotato");
        registerRecipes("curedfish");
        registerRecipes("mushroom_to_dye");
        registerRecipes("mushroom_to_dye2");
        registerRecipes("round_stone");

        if(ammpdbm_mod.isLoadedTwilight){
            registerRecipes("trans_1");
            registerRecipes("trans_1_");
        }

        if(ammpdbm_mod.isLoadedProjectX){
            registerRecipes("alblock");
            registerRecipes("alblock_");
            registerRecipes("juke");
            registerRecipes("sparksToSulfur");
            registerRecipes("sparksToSulfur2");
            if(ammpdbm_mod.isLoadedProjectRed_exploration){
                registerRecipes("pr_s_r");
                registerRecipes("pr_s_p");
                registerRecipes("pr_p_r");
                registerRecipes("pr_p_s");
                registerRecipes("pr_r_s");
                registerRecipes("pr_r_p");
            }
            if(ammpdbm_mod.isLoadedSulfurTorches){
                registerRecipes("altorch");
                registerRecipes("sultorch");
            }
        }
        GameRegistry.addSmelting(BlockRegister.ORE_BIT,new ItemStack(ItemRegister.BITCOIN5000),0.5F);
        GameRegistry.addSmelting(Items.GHAST_TEAR,new ItemStack(ItemRegister.SOUL_CRYSTAL_s),0.5F);


        PotionHelper.addMix(PotionTypes.SWIFTNESS, Ingredient.fromStacks(new ItemStack(Items.DYE,1,3)), PotionRegister.HASTE_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.LONG_SWIFTNESS, Ingredient.fromStacks(new ItemStack(Items.DYE,1,3)), PotionRegister.HASTE_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.HASTE_TYPE_STANDART, Items.REDSTONE, PotionRegister.HASTE_TYPE_LONG);

        PotionHelper.addMix(PotionTypes.AWKWARD, ItemRegister.GOLDEN_POTATO, PotionRegister.MINDDEVOUR_TYPE_STANDARD);

        PotionHelper.addMix(PotionRegister.MINDDEVOUR_TYPE_STANDARD,ItemRegister.CURING_GRASS,PotionRegister.MINDPROTECTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionRegister.MINDDEVOUR_TYPE_STANDARD,Items.FERMENTED_SPIDER_EYE,PotionRegister.MINDPROTECTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionRegister.MINDPROTECTION_TYPE_STANDARD, Ingredient.fromStacks(new ItemStack(Items.DYE,1,3)),PotionRegister.MINDPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.WATER,ItemRegister.SUPERCURING_GRASS,PotionRegister.MINDPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.AWKWARD,ItemRegister.SUPERCURING_GRASS,PotionRegister.MINDPROTECTION_TYPE_STRONG);

        PotionHelper.addMix(PotionTypes.THICK,ItemRegister.ESSENCE_RAINBOW,PotionRegister.MAGICPROTECTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionRegister.MAGICPROTECTION_TYPE_STANDARD,Items.GLOWSTONE_DUST,PotionRegister.MAGICPROTECTION_TYPE_STRONG);

        PotionHelper.addMix(PotionTypes.WATER_BREATHING, Items.GOLDEN_CARROT, PotionRegister.OCEANPOTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionTypes.LONG_WATER_BREATHING, Items.GOLDEN_CARROT, PotionRegister.OCEANPOTION_TYPE_LONG);

        PotionHelper.addMix(PotionTypes.NIGHT_VISION, Ingredient.fromStacks(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata())), PotionRegister.OCEANPOTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionTypes.LONG_NIGHT_VISION, Ingredient.fromStacks(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata())), PotionRegister.OCEANPOTION_TYPE_LONG);

        PotionHelper.addMix(PotionRegister.OCEANPOTION_TYPE_STANDARD, Items.REDSTONE, PotionRegister.OCEANPOTION_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.OCEANPOTION_TYPE_LONG, ItemRegister.FISH_C, PotionRegister.OCEANPOTIONCOMBAT_TYPE_LONG);

        PotionHelper.addMix(PotionTypes.AWKWARD,ItemRegister.FLESH,PotionRegister.HUMANITY_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STANDART, Items.REDSTONE, PotionRegister.HUMANITY_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STANDART, Items.APPLE, PotionRegister.HUMANITY_TYPE_STRONG);

        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STRONG, Item.getItemFromBlock(Blocks.WEB), PotionRegister.ENDERPROTECTION_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.ENDERPROTECTION_TYPE_STANDART, Items.REDSTONE, PotionRegister.ENDERPROTECTION_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.ENDERPROTECTION_TYPE_STANDART, Items.GLOWSTONE_DUST, PotionRegister.ENDERPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.CHORUS_FRUIT, PotionRegister.ENDERPROTECTION_TYPE_SHORT);
        PotionHelper.addMix(PotionRegister.ENDERPROTECTION_TYPE_SHORT, Items.REDSTONE, PotionRegister.ENDERPROTECTION_TYPE_STRONG);

        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STANDART, Items.BONE, PotionRegister.WITHERPROTECTION_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.WITHERPROTECTION_TYPE_STANDART, Ingredient.fromStacks(new ItemStack(Item.getItemFromBlock(Blocks.SKULL),1,1)), PotionRegister.WITHERPROTECTION_TYPE_STRONG);

        PotionHelper.addMix(PotionTypes.THICK, Ingredient.fromStacks(new ItemStack(Items.GOLDEN_APPLE,1,0)), PotionRegister.GOLDENAPLLE_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.THICK, Ingredient.fromStacks(new ItemStack(Items.GOLDEN_APPLE,1,1)), PotionRegister.GOLDENAPLLE_TYPE_STRONG);

        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.POTIONITEM), new ItemStack(Items.NETHER_STAR), new ItemStack(ItemRegister.NETHER_DRINK));

        if(ammpdbm_mod.isLoadedTinkers){
            PotionHelper.addMix(PotionRegister.WITHERPROTECTION_TYPE_STANDART,Ingredient.fromStacks(new ItemStack(Item.getByNameOrId("tconstruct:materials"),1,17)),PotionRegister.WITHERPROTECTION_TYPE_STRONG);
        }
    }

    private static void registerRecipes(String name)
    {
        CraftingHelper.register(new ResourceLocation("ammpdbm_mod", name), (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
    }
}