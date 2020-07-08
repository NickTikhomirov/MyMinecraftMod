
package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.*;
import koldunec.vint.tileentities.containers.ContainerTower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

@SuppressWarnings("ConstantConditions")
public class TwilightTransmutationsData {
    public static DefaultConsumer CONSUMER = new DefaultConsumer(Items.AIR);

    public static void initCinder(){
        Defaulter CINDER = new Defaulter(TFItems.lamp_of_cinders);

        CINDER.register(Items.ROTTEN_FLESH, TFItems.raw_meef);
        CINDER.register(TFBlocks.twilight_sapling, 0, TFBlocks.twilight_sapling, 4);
        CINDER.register(Items.DRAGON_BREATH, TFItems.fiery_tears);

        CINDER.time = 250;
        CINDER.register(Blocks.CHEST, BlockRegister.STORE);

        CINDER.time = 500;
        CINDER.register(Blocks.CLAY, TFBlocks.maze_stone);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TFItems.lamp_of_cinders, 0);
    }


    public static void initCarminite(){
        Defaulter CARMINITE = new Defaulter(TFItems.carminite);

        CARMINITE.register(Blocks.COAL_BLOCK, TFBlocks.fire_jet,0);
        CARMINITE.register(Blocks.MAGMA, TFBlocks.fire_jet,3);
        CARMINITE.register(Blocks.DIRT, Blocks.DIRT,2);

        CARMINITE.register(Items.COOKED_BEEF, TFItems.experiment_115);
        CARMINITE.register(Items.COOKED_MUTTON, TFItems.experiment_115);
        CARMINITE.register(TFItems.cooked_meef, TFItems.experiment_115);
        CARMINITE.register(TFItems.cooked_venison, TFItems.experiment_115);

        CARMINITE.register(Blocks.WOOL, 0, BlockRegister.FAKE_IRON);
        CARMINITE.register(Blocks.WOOL, 4, BlockRegister.FAKE_GOLD);
        CARMINITE.register(Blocks.WOOL, 3, BlockRegister.FAKE_DIAMOND);
        CARMINITE.register(Blocks.WOOL, 7, BlockRegister.FAKE_NETHERITE);
        CARMINITE.register(Blocks.WOOL, 5, BlockRegister.FAKE_EMERALD);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TFItems.carminite, 0);
    }


    public static void initTransform(){
        Defaulter TRANSFORM = new Defaulter(TFBlocks.magic_log_core, 1);

        TRANSFORM.register(Items.COOKED_BEEF, TFItems.cooked_venison);
        TRANSFORM.register(Items.BEEF, TFItems.raw_venison);
        TRANSFORM.register(BlockRegister.STORE, TFItems.charm_of_keeping_2);
        TRANSFORM.register(TFItems.ironwood_ingot, TFItems.steeleaf_ingot);
        TRANSFORM.register(Blocks.WATERLILY, TFBlocks.huge_lilypad);
        TRANSFORM.register(TFBlocks.huge_lilypad, TFBlocks.huge_waterlily);

        TRANSFORM.register(Blocks.STONEBRICK, 0, TFBlocks.naga_stone, 1);
        TRANSFORM.register(Blocks.STONEBRICK, 3, TFBlocks.naga_stone, 0);
        TRANSFORM.register(Blocks.STONE_BRICK_STAIRS, TFBlocks.nagastone_stairs, 0);

        TRANSFORM.register(Blocks.SAPLING, 0, TFBlocks.twilight_sapling, 0);   //oak to sickly oak
        TRANSFORM.register(Blocks.SAPLING, 2, TFBlocks.twilight_sapling, 1);   //birch to canopy
        TRANSFORM.register(Blocks.SAPLING, 3, TFBlocks.twilight_sapling, 1);   //jungle to canopy
        TRANSFORM.register(Blocks.SAPLING, 4, TFBlocks.twilight_sapling, 2);   //acacia to mangrove

        TRANSFORM.time = 1000;
        TRANSFORM.register(TFBlocks.twilight_sapling, 2, TFBlocks.twilight_sapling, 6);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TRANSFORM.catalyst, 1);
    }


    public static void initDragonBreath(){
        Defaulter DRAGON = new Defaulter(Items.DRAGON_BREATH);

        DRAGON.time = 200;
        DRAGON.register(Blocks.STONE, Blocks.END_STONE);
        DRAGON.register(TFItems.peacock_fan, Items.ELYTRA);
    }


    public static void initIce(){
        Defaulter ICE_CATALYST = new Defaulter(ItemRegister.AURORA_CORE);

        ICE_CATALYST.register(Blocks.SNOW, Blocks.PACKED_ICE);
        ICE_CATALYST.register(Blocks.STONEBRICK, TFBlocks.aurora_block);
        ICE_CATALYST.register(Blocks.STONEBRICK, 3, TFBlocks.aurora_block);
        ICE_CATALYST.register(Blocks.GLASS, TFBlocks.auroralized_glass);
        ICE_CATALYST.register(Items.FIRE_CHARGE, TFItems.ice_bomb);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(ItemRegister.AURORA_CORE, 0);
    }


    public static void initNether(){
        Defaulter CRYSTAL = new Defaulter(ItemRegister.NETHER_CRYSTAL);
        Defaulter STAR = new Defaulter(Items.NETHER_STAR);

        CRYSTAL.register(Blocks.NETHER_WART_BLOCK, Blocks.SOUL_SAND);
        STAR.register(Items.DYE, 4, Items.QUARTZ);
        STAR.register(Items.QUARTZ,Items.DYE, 4 );

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(ItemRegister.NETHER_CRYSTAL, 0);
        ContainerTower.CATALYSTS_FOR_TRANSFER.put(Items.NETHER_STAR, 0);
    }

    public static void initWoods(ItemStack i, int time){
        Defaulter CATALYST = new Defaulter(i.getItem(), i.getMetadata());
        CATALYST.time = time;

        CATALYST.register(TFBlocks.twilight_log, 0, TFBlocks.magic_log,0);
        CATALYST.register(TFBlocks.twilight_log, 1, TFBlocks.magic_log, 3);
        CATALYST.register(TFBlocks.twilight_log, 2, TFBlocks.magic_log, 2);
        CATALYST.register(TFBlocks.magic_log, 2, TFBlocks.magic_log, 1);
        CATALYST.register(TFBlocks.twilight_log, 3, TFBlocks.cinder_log);
    }

    public static void initRepairs(){
        TransmutationsRegister.RECIPES_2.add(new RepairRecipe(TFItems.crumble_horn, new ItemStack(TFBlocks.magic_log, 1, 2), 32));
        TransmutationsRegister.RECIPES_2.add(new RepairRecipe(Items.ELYTRA, new ItemStack(Items.RABBIT), 27));
        if(IntegrationHelper.isLoadedNatura)
            TransmutationsRegister.RECIPES_2.add(new RepairRecipe(Items.ELYTRA, new ItemStack(Item.getByNameOrId("natura:materials"), 1, 6), 108));
        TransmutationsRegister.RECIPES_2.add(new DurabilityConversion());

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TFItems.crumble_horn, -1);
        ContainerTower.CATALYSTS_FOR_TRANSFER.put(Items.ELYTRA, -1);
        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TFItems.phantom_helmet, -1);
        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TFItems.phantom_chestplate, -1);
    }

    public static void initOthers(){
        CONSUMER.update(TFItems.borer_essence);
        CONSUMER.time = 200;
        CONSUMER.result_amount = 2;
        CONSUMER.register(Blocks.IRON_ORE, Items.IRON_INGOT);
        CONSUMER.register(Blocks.GOLD_ORE, Items.GOLD_INGOT);

        CONSUMER.update(Items.GOLDEN_APPLE, 1);
        CONSUMER.register(TFItems.charm_of_life_2, Items.TOTEM_OF_UNDYING);
    }
}
