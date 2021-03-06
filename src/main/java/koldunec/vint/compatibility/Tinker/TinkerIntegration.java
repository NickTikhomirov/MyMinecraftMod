package koldunec.vint.compatibility.Tinker;

import koldunec.vint.compatibility.Tinker.materials.*;
import koldunec.vint.compatibility.Tinker.traits.*;
import koldunec.vint.compatibility.Tinker.traits.tas_tic.Cool;
import koldunec.vint.compatibility.Tinker.traits.tas_tic.SweetBlood;
import koldunec.vint.compatibility.Tinker.traits.tas_tic.Warm;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.compatibility.Sidemod_Items;
import koldunec.vint.objectbuilders.LootObjectsBuilder;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.tools.*;

import java.util.ArrayList;
import java.util.HashSet;


public class TinkerIntegration {

    public static HashSet<Modifier> APPENDANTS_MOD = new HashSet<>();
    public static HashSet<Material> APPENDANTS_MAT = new HashSet<>();

    public static ArrayList<BaseMaterial> list = new ArrayList<>();

    public static BaseMaterial CARMINITE = new MaterialCarminite();
    public static BaseMaterial IRONWOOD = new Ironwood();
    public static BaseMaterial MAZESTONE = new Mazestone();
    public static BaseMaterial FROZEN = new MaterialFrozen();
    public static Material AURORA = new Material("aurora", ColorConstants.FROZEN_COLOR, true);

    public static Material SPECTRE = new Material("spectral_metal", ColorConstants.SPECTRE_COLOR);
    public static Material SPECTRE_STRING = new Material("spectral_string", ColorConstants.SPECTRE_COLOR);

    public static Material BAMBOO = new Material("bamboo", ColorConstants.BAMBOO_COLOR);
    public static Material HONEY_CRYSTAL = new Material("honey_crystal", ColorConstants.HONEY_COLOR);

    public static Material CRIMSON_LOG = new Material("crimson_log", ColorConstants.CRIMSON_COLOR);
    public static Material WARPED_LOG = new Material("warped_log", ColorConstants.WARPED_COLOR);
    public static Material GOLD_LOG = new Material("gold_log", ColorConstants.CACTUS_COLOR);
    public static Material GREEN_LOG = new Material("green_log", ColorConstants.GREEN_LOG_COLOR);
    public static Material NETHER_CACTUS = new Material("nether_cactus", ColorConstants.CACTUS_COLOR);

    public static AbstractTrait LEFT_HAND_RULE = new LeftHandRule();
    public static AbstractTrait BORING = new Boring();
    public static AbstractTrait MAZEY = new Mazey();
    public static AbstractTrait REDPOWER = new RedPower();
    public static AbstractTrait SLIMECUTTER = new SlimeCutter();
    public static AbstractTrait BLACKENING = new Blackening();
    public static AbstractTrait BZZZ = new Bzzz();
    public static AbstractTrait REACHING = new Reaching();
    public static AbstractTrait ICE_QUEEN = new IceQueen();
    public static AbstractTrait DEAL = new Deal();
    public static AbstractTrait MERCIFUL = new Merciful();
    public static AbstractTrait CRUSADE = new Crusade();

    public static AbstractTrait SHIFTING = new Shifting();
    public static AbstractTrait WONDERBREAKER = new Wonderbreaker();

    public static AbstractTrait CAPITATOR = new Capitator();
    public static AbstractTrait FUNDAMENTAL = new Fundamental();
    public static AbstractTrait PRIMAL = new Primal();
    public static AbstractTrait COMPLEX = new Complex();
    public static ModifierTrait ACTIVATING = new Activating();
    public static ModifierTrait DODO_RAGE = new DodoRage();
    public static ModifierTrait DIMENSIONAL = new Dimensional();

    public static AbstractTrait WARM = new Warm();
    public static AbstractTrait COOL = new Cool();
    public static ModifierTrait BLOOD_HYDRATION = new SweetBlood();

    public static Fluid IRONWOOD_JIJA = null;


    public static void preInit(){
        TinkerRegistry.registerDryingRecipe(ItemRegister.VITASARIA, ItemRegister.DUST, 5*60*20);
        preInitJija();

        if(IntegrationHelper.isLoadedFuture)
            APPENDANTS_MAT.add(BAMBOO);

        APPENDANTS_MOD.add(FUNDAMENTAL);
        APPENDANTS_MOD.add(CAPITATOR);
        APPENDANTS_MOD.add(ACTIVATING);
        if(IntegrationHelper.isLoadedPrimitive) {
            APPENDANTS_MOD.add(COMPLEX);
            APPENDANTS_MOD.add(DODO_RAGE);
        }
        if(IntegrationHelper.isLoadedTough)
            APPENDANTS_MOD.add(BLOOD_HYDRATION);

        ConfigureMaterials.addStatsVint();
        ConfigureMaterials.addStatsTwilight();
        ConfigureMaterials.addStatsFuture();
        ConfigureMaterials.addStatsOther();
    }


