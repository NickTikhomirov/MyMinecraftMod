package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.ROutputAurora;
import koldunec.vint.tileentities.containers.ContainerTower;
import koldunec.vint.utils.routers.Sidemod_Items;
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
        Defaulter CRYSTAL = new Defaulter(ItemRegister.NETHER_CRYSTAL);

        CRYSTAL.register(Blocks.NETHERRACK, Item.getByNameOrId("natura:nether_heat_sand"));
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

        TwilightTransmutationsData.CONSUMER.update(Items.GOLDEN_APPLE, 1);
        TwilightTransmutationsData.CONSUMER.register(sapling, 1, sapling, 2);

        TwilightTransmutationsData.CONSUMER.update(Items.BEETROOT);
        TwilightTransmutationsData.CONSUMER.register(Blocks.SLIME_BLOCK, dirt, 0);
        TwilightTransmutationsData.CONSUMER.register(slime, 1, dirt, 1);
        TwilightTransmutationsData.CONSUMER.register(slime, 2, dirt, 2);
        TwilightTransmutationsData.CONSUMER.register(slime, 3, dirt, 3);

    }


    public static void updateGlass(){
        TFBlocks.auroralized_glass.setHardness(0.3F);
    }
}
