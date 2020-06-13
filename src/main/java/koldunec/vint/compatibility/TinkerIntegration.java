package koldunec.vint.compatibility;

import koldunec.vint.compatibility.traits.*;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.IntegrationHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;
import twilightforest.item.TFItems;


import static koldunec.vint.helpers.TechHelper.getColor;

public class TinkerIntegration {
    public static Material CARMINITE = new Material("carminite", getColor(154F,0,0));
    public static Material IRONWOOD = new Material("ironwood", getColor(133F,137F,52F));
    public static Material MAZESTONE = new Material("mazestone", getColor(110F,122,109));
    public static Material NETHER_CACTUS = new Material("nether_cactus",getColor(210,210,0));
    public static Material CRIMSON_LOG = new Material("crimson_log", getColor(123F,0F,0F));
    public static Material WARPED_LOG = new Material("warped_log", getColor(22F,97F,91F));
    public static Material BAMBOO = new Material("bamboo", getColor(130,168,89));
    public static AbstractTrait LEFT_HAND_RULE = new LeftHandRule();
    public static AbstractTrait BORING = new Boring();
    public static AbstractTrait PRIMAL = new Primal();
    public static AbstractTrait MAZEY = new Mazey();
    public static AbstractTrait REDPOWER = new RedPower();
    public static AbstractTrait SLIMECUTTER = new SlimeCutter();
    public static Fluid IRONWOOD_JIJA = null;

    public static void preInit(){
        preInitJija();
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

            TinkerRegistry.addMaterialStats(IRONWOOD,
                    new HeadMaterialStats(204, 6F, 4F, 2),
                    new ExtraMaterialStats(50),
                    new HandleMaterialStats(0.85F, 60),
                    new BowMaterialStats(0.5F, 1.5F,7)
            );


            TinkerRegistry.integrate(CARMINITE).preInit();
            TinkerRegistry.integrate(MAZESTONE).preInit();
            TinkerRegistry.integrate(IRONWOOD,IRONWOOD_JIJA,"Ironwood").preInit();
        }
        TinkerRegistry.integrate(NETHER_CACTUS).preInit();
        TinkerRegistry.integrate(CRIMSON_LOG).preInit();
        TinkerRegistry.integrate(WARPED_LOG).preInit();

        if(IntegrationHelper.isLoadedFuture){
            TinkerRegistry.addMaterialStats(BAMBOO,
                    new HandleMaterialStats(1.2F, 50),
                    new ArrowShaftMaterialStats(1.3F,50)
            );

            TinkerRegistry.integrate(BAMBOO).preInit();
        }
    }


    public static void init(){
        String HEAD = MaterialTypes.HEAD;
        String EXTRA = MaterialTypes.EXTRA;
        String HANDLE = MaterialTypes.HANDLE;
        String SHAFT = MaterialTypes.SHAFT;
        String BOW = MaterialTypes.BOW;

        if(IntegrationHelper.isLoadedTwilight) {
            CARMINITE.addItem(new ItemStack(Item.getByNameOrId("twilightforest:carminite"), 1), 1, 144);
            CARMINITE.setRepresentativeItem(Item.getByNameOrId("twilightforest:carminite"));
            CARMINITE.setCraftable(true).setCastable(false);
            CARMINITE.addTrait(TConstruct.twilit);
            CARMINITE.addTrait(TConstruct.twilit, HEAD).addTrait(BORING, HEAD);
            CARMINITE.addTrait(TConstruct.twilit, HANDLE).addTrait(TinkerTraits.lightweight, HANDLE).addTrait(TinkerTraits.unnatural, HANDLE);

            MAZESTONE.addItem(Item.getByNameOrId("twilightforest:maze_stone"));
            MAZESTONE.setRepresentativeItem(Block.getBlockFromName("twilightforest:maze_stone"));
            MAZESTONE.setCraftable(true).setCastable(false);
            MAZESTONE.addTrait(LEFT_HAND_RULE, HEAD);
            MAZESTONE.addTrait(TinkerTraits.duritos, EXTRA);
            MAZESTONE.addTrait(MAZEY, HEAD).addTrait(MAZEY, EXTRA);
            MAZESTONE.addTrait(TConstruct.twilit, HEAD).addTrait(TConstruct.twilit, EXTRA);
            MAZESTONE.addTrait(TinkerTraits.heavy, HEAD).addTrait(TinkerTraits.heavy, EXTRA);

            IRONWOOD.addItem(TFItems.ironwood_ingot,1, 144);
            IRONWOOD.setRepresentativeItem(TFItems.ironwood_ingot);
            IRONWOOD.addTrait(TConstruct.twilit).addTrait(TinkerTraits.ecological).addTrait(TinkerTraits.magnetic);
            IRONWOOD.addTrait(TConstruct.twilit, HEAD).addTrait(TinkerTraits.ecological, HEAD).addTrait(TinkerTraits.magnetic2, HEAD);
            IRONWOOD.setCastable(true).setCraftable(false);

            TConstruct.nagascale.addTrait(TConstruct.synergy).addTrait(TinkerTraits.fractured);



            /*TConstruct.knightmetal
                    .addTrait(TConstruct.twilit,MaterialTypes.HANDLE)
                    .addTrait(TConstruct.valiant,MaterialTypes.HANDLE)
                    .addTrait(TinkerTraits.dense,MaterialTypes.HANDLE);*/
        }

        if(IntegrationHelper.isLoadedFuture){
            BAMBOO.addItem(Item.getByNameOrId(IntegrationHelper.idFuture+":bamboo"),1,144);
            BAMBOO.setRepresentativeItem(Item.getByNameOrId(IntegrationHelper.idFuture+":bamboo"));
            BAMBOO.setCraftable(true).setCastable(false);
            BAMBOO.addTrait(TinkerTraits.ecological, HANDLE).addTrait(SLIMECUTTER, HANDLE);
            BAMBOO.addTrait(TinkerTraits.fractured, SHAFT);
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


    public static void postInit(){
        if(IntegrationHelper.isLoadedTwilight){
            PRIMAL.addRecipeMatch(new RecipeMatch.ItemCombination(
                    1,
                    new ItemStack(Blocks.RED_MUSHROOM),
                    new ItemStack(Blocks.BROWN_MUSHROOM),
                    new ItemStack(Item.getByNameOrId(IntegrationHelper.idTwilight+":raw_meef"))
            ));
        }
    }


    private static void preInitJija(){
        IRONWOOD_JIJA = new Tinkerfluid("ironwood", IRONWOOD.materialTextColor);
        IRONWOOD_JIJA.setTemperature(1000).setLuminosity(15);
        FluidRegistry.registerFluid(IRONWOOD_JIJA);
        FluidRegistry.addBucketForFluid(IRONWOOD_JIJA);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("fluid", IRONWOOD_JIJA.getName()); // name of the fluid
        tag.setString("ore", "Ironwood"); // ore-suffix: ingotFoo, blockFoo, oreFoo,...
        tag.setBoolean("toolforge", true);
        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
    }
}
