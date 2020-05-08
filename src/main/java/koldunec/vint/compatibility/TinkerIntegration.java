package koldunec.vint.compatibility;

import koldunec.vint.compatibility.traits.*;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.TinkerTraits;
import slimeknights.tconstruct.tools.traits.TraitSlimey;
import twilightforest.compat.TConstruct;
import twilightforest.entity.EntityTFMazeSlime;


import static koldunec.vint.helpers.TechHelper.getColor;

public class TinkerIntegration {
    public static Material CARMINITE = new Material("carminite", getColor(154F,0,0));
    public static Material IRONWOOD = new Material("ironwood", getColor(154F,0,0));
    public static Material MAZESTONE = new Material("mazestone", getColor(110F,122,109));
    public static Material NETHER_CACTUS = new Material("nether_cactus",getColor(210,210,0));
    public static Material CRIMSON_LOG = new Material("crimson_log", getColor(123F,0F,0F));
    public static Material WARPED_LOG = new Material("warped_log", getColor(22F,97F,91F));
    public static AbstractTrait LEFT_HAND_RULE = new LeftHandRule();
    public static AbstractTrait BORING = new Boring();
    public static AbstractTrait SIXFEETS = new Sixfeets();
    public static AbstractTrait MAZEY = new Mazey();
    public static AbstractTrait REDPOWER = new RedPower();


    public static void preInit(){
        RedPower.initPerks();

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

        if(IntegrationHelper.isLoadedTwilight) {
            TinkerRegistry.addMaterialStats(CARMINITE,
                    new HeadMaterialStats(200, 11F, 7.5F, 2),
                    new ExtraMaterialStats(150),
                    new HandleMaterialStats(1.4F,5)
            );
            TinkerRegistry.addMaterialStats(MAZESTONE,
                    new HeadMaterialStats(255, 3F, 2F, 4),
                    new ExtraMaterialStats(340)
            );

            TinkerRegistry.integrate(CARMINITE).preInit();
            TinkerRegistry.integrate(MAZESTONE).preInit();
            TinkerRegistry.integrate(NETHER_CACTUS).preInit();
            TinkerRegistry.integrate(CRIMSON_LOG).preInit();
            TinkerRegistry.integrate(WARPED_LOG).preInit();
        }
    }


    public static void init(){
        if(IntegrationHelper.isLoadedTwilight) {
            CARMINITE.addItem(new ItemStack(Item.getByNameOrId("twilightforest:carminite"), 1), 1, 144);
            CARMINITE.setRepresentativeItem(Item.getByNameOrId("twilightforest:carminite"));
            CARMINITE.setCraftable(true).setCastable(false);
            CARMINITE.addTrait(TConstruct.twilit);
            CARMINITE.addTrait(TConstruct.twilit, MaterialTypes.HEAD).addTrait(BORING, MaterialTypes.HEAD);
            CARMINITE.addTrait(TConstruct.twilit, MaterialTypes.HANDLE).addTrait(TinkerTraits.lightweight, MaterialTypes.HANDLE).addTrait(TinkerTraits.unnatural, MaterialTypes.HANDLE);

            MAZESTONE.addItem(Item.getByNameOrId("twilightforest:maze_stone"));
            MAZESTONE.setRepresentativeItem(Block.getBlockFromName("twilightforest:maze_stone"));
            MAZESTONE.setCraftable(true).setCastable(false);
            MAZESTONE.addTrait(LEFT_HAND_RULE, MaterialTypes.HEAD);
            MAZESTONE.addTrait(TinkerTraits.duritos, MaterialTypes.EXTRA);
            MAZESTONE.addTrait(MAZEY, MaterialTypes.HEAD).addTrait(MAZEY, MaterialTypes.EXTRA);
            MAZESTONE.addTrait(TConstruct.twilit, MaterialTypes.HEAD).addTrait(TConstruct.twilit, MaterialTypes.EXTRA);
            MAZESTONE.addTrait(TinkerTraits.heavy, MaterialTypes.HEAD).addTrait(TinkerTraits.heavy, MaterialTypes.EXTRA);

            TConstruct.nagascale.addTrait(TConstruct.synergy).addTrait(TinkerTraits.fractured);
            /*TConstruct.knightmetal
                    .addTrait(TConstruct.twilit,MaterialTypes.HANDLE)
                    .addTrait(TConstruct.valiant,MaterialTypes.HANDLE)
                    .addTrait(TinkerTraits.dense,MaterialTypes.HANDLE);*/
        }

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
}