    public static void init(){
        ConfigureMaterials.ConfigureFuture();
        ConfigureMaterials.ConfigureTwilight();
        ConfigureMaterials.TweakTiCWithTaN();
        ConfigureMaterials.ConfigureOther();

        NETHER_CACTUS.addItem(BlockRegister.NETHER_CACTUS, 144);
        NETHER_CACTUS.setRepresentativeItem(BlockRegister.NETHER_CACTUS);
        NETHER_CACTUS.setCraftable(true).setCastable(false);
        NETHER_CACTUS.addTrait(TinkerTraits.hellish).addTrait(TinkerTraits.prickly);

        ConfigureMaterials.initLog(CRIMSON_LOG, BlockRegister.RED_NY_LOG);
        ConfigureMaterials.initLog(WARPED_LOG, BlockRegister.BLUE_NY_LOG);
        ConfigureMaterials.initLog(GOLD_LOG, BlockRegister.GOLD_NY_LOG);
        ConfigureMaterials.initLog(GREEN_LOG, BlockRegister.GREEN_NY_LOG);
        CRIMSON_LOG.addTrait(BLACKENING).addTrait(TinkerTraits.aridiculous);
        CRIMSON_LOG.addTrait(BLACKENING, MaterialTypes.EXTRA).addTrait(TinkerTraits.momentum, MaterialTypes.EXTRA);
        CRIMSON_LOG.addTrait(REDPOWER, MaterialTypes.HEAD).addTrait(BLACKENING, MaterialTypes.HEAD);
        WARPED_LOG.addTrait(BLACKENING).addTrait(TinkerTraits.alien).addTrait(DEAL, MaterialTypes.HEAD).addTrait(BLACKENING, MaterialTypes.HEAD).addTrait(TinkerTraits.alien, MaterialTypes.HEAD);
        GOLD_LOG.addTrait(BLACKENING);
        GREEN_LOG.addTrait(BLACKENING).addTrait(MERCIFUL);
    }


    public static void postInit(){
        ((Capitator)CAPITATOR).appendCactus();      // fixes Apotheosis interaction
        CAPITATOR.addRecipeMatch(new RecipeMatch.ItemCombination(
                1,
                new ItemStack(Blocks.TRIPWIRE_HOOK),
                new ItemStack(Blocks.TRIPWIRE_HOOK),
                new ItemStack(Blocks.CHEST),
                new ItemStack(Items.SHEARS)
                ));
        if(IntegrationHelper.isLoadedTwilight){
            PRIMAL.addRecipeMatch(new RecipeMatch.ItemCombination(
                    1,
                    new ItemStack(Blocks.RED_MUSHROOM),
                    new ItemStack(Blocks.BROWN_MUSHROOM),
                    new ItemStack(Item.getByNameOrId(IntegrationHelper.idTwilight+":raw_meef"))
            ));
        }
        ACTIVATING.addRecipeMatch(new RecipeMatch.Item(new ItemStack(Blocks.REDSTONE_TORCH), 1));
        FUNDAMENTAL.addRecipeMatch(new RecipeMatch.Item(new ItemStack(ItemRegister.WOODEN_RUNE),1));
        if(IntegrationHelper.isLoadedPrimitive) {
            COMPLEX.addRecipeMatch(new RecipeMatch.Item(new ItemStack(Sidemod_Items.Camodye()), 1));
            DODO_RAGE.addRecipeMatch(new RecipeMatch.Item(new ItemStack(Item.getByNameOrId("primitivemobs:dodo_egg")), 1));
        }
        if(IntegrationHelper.isLoadedTough)
            BLOOD_HYDRATION.addRecipeMatch(new RecipeMatch.ItemCombination( 1,
                    new ItemStack(TinkerCommons.edibles, 1, 3),
                    new ItemStack(TinkerCommons.edibles, 1, 3),
                    new ItemStack(TinkerCommons.edibles, 1, 3),
                    new ItemStack(Items.BONE),
                    new ItemStack(Items.BONE)
            ));
        if(IntegrationHelper.isLoadedRandomThings)
            DIMENSIONAL.addRecipeMatch(new RecipeMatch.ItemCombination(1,
                    new ItemStack(Item.getByNameOrId("randomthings:stableenderpearl")))
            );
    }


    private static void preInitJija(){
        IRONWOOD_JIJA = new Tinkerfluid("ironwood", IRONWOOD.materialTextColor);
        IRONWOOD_JIJA.setTemperature(1000).setLuminosity(15);
        FluidRegistry.registerFluid(IRONWOOD_JIJA);
        FluidRegistry.addBucketForFluid(IRONWOOD_JIJA);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("fluid", IRONWOOD_JIJA.getName());    // name of the fluid
        tag.setString("ore", "Ironwood");                   // ore-suffix: ingotFoo, blockFoo, oreFoo,...
        tag.setBoolean("toolforge", true);
        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
    }

    public static void ProcessQueen(LivingDropsEvent e){
        LootObjectsBuilder.BuildItemWithDeath(e, TinkerTools.pickHead.getItemstackWithMaterial(AURORA));
        LootObjectsBuilder.BuildItemWithDeath(e, TinkerTools.excavatorHead.getItemstackWithMaterial(AURORA));
    }

    protected BaseMaterial put(BaseMaterial get){
        if(!get.allowed())
            return null;
        list.add(get);
        return get;
    }

    public static ItemStack getDrying(ItemStack i){
        return TinkerRegistry.getDryingResult(i);
    }

    public static int getTime(ItemStack i){
        return TinkerRegistry.getDryingTime(i);
    }

}
