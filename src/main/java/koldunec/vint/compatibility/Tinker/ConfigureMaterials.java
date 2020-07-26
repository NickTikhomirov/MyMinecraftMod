package koldunec.vint.compatibility.Tinker;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.CompatibilityRegister;
import koldunec.vint.init.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;
import twilightforest.item.TFItems;

import static koldunec.vint.compatibility.Tinker.TinkerIntegration.*;


public class ConfigureMaterials {

    static String HEAD = MaterialTypes.HEAD;
    static String EXTRA = MaterialTypes.EXTRA;
    static String HANDLE = MaterialTypes.HANDLE;
    static String SHAFT = MaterialTypes.SHAFT;
    static String BOW = MaterialTypes.BOW;


    public static void configureLog(Material m){
        TinkerRegistry.addMaterialStats(m,
                new HeadMaterialStats(200, 5F, 5F, 0),
                new ExtraMaterialStats(65),
                new HandleMaterialStats(1F,0)
        );
    }

    public static void initLog(Material m, Block b){
        m.addItem(b, 144);
        m.setRepresentativeItem(b);
        m.setCraftable(true).setCastable(false);
    }

    public static void addStatsVint(){
        TinkerRegistry.addMaterialStats(NETHER_CACTUS,
                new HeadMaterialStats(256, 6F, 4F, 1),
                new ExtraMaterialStats(65),
                new HandleMaterialStats(0.99F,20)
        );

        configureLog(CRIMSON_LOG);
        configureLog(WARPED_LOG);
        configureLog(GOLD_LOG);
        configureLog(GREEN_LOG);



        TinkerRegistry.integrate(NETHER_CACTUS).preInit();
        TinkerRegistry.integrate(CRIMSON_LOG).preInit();
        TinkerRegistry.integrate(WARPED_LOG).preInit();
        TinkerRegistry.integrate(GOLD_LOG).preInit();
        TinkerRegistry.integrate(GREEN_LOG).preInit();
    }


    public static void addStatsTwilight(){
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        APPENDANTS_MOD.add(PRIMAL);

        CARMINITE.addParts();
        FROZEN.addParts();

        TinkerRegistry.addMaterialStats(MAZESTONE,
                new HeadMaterialStats(255, 3F, 2F, 4),
                new ExtraMaterialStats(340)
        );

        TinkerRegistry.addMaterialStats(IRONWOOD,
                new HeadMaterialStats(204, 6F, 4F, 2),
                new ExtraMaterialStats(50),
                new HandleMaterialStats(1, 60),
                new BowMaterialStats(0.5F, 1.5F,7)
        );


        TinkerRegistry.addMaterialStats(AURORA,
                new HeadMaterialStats(511,11F,2F, 4));


        CARMINITE.register();
        FROZEN.register();
        TinkerRegistry.integrate(MAZESTONE).preInit();
        TinkerRegistry.integrate(AURORA).preInit();
        TinkerRegistry.integrate(IRONWOOD,IRONWOOD_JIJA,"Ironwood").preInit();
    }


    public static void addStatsFuture(){
        if(!IntegrationHelper.isLoadedFuture)
            return;
        TinkerRegistry.addMaterialStats(BAMBOO,
                new HandleMaterialStats(1.2F, 50),
                new ArrowShaftMaterialStats(1.3F,50)
        );
        TinkerRegistry.addMaterialStats(HONEY_CRYSTAL,
                new HeadMaterialStats(800,5F,2F,0),
                new ExtraMaterialStats(400),
                new HandleMaterialStats(0.7F, 50)
        );

        TinkerRegistry.integrate(BAMBOO).preInit();
        TinkerRegistry.integrate(HONEY_CRYSTAL).preInit();
    }


    public static void addStatsOther(){
        if(!IntegrationHelper.isLoadedRandomThings)
            return;

        TinkerRegistry.addMaterialStats(SPECTRE,
                new HeadMaterialStats(300, 10, 7,2),
                new HandleMaterialStats(0.8F, 10));

        TinkerRegistry.addMaterialStats(SPECTRE_STRING,
                new BowStringMaterialStats(1.4F));

        TinkerRegistry.integrate(SPECTRE).preInit();
        TinkerRegistry.integrate(SPECTRE_STRING).preInit();
        APPENDANTS_MOD.add(DIMENSIONAL);
    }

