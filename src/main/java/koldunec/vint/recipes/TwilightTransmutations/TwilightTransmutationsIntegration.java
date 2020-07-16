package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.ROutputAurora;
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

    public static void initIntegration(){
        initQuark();
        initNatura();
        initTinkers();
        initEbob();

        if(IntegrationHelper.isLoadedPrimitive){
            TwilightTransmutationsData.CONSUMER.update(Item.getByNameOrId("primitivemobs:camouflage_dye"));
            TwilightTransmutationsData.CONSUMER.time=500;
            TwilightTransmutationsData.CONSUMER.register(Blocks.STONE, ItemRegister.PAINT_TRANSMUTATOR);
        }

        if(IntegrationHelper.isLoadedCharm){
            TransmutationsRegister.put(
                    new RecipeInput(new ItemStack(Item.getByNameOrId("charm:charged_emerald")), new ItemStack(ItemRegister.FROZEN_CORE)),
                    new ROutputAurora()
            );
        }

        if(IntegrationHelper.isLoaded("conduit")){
            Defaulter CONDUIT = new Defaulter(Sidemod_Items.ConduitHeart());

            CONDUIT.register(Items.GLOWSTONE_DUST, Items.PRISMARINE_CRYSTALS);
            CONDUIT.register(Items.QUARTZ, Items.PRISMARINE_SHARD);
            CONDUIT.register(Blocks.NETHER_WART_BLOCK, Blocks.SPONGE);
            CONDUIT.register(BlockRegister.WARPED_WART, Blocks.SPONGE);
            CONDUIT.register(Sidemod_Items.Focus(), Sidemod_Items.ConduitShell());

            ContainerTower.CATALYSTS_FOR_TRANSFER.put(CONDUIT.catalyst, 0);

            CONDUIT.catalyst = TFItems.lamp_of_cinders;    // NO MORE CONDUIT
            CONDUIT.time = 250;
            CONDUIT.register(ItemRegister.AURORA_CORE, Sidemod_Items.ConduitHeart());
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
        DefaultConsumer CONSUMER = TwilightTransmutationsData.CONSUMER;

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

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(ItemRegister.GOLDEN_POTATO, 0);
    }


    private static void initTinkers() {
        if(!IntegrationHelper.isLoadedTinkers)
            return;
        Defaulter DRAGON = new Defaulter(Items.DRAGON_BREATH);
        Item sapling = Item.getByNameOrId("tconstruct:slime_sapling");
        Item dirt = Item.getByNameOrId("tconstruct:slime_dirt");
        Item grass = Item.getByNameOrId("tconstruct:slime_grass");
        Item slime = Item.getByNameOrId("tconstruct:slime");
        DRAGON.register(sapling, 1, sapling, 2);

        DefaultConsumer CONSUMER = TwilightTransmutationsData.CONSUMER;

        CONSUMER.update(Items.GOLDEN_APPLE, 1);
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
    }

    public static void initEbob(){
        if(!IntegrationHelper.isLoaded("ebwizardry"))
            return;
        DefaultConsumer CONSUMER = TwilightTransmutationsData.CONSUMER;
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
}
