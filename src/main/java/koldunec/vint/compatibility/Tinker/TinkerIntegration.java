package koldunec.vint.compatibility.Tinker;

import koldunec.vint.compatibility.Tinker.TinkerBook.MaterialDocumenter;
import koldunec.vint.compatibility.Tinker.TinkerBook.ModifierAppender;
import koldunec.vint.compatibility.Tinker.traits.*;
import koldunec.vint.compatibility.Tinker.traits.tas_tic.Cool;
import koldunec.vint.compatibility.Tinker.traits.tas_tic.Warm;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.objectbuilders.Sidemod_Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.TinkerTraits;


import static koldunec.vint.utils.TechHelper.getColor;

public class TinkerIntegration {
    public static Material CARMINITE = new Material("carminite", getColor(154F,0,0));
    public static Material IRONWOOD = new Material("ironwood", getColor(133F,137F,52F));
    public static Material MAZESTONE = new Material("mazestone", getColor(110F,122,109));

    public static Material BAMBOO = new Material("bamboo", getColor(130,168,89));
    public static Material HONEY_CRYSTAL = new Material("honey_crystal",getColor(255F,195F,15F));

    public static Material CRIMSON_LOG = new Material("crimson_log", getColor(123F,0F,0F));
    public static Material WARPED_LOG = new Material("warped_log", getColor(22F,97F,91F));
    public static Material NETHER_CACTUS = new Material("nether_cactus",getColor(210,210,0));
    public static Material DEBRIS = new Material("debris", getColor(126,96,89));

    public static AbstractTrait LEFT_HAND_RULE = new LeftHandRule();
    public static AbstractTrait BORING = new Boring();
    public static AbstractTrait MAZEY = new Mazey();
    public static AbstractTrait REDPOWER = new RedPower();
    public static AbstractTrait SLIMECUTTER = new SlimeCutter();
    public static AbstractTrait BLACKENING = new Blackening();
    public static AbstractTrait BZZZ = new Bzzz();
    public static AbstractTrait REACHING = new Reaching();

    public static AbstractTrait CAPITATOR = new Capitator();
    public static AbstractTrait FUNDAMENTAL = new Fundamental();
    public static AbstractTrait PRIMAL = new Primal();
    public static AbstractTrait COMPLEX = new Complex();

    public static AbstractTrait WARM = new Warm();
    public static AbstractTrait COOL = new Cool();

    public static Fluid IRONWOOD_JIJA = null;

    public static void preInit(){
        preInitJija();
        RedPower.initPerks();

        MaterialDocumenter.APPENDANTS.add(DEBRIS);
        if(IntegrationHelper.isLoadedFuture)
            MaterialDocumenter.APPENDANTS.add(BAMBOO);

        ModifierAppender.APPENDANTS.add(FUNDAMENTAL);
        ModifierAppender.APPENDANTS.add(CAPITATOR);
        if(IntegrationHelper.isLoadedPrimitive)
            ModifierAppender.APPENDANTS.add(COMPLEX);

        ConfigureMaterials.addStatsVint();
        ConfigureMaterials.addStatsTwilight();
        ConfigureMaterials.addStatsFuture();
    }


    public static void init(){
        ConfigureMaterials.ConfigureFuture();
        ConfigureMaterials.ConfigureTwilight();
        ConfigureMaterials.TweakTiCWithTaN();

        DEBRIS.addItem(BlockRegister.FRESH_DEBRIS,144);
        DEBRIS.setRepresentativeItem(BlockRegister.FRESH_DEBRIS);
        DEBRIS.setCraftable(true).setCastable(false);
        DEBRIS.addTrait(BLACKENING).addTrait(TinkerTraits.aridiculous).addTrait(REACHING);

        NETHER_CACTUS.addItem(BlockRegister.NETHER_CACTUS, 144);
        NETHER_CACTUS.setRepresentativeItem(BlockRegister.NETHER_CACTUS);
        NETHER_CACTUS.setCraftable(true).setCastable(false);
        NETHER_CACTUS.addTrait(TinkerTraits.hellish).addTrait(TinkerTraits.prickly);

        CRIMSON_LOG.addItem(BlockRegister.RED_NY_LOG, 144);
        WARPED_LOG.addItem(BlockRegister.BLUE_NY_LOG, 144);
        CRIMSON_LOG.setRepresentativeItem(BlockRegister.RED_NY_LOG);
        WARPED_LOG.setRepresentativeItem(BlockRegister.BLUE_NY_LOG);
        CRIMSON_LOG.setCraftable(true).setCastable(false);
        WARPED_LOG.setCraftable(true).setCastable(false);
        CRIMSON_LOG.addTrait(TinkerTraits.hellish).addTrait(REDPOWER);
        WARPED_LOG.addTrait(TinkerTraits.hellish).addTrait(TinkerTraits.alien);
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
        FUNDAMENTAL.addRecipeMatch(new RecipeMatch.Item(new ItemStack(ItemRegister.WOODEN_RUNE),1));
        if(IntegrationHelper.isLoadedPrimitive)
            COMPLEX.addRecipeMatch(new RecipeMatch.Item(new ItemStack(Sidemod_Items.Camodye()),1));
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
}