    public static void ConfigureOther(){
        if(!IntegrationHelper.isLoadedRandomThings)
            return;
        Item spectre = Item.getByNameOrId("randomthings:ingredient");

        SPECTRE.addItem(new ItemStack(spectre, 1, 3), 1, 144);
        SPECTRE.setRepresentativeItem(new ItemStack(spectre, 1, 3));
        SPECTRE.setCraftable(true).setCastable(false);
        SPECTRE.addTrait(REACHING, HEAD).addTrait(REACHING, HANDLE).addTrait(REACHING);
        SPECTRE.addTrait(TinkerTraits.unnatural, HEAD).addTrait(TinkerTraits.lightweight, HANDLE);

        SPECTRE_STRING.addItem(new ItemStack(spectre, 1, 12), 1, 144);
        SPECTRE_STRING.setRepresentativeItem(new ItemStack(spectre, 1, 12));
        SPECTRE_STRING.setCraftable(true).setCastable(false);
        SPECTRE_STRING.addTrait(TinkerTraits.lightweight, MaterialTypes.BOWSTRING);
    }


    public static void ConfigureFuture(){
        if(!IntegrationHelper.isLoadedFuture)
            return;

        BAMBOO.addItem(CompatibilityRegister.FUTURE_BAMBOO,1,144);
        BAMBOO.setRepresentativeItem(CompatibilityRegister.FUTURE_BAMBOO);
        BAMBOO.setCraftable(true).setCastable(false);
        BAMBOO.addTrait(TinkerTraits.ecological, HANDLE).addTrait(SLIMECUTTER, HANDLE);
        BAMBOO.addTrait(TinkerTraits.fractured, SHAFT);

        HONEY_CRYSTAL.addItem(ItemRegister.HONEY_CRYSTAL,1,144);
        HONEY_CRYSTAL.setRepresentativeItem(ItemRegister.HONEY_CRYSTAL);
        HONEY_CRYSTAL.setCraftable(true).setCastable(false);
        HONEY_CRYSTAL.addTrait(TinkerTraits.tasty).addTrait(BZZZ).addTrait(CRUSADE);
    }


    public static void ConfigureTwilight(){
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        CARMINITE.addItem(new ItemStack(Item.getByNameOrId("twilightforest:carminite"), 1), 1, 144);
        CARMINITE.setRepresentativeItem(Item.getByNameOrId("twilightforest:carminite"));
        CARMINITE.registerTraits();

        for(int i=0; i<8; ++i)
            MAZESTONE.addItem(new ItemStack(Item.getByNameOrId("twilightforest:maze_stone"), 1, i),1, 144);
        MAZESTONE.setRepresentativeItem(new ItemStack(Block.getBlockFromName("twilightforest:maze_stone"), 1, 1));
        MAZESTONE.setCraftable(true).setCastable(false);
        MAZESTONE.addTrait(LEFT_HAND_RULE, HEAD);
        MAZESTONE.addTrait(TinkerTraits.duritos, EXTRA);
        MAZESTONE.addTrait(MAZEY, HEAD).addTrait(MAZEY, EXTRA);
        MAZESTONE.addTrait(TConstruct.twilit, HEAD).addTrait(TConstruct.twilit, EXTRA);
        MAZESTONE.addTrait(TinkerTraits.heavy, EXTRA);

        IRONWOOD.addItem(TFItems.ironwood_ingot,1, 144);
        IRONWOOD.setRepresentativeItem(TFItems.ironwood_ingot);
        IRONWOOD.addTrait(TConstruct.twilit).addTrait(TinkerTraits.ecological).addTrait(TinkerTraits.magnetic);
        IRONWOOD.addTrait(TConstruct.twilit, HEAD).addTrait(TinkerTraits.ecological, HEAD).addTrait(TinkerTraits.magnetic2, HEAD);
        IRONWOOD.setCastable(true).setCraftable(false);

        FROZEN.addItem(ItemRegister.FROZEN_CORE);
        FROZEN.setRepresentativeItem(ItemRegister.FROZEN_CORE);
        FROZEN.registerTraits();

        AURORA.setCastable(false).setCraftable(false);
        AURORA.setRepresentativeItem(ItemRegister.AURORA_CORE);
        AURORA.addTrait(SHIFTING).addTrait(WONDERBREAKER);

        if(IntegrationHelper.isLoadedTough)
            FROZEN.addTrait(COOL, HEAD).addTrait(COOL, BOW).addTrait(COOL, SHAFT);

        TConstruct.nagascale.addTrait(TConstruct.synergy).addTrait(TinkerTraits.fractured);
    }


    public static void TweakTiCWithTaN(){
        if(!IntegrationHelper.isLoadedTough)
            return;
        TinkerMaterials.prismarine.addTrait(COOL).addTrait(COOL, HEAD);
        TinkerMaterials.firewood.addTrait(WARM);
        if(IntegrationHelper.isLoadedTwilight){
            TConstruct.fierymetal.addTrait(WARM);
        }

    }
}
