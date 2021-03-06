package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.jeimodule.RecipeLimbo;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.BasaltProvider;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RepairRecipe;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.WonderbreakingRecipe;
import koldunec.vint.tileentities.containers.ContainerTower;
import koldunec.vint.compatibility.Sidemod_Items;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class TwilightTransmutationsIntegration {
    static DefaultConsumer CONSUMER = TwilightTransmutationsData.CONSUMER;
    static Defaulter CARMINITE = new Defaulter(TFItems.carminite);

    public static void initIntegration(){
        initQuark();
        initNatura();
        initTinkers();
        initEbob();
        initRandom();

        if(IntegrationHelper.isLoadedPrimitive){
            CONSUMER.update(Item.getByNameOrId("primitivemobs:camouflage_dye"));
            CONSUMER.time=500;
            CONSUMER.register(Blocks.STONE, ItemRegister.PAINT_TRANSMUTATOR);
        }


        if(IntegrationHelper.isLoaded("conduit")){
            Defaulter CONDUIT = new Defaulter(Sidemod_Items.ConduitHeart());
            ContainerTower.CATALYSTS_FOR_TRANSFER.put(CONDUIT.catalyst, 0);

            CONDUIT.register(Items.GLOWSTONE_DUST, Items.PRISMARINE_CRYSTALS);
            CONDUIT.register(Items.QUARTZ, Items.PRISMARINE_SHARD);
            CONDUIT.register(Blocks.NETHER_WART_BLOCK, Blocks.SPONGE);
            CONDUIT.register(BlockRegister.WARPED_WART, Blocks.SPONGE);
            CONDUIT.register(Sidemod_Items.Focus(), Sidemod_Items.ConduitShell());
            CONDUIT.register(Items.PORKCHOP, Items.FISH);
            CONDUIT.register(Items.COOKED_PORKCHOP, Items.COOKED_FISH);
            if(IntegrationHelper.isLoadedTinkers) {
                Item i = Item.getByNameOrId(IntegrationHelper.idTinker+":edible");
                CONDUIT.register(i, 13, i, 20);   // pig jerky to fish jerky
                CONDUIT.register(i, Items.FISH, 2);         // bacon to clownfish
            }
            if(IntegrationHelper.isLoadedNatura){
                Item i = Item.getByNameOrId("natura:edibles");
                CONDUIT.register(i, Items.FISH, 1);         // imp meat to salmon
                CONDUIT.register(i, 1, Items.COOKED_FISH, 1); // same but cooked
            }

            CONDUIT.catalyst = TFItems.lamp_of_cinders;    // NO MORE CONDUIT
            CONDUIT.time = 250;
            CONDUIT.register(ItemRegister.FROZEN_CORE, Sidemod_Items.ConduitHeart());
        }

        if(IntegrationHelper.isLoadedFuture){
            Defaulter ICE = new Defaulter(ItemRegister.FROZEN_CORE);
            ICE.register(Blocks.PACKED_ICE, Sidemod_Items.BlueIce());

            Item trident = Sidemod_Items.getFuture("trident");
            RepairRecipe.BuildAndJEI_AndSave(trident, new ItemStack(Items.PRISMARINE_SHARD), 25);
            RepairRecipe.BuildAndJEI_AndSave(trident, new ItemStack(Items.PRISMARINE_CRYSTALS), 50);
            RepairRecipe.BuildAndJEI_AndSave(trident, new ItemStack(ItemRegister.FROZEN_CORE), 50);
            if(IntegrationHelper.isLoaded("conduit"))
                RepairRecipe.BuildAndJEI_AndSave(trident, new ItemStack(Sidemod_Items.ConduitShell()), 250);

            RecipeInput in = new RecipeInput(Items.LAVA_BUCKET, Sidemod_Items.BlueIce());
            RecipeOutput out = new BasaltProvider(BlockRegister.BASALT_RAW, 50);
            TransmutationsRegister.RECIPES_1.put(in, out);
            if(IntegrationHelper.isLoadedJEI)
                new RecipeLimbo.CobbleGenRecipe(in, out);
        }

    }

    private static void initQuark(){
        if(!IntegrationHelper.isLoadedQuark)
            return;
        Defaulter TRANSFORM = new Defaulter(TFBlocks.magic_log_core, 1);
        Defaulter DIAMONDCORE = new Defaulter(Item.getByNameOrId("quark:diamond_heart"));
        Defaulter DRAGON = new Defaulter(Items.DRAGON_BREATH);

        DRAGON.register(Items.QUARTZ, Item.getByNameOrId("quark:biotite"));

        TRANSFORM.register(Item.getByNameOrId("quark:root"), TFItems.liveroot);
        for(int i=0; i<3; ++i)
            TRANSFORM.register(Item.getByNameOrId("quark:root_flower"), i, TFItems.liveroot);

        Item crystal = Item.getByNameOrId("quark:crystal");
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 0,  crystal, 0);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 15, crystal, 8);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 14, crystal, 1);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 1,  crystal, 2);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 4,  crystal, 3);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 13, crystal, 4);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 3,  crystal, 5);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 11, crystal, 6);
        DIAMONDCORE.register(Blocks.CONCRETE_POWDER, 10, crystal, 7);

        CONSUMER.update(Blocks.NETHERRACK);
        CONSUMER.register(TFBlocks.fire_jet, Block.getBlockFromName("quark:smoker"));
        TRANSFORM.register(Block.getBlockFromName("quark:smoker"), TFBlocks.fire_jet);
        CARMINITE.register(Block.getBlockFromName("quark:smoker"), TFBlocks.fire_jet);
        CARMINITE.register(Block.getBlockFromName("quark:glowshroom"), TFBlocks.twilight_plant, 4);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(DIAMONDCORE.catalyst, 0);
    }

    private static void initNatura(){
        if(!IntegrationHelper.isLoadedNatura)
            return;
        Block log = Block.getBlockFromName("natura:overworld_logs");
        Block log2 = Block.getBlockFromName("natura:overworld_logs2");
        Block log3 = Block.getBlockFromName("natura:redwood_logs");
        Block sap = Block.getBlockFromName("natura:overworld_sapling");
        Block sap2 = Block.getBlockFromName("natura:overworld_sapling2");
        Block sap3 = Block.getBlockFromName("natura:nether_sapling");
        Defaulter CRYSTAL = new Defaulter(ItemRegister.NETHER_CRYSTAL);
        Defaulter CINDER = new Defaulter(TFItems.lamp_of_cinders);
        Defaulter POTAT = new Defaulter(ItemRegister.GOLDEN_POTATO);

        for(int i=0; i<3; ++i)
            CARMINITE.register(Block.getBlockFromName("natura:nether_glowshroom"), i, TFBlocks.twilight_plant, 4);

        CRYSTAL.register(Blocks.NETHERRACK, Item.getByNameOrId("natura:nether_heat_sand"));
        CINDER.register(Blocks.ICE, Block.getBlockFromName("natura:clouds"));
        CINDER.register(Blocks.PACKED_ICE, Block.getBlockFromName("natura:clouds"));
        POTAT.register(BlockRegister.RED_NY_LOG, log, 2);
        POTAT.register(BlockRegister.BLUE_NY_LOG, log, 2);  // amaranth
        POTAT.register(Blocks.LOG, log, 0);                 // maple
        POTAT.register(Blocks.LOG, 1, log, 1);    // silverbell
        POTAT.register(Blocks.LOG, 3, log, 3);    // tigerwood
        POTAT.register(Blocks.LOG2, log2, 1);               // eucalyptus
        POTAT.register(TFBlocks.twilight_log, 2, log2, 0); // willow
        POTAT.register(Blocks.LOG, 2, log2, 2);   // hopseed

        POTAT.register(Blocks.LOG2, 1, log3, 2);
        POTAT.register(TFBlocks.twilight_log, 3, log3);
        POTAT.register(TFBlocks.tower_wood, log3, 1);

        CONSUMER.update(TFItems.torchberries);
        CONSUMER.register(Blocks.LOG, 2, log2, 3);
        CONSUMER.register(Blocks.SAPLING, 2, sap2, 3);

        POTAT.register(Blocks.SAPLING, sap, 0);                // maple
        POTAT.register(Blocks.SAPLING, 1, sap, 1);   // silverbell
        POTAT.register(Blocks.SAPLING, 3, sap, 3);   // tigerwood
        POTAT.register(Blocks.SAPLING, 4, sap2, 1);  // eucalyptus
        POTAT.register(Blocks.SAPLING, 2, sap2, 0);  // willow
        POTAT.register(Blocks.TALLGRASS, 1, sap2, 2);// hopseed

        CONSUMER.update(ItemRegister.POTION_MIX);
        CONSUMER.register(Blocks.SAPLING, 3, sap, 2);  // amaranth

        if(IntegrationHelper.isLoadedTinkers){
            CONSUMER.update(Item.getByNameOrId("tconstruct:slime_congealed"), 3);
            CONSUMER.register(sap3, Block.getBlockFromName("natura:nether_sapling2"));
            CONSUMER.register(BlockRegister.FRESH_DEBRIS, Block.getBlockFromName("natura:nether_logs2"));
            CONSUMER.register(Block.getBlockFromName("natura:nether_logs"), 0, Block.getBlockFromName("natura:nether_logs2"), 15);
            CONSUMER.register(Block.getBlockFromName("natura:nether_logs"), 1, Block.getBlockFromName("natura:nether_logs2"), 15);
            CONSUMER.register(Block.getBlockFromName("natura:nether_logs"), 2, Block.getBlockFromName("natura:nether_logs2"), 15);
        }

        CONSUMER.update(ItemRegister.SOUL_FRUIT);
        CONSUMER.register(sap3, 1, sap3);
        CONSUMER.register(sap3, 2, sap3);


        Item food = Item.getByNameOrId("natura:edibles");
        CONSUMER.update(food, 10);
        ContainerTower.CATALYSTS_FOR_TRANSFER.put(food, 10);
        CONSUMER.register(Blocks.DIRT, Block.getBlockFromName("natura:nether_tainted_soil"));
        CONSUMER.register(Blocks.VINE, Block.getBlockFromName("natura:nether_thorn_vines"));
        CONSUMER.register(food, 2, Item.getByNameOrId("natura:overworld_berrybush_raspberry"));
        CONSUMER.register(food, 3, Item.getByNameOrId("natura:overworld_berrybush_blueberry"));
        CONSUMER.register(food, 4, Item.getByNameOrId("natura:overworld_berrybush_blackberry"));
        CONSUMER.register(food, 5, Item.getByNameOrId("natura:overworld_berrybush_maloberry"));
        CONSUMER.register(food, 6, Item.getByNameOrId("natura:nether_berrybush_blightberry"));
        CONSUMER.register(food, 7, Item.getByNameOrId("natura:nether_berrybush_duskberry"));
        CONSUMER.register(food, 8, Item.getByNameOrId("natura:nether_berrybush_skyberry"));
        CONSUMER.register(food, 9, Item.getByNameOrId("natura:nether_berrybush_stingberry"));
        CONSUMER.register(Blocks.RED_MUSHROOM, Items.NETHER_WART);
        CONSUMER.register(Blocks.BROWN_MUSHROOM, Items.NETHER_WART);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(ItemRegister.GOLDEN_POTATO, 0);
    }


    private static void initTinkers() {
        if(!IntegrationHelper.isLoadedTinkers)
            return;
        Item sapling = Item.getByNameOrId("tconstruct:slime_sapling");
        Item dirt = Item.getByNameOrId("tconstruct:slime_dirt");
        Item grass = Item.getByNameOrId("tconstruct:slime_grass");
        Item slime = Item.getByNameOrId("tconstruct:slime");

        CONSUMER.update(TFItems.fiery_blood);
        CONSUMER.register(sapling, 1, sapling, 2);
        CONSUMER.update(TFItems.fiery_tears);
        CONSUMER.register(sapling, 1, sapling, 2);

        CONSUMER.update(ItemRegister.VITASARIA);
        CONSUMER.register(Blocks.SLIME_BLOCK, dirt, 0);
        CONSUMER.register(slime, 1, dirt, 1);
        CONSUMER.update(Items.REDSTONE);
        CONSUMER.register(dirt, 1, dirt, 2);
        CONSUMER.update(ItemRegister.POTION_MIX);
        CONSUMER.register(dirt, 1, dirt, 3);
        CONSUMER.register(dirt, 2, dirt, 3);
        CONSUMER.update(ItemRegister.VITASARIA_SEEDS);
        CONSUMER.register(dirt, 0, grass, 1);
        CONSUMER.register(dirt, 1, grass, 2);
        CONSUMER.register(dirt, 2, grass, 8);
        CONSUMER.register(dirt, 3, grass, 14);

        TransmutationsRegister.RECIPES_2.add(RepairRecipe.BuildAndJEI(Item.getByNameOrId("tconstruct:moms_spaghetti"), new ItemStack(TFItems.maze_wafer), 25));

        TransmutationsRegister.RECIPES_2.add(new WonderbreakingRecipe());
        WonderbreakingRecipe.init();
    }

    private static void initEbob(){
        if(!IntegrationHelper.isLoaded("ebwizardry"))
            return;
        CONSUMER.update(Item.getByNameOrId("ebwizardry:blank_scroll"));
        Defaulter DIAMOND = new Defaulter(Item.getByNameOrId("ebwizardry:astral_diamond"));
        ContainerTower.CATALYSTS_FOR_TRANSFER.put(DIAMOND.catalyst, 0);
        Item crystal = Item.getByNameOrId("ebwizardry:magic_crystal");
        CONSUMER.register(TFItems.magic_beans, Item.getByNameOrId("ebwizardry:blast_upgrade"));

        DIAMOND.register(ItemRegister.NETHER_CRYSTAL, crystal, 1);
        DIAMOND.register(Blocks.ICE, crystal, 2);
        DIAMOND.register(Blocks.PACKED_ICE, crystal, 2);
        DIAMOND.register(TFItems.borer_essence, crystal, 4);
        DIAMOND.register(Items.EMERALD, crystal, 5);
        DIAMOND.register(Items.ENDER_PEARL, crystal, 6);
        DIAMOND.register(TFItems.charm_of_life_1, crystal, 7);

        DIAMOND.isThunder = true;
        DIAMOND.register(crystal, crystal, 3);
    }

    private static void initRandom(){
        if(!IntegrationHelper.isLoadedRandomThings)
            return;
        Block biome = Block.getBlockFromName("randomthings:biomestone");
        CONSUMER.update(TFBlocks.huge_stalk);
        CONSUMER.register(Blocks.COBBLESTONE, biome);
        CONSUMER.register(Blocks.STONE, biome, 1);
        CONSUMER.register(Blocks.STONEBRICK, biome, 2);
        CONSUMER.register(Blocks.STONEBRICK, 2, biome, 3);
        CONSUMER.register(Blocks.STONEBRICK, 3, biome, 4);
        CONSUMER.register(Blocks.GLASS, Block.getBlockFromName("randomthings:biomeglass"));
        CONSUMER.update(BlockRegister.GREEN_NY_LOG);
        CONSUMER.register(Blocks.CHEST, Block.getBlockFromName("randomthings:specialchest"));
        CARMINITE.register(Block.getBlockFromName("randomthings:glowingmushroom"), TFBlocks.twilight_plant, 4);

    }
}
