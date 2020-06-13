package koldunec.vint.potions;

import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import twilightforest.item.TFItems;

public class BrewRegister {
    public static void initBrewing() {
        initWaterBreathChain();
        initPlagueAndAcid();
        initMindProtection();
        initPoisonProtection();
        initOther();

        //apple potions
        PotionHelper.addMix(PotionTypes.THICK, Ingredient.fromStacks(new ItemStack(Items.GOLDEN_APPLE, 1, 0)), PotionRegister.GOLDENAPLLE_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.THICK, Ingredient.fromStacks(new ItemStack(Items.GOLDEN_APPLE, 1, 1)), PotionRegister.GOLDENAPLLE_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.THICK, ItemRegister.DIAMONDGOLDEN_APPLE, PotionRegister.DIAMONDGOLDENAPPLE_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.THICK, ItemRegister.GOLDENDIAMOND_APPLE, PotionRegister.GOLDENDIAMONAPPLE_TYPE_STANDART);

        //haste block
        PotionHelper.addMix(PotionTypes.SWIFTNESS, Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 3)), PotionRegister.HASTE_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.LONG_SWIFTNESS, Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 3)), PotionRegister.HASTE_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.HASTE_TYPE_STANDART, Items.REDSTONE, PotionRegister.HASTE_TYPE_LONG);

        //regeneration block
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemRegister.SOUL_FRUIT, PotionTypes.STRONG_REGENERATION);

        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.POTIONITEM), new ItemStack(Items.NETHER_STAR), new ItemStack(ItemRegister.NETHER_DRINK));

        if (IntegrationHelper.isLoadedTwilight)
            PotionHelper.addMix(PotionTypes.THICK, TFItems.torchberries, PotionTypes.NIGHT_VISION);
        if (IntegrationHelper.isLoadedTinkers)
            PotionHelper.addMix(PotionRegister.WITHERPROTECTION_TYPE_STANDART, Ingredient.fromStacks(new ItemStack(Item.getByNameOrId("tconstruct:materials"), 1, 17)), PotionRegister.WITHERPROTECTION_TYPE_STRONG);

    }


    private static void initWaterBreathChain() {
        PotionHelper.addMix(PotionTypes.WATER_BREATHING, Items.GOLDEN_CARROT, PotionRegister.OCEANPOTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionTypes.LONG_WATER_BREATHING, Items.GOLDEN_CARROT, PotionRegister.OCEANPOTION_TYPE_LONG);
        PotionHelper.addMix(PotionTypes.NIGHT_VISION, Ingredient.fromStacks(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata())), PotionRegister.OCEANPOTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionTypes.LONG_NIGHT_VISION, Ingredient.fromStacks(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata())), PotionRegister.OCEANPOTION_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.OCEANPOTION_TYPE_STANDARD, Items.REDSTONE, PotionRegister.OCEANPOTION_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.OCEANPOTION_TYPE_LONG, ItemRegister.FISH_C, PotionRegister.OCEANPOTIONCOMBAT_TYPE_LONG);

    }

    private static void initMindProtection() {
        PotionHelper.addMix(PotionTypes.THICK, ItemRegister.GOLDEN_POTATO, PotionRegister.MINDDEVOUR_TYPE_STANDARD);

        PotionHelper.addMix(PotionRegister.MINDDEVOUR_TYPE_STANDARD, ItemRegister.CURING_GRASS, PotionRegister.MINDPROTECTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionRegister.MINDDEVOUR_TYPE_STANDARD, Items.FERMENTED_SPIDER_EYE, PotionRegister.MINDPROTECTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionRegister.MINDPROTECTION_TYPE_STANDARD, Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 3)), PotionRegister.MINDPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.WATER, ItemRegister.SUPERCURING_GRASS, PotionRegister.MINDPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemRegister.SUPERCURING_GRASS, PotionRegister.MINDPROTECTION_TYPE_STRONG);
    }

    private static void initPlagueAndAcid() {
        PotionHelper.addMix(PotionTypes.POISON, Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), PotionRegister.PLAGUE_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.POISON, Item.getItemFromBlock(Blocks.RED_MUSHROOM), PotionRegister.PLAGUE_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.PLAGUE_TYPE_STANDART, Items.GHAST_TEAR, PotionRegister.PLAGUE_TYPE_MIXED1);
        PotionHelper.addMix(PotionRegister.PLAGUE_TYPE_MIXED1, ItemRegister.FLESH, PotionRegister.PLAGUE_TYPE_MIXED2);

        PotionHelper.addMix(PotionRegister.MINDDEVOUR_TYPE_STANDARD, Items.SPIDER_EYE, PotionRegister.ACID_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.ACID_TYPE_STANDART, ItemRegister.ESSENCE_RAINBOW, PotionRegister.ACID_TYPE_STRONG);
        PotionHelper.addMix(PotionRegister.ACID_TYPE_STRONG, Ingredient.fromStacks(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata())), PotionRegister.ACID_TYPE_SUPERSTRONG);
    }

    private static void initOther(){
        //MagicProtection
        PotionHelper.addMix(PotionTypes.THICK, ItemRegister.POTION_MIX, PotionRegister.MAGICPROTECTION_TYPE_STANDARD);
        PotionHelper.addMix(PotionRegister.MAGICPROTECTION_TYPE_STANDARD, Items.GLOWSTONE_DUST, PotionRegister.MAGICPROTECTION_TYPE_STRONG);
        //Humanity
        PotionHelper.addMix(PotionTypes.MUNDANE, ItemRegister.FLESH, PotionRegister.HUMANITY_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STANDART, Items.REDSTONE, PotionRegister.HUMANITY_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STANDART, Items.APPLE, PotionRegister.HUMANITY_TYPE_STRONG);
        //ender protection
        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STRONG, Item.getItemFromBlock(Blocks.WEB), PotionRegister.ENDERPROTECTION_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.ENDERPROTECTION_TYPE_STANDART, Items.REDSTONE, PotionRegister.ENDERPROTECTION_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.ENDERPROTECTION_TYPE_STANDART, Items.GLOWSTONE_DUST, PotionRegister.ENDERPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemRegister.CHORUS_C, PotionRegister.ENDERPROTECTION_TYPE_SHORT);
        PotionHelper.addMix(PotionRegister.ENDERPROTECTION_TYPE_SHORT, Items.REDSTONE, PotionRegister.ENDERPROTECTION_TYPE_STRONG);
        //wither protection
        PotionHelper.addMix(PotionRegister.HUMANITY_TYPE_STANDART, Items.BONE, PotionRegister.WITHERPROTECTION_TYPE_STANDART);
        PotionHelper.addMix(PotionRegister.WITHERPROTECTION_TYPE_STANDART, Ingredient.fromStacks(new ItemStack(Item.getItemFromBlock(Blocks.SKULL), 1, 1)), PotionRegister.WITHERPROTECTION_TYPE_STRONG);
    }

    private static void initPoisonProtection(){
        PotionHelper.addMix(PotionTypes.POISON, ItemRegister.EYE_C, PotionRegister.POISONPROTECTION_TYPE_STANDART);
        PotionHelper.addMix(PotionTypes.STRONG_POISON, ItemRegister.EYE_C, PotionRegister.POISONPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionRegister.POISONPROTECTION_TYPE_STANDART, Items.REDSTONE, PotionRegister.POISONPROTECTION_TYPE_STRONG);
        PotionHelper.addMix(PotionTypes.LONG_POISON, ItemRegister.EYE_C, PotionRegister.POISONPROTECTION_TYPE_LONG);
        PotionHelper.addMix(PotionRegister.POISONPROTECTION_TYPE_STANDART, Items.GLOWSTONE_DUST, PotionRegister.POISONPROTECTION_TYPE_LONG);

    }
}
