package koldunec.vint.compatibility.Tinker;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.TinkerBook.ModifierAppender;
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

    public static void addStatsVint(){
        TinkerRegistry.addMaterialStats(NETHER_CACTUS,
                new HeadMaterialStats(256, 6F, 4F, 1),
                new ExtraMaterialStats(65),
                new HandleMaterialStats(0.99F,20)
        );

        TinkerRegistry.addMaterialStats(CRIMSON_LOG,
                new HeadMaterialStats(256, 6F, 4F, 1),
                new ExtraMaterialStats(65),
                new HandleMaterialStats(0.99F,20)
        );
        TinkerRegistry.addMaterialStats(WARPED_LOG,
                new HeadMaterialStats(256, 6F, 4F, 1),
                new ExtraMaterialStats(65),
                new HandleMaterialStats(0.99F,20)
        );

        TinkerRegistry.addMaterialStats(DEBRIS,
                new ExtraMaterialStats(128));


        TinkerRegistry.integrate(NETHER_CACTUS).preInit();
        TinkerRegistry.integrate(CRIMSON_LOG).preInit();
        TinkerRegistry.integrate(WARPED_LOG).preInit();
        TinkerRegistry.integrate(DEBRIS).preInit();
    }


    public static void addStatsTwilight(){
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        ModifierAppender.APPENDANTS.add(PRIMAL);

        TinkerRegistry.addMaterialStats(CARMINITE,
                new HeadMaterialStats(200, 11F, 7.5F, 2),
                new ExtraMaterialStats(150),
                new HandleMaterialStats(1.4F,5),
                new BowStringMaterialStats(1.4F)
        );
        TinkerRegistry.addMaterialStats(MAZESTONE,
                new HeadMaterialStats(255, 3F, 2F, 4),
                new ExtraMaterialStats(340)
        );

        TinkerRegistry.addMaterialStats(IRONWOOD,
                new HeadMaterialStats(204, 6F, 4F, 2),
                new ExtraMaterialStats(50),
                new HandleMaterialStats(0.85F, 60),
                new BowMaterialStats(0.5F, 1.5F,7)
        );
        TinkerRegistry.addMaterialStats(FROZEN,
                new HeadMaterialStats(333,0.1F,4F, 2),
                new BowMaterialStats(1.8F, 1F, 4),
                new ArrowShaftMaterialStats(1, 10));


        TinkerRegistry.integrate(CARMINITE).preInit();
        TinkerRegistry.integrate(MAZESTONE).preInit();
        TinkerRegistry.integrate(FROZEN).preInit();
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
        HONEY_CRYSTAL.addTrait(TinkerTraits.tasty).addTrait(BZZZ);
    }


    public static void ConfigureTwilight(){
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        CARMINITE.addItem(new ItemStack(Item.getByNameOrId("twilightforest:carminite"), 1), 1, 144);
        CARMINITE.setRepresentativeItem(Item.getByNameOrId("twilightforest:carminite"));
        CARMINITE.setCraftable(true).setCastable(false);
        CARMINITE.addTrait(TConstruct.twilit).addTrait(BZZZ);
        CARMINITE.addTrait(TConstruct.twilit, HEAD).addTrait(BORING, HEAD).addTrait(BZZZ, HEAD);
        CARMINITE.addTrait(TConstruct.twilit, HANDLE).addTrait(TinkerTraits.lightweight, HANDLE).addTrait(TinkerTraits.unnatural, HANDLE);

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
        FROZEN.setCastable(false).setCraftable(true);
        FROZEN.addTrait(TConstruct.twilit, HEAD).addTrait(TConstruct.twilit, BOW).addTrait(TConstruct.twilit, SHAFT);
        FROZEN.addTrait(TinkerTraits.coldblooded, HEAD).addTrait(TinkerTraits.coldblooded, BOW);
        FROZEN.addTrait(TinkerTraits.freezing, SHAFT);
        FROZEN.addTrait(ICE_QUEEN, BOW);

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
