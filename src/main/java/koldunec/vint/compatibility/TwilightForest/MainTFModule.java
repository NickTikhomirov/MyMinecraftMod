package koldunec.vint.compatibility.TwilightForest;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Sidemod_Items;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.utils.EnumTwilightScrollTypes;
import koldunec.vint.vint;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import twilightforest.block.TFBlocks;
import twilightforest.compat.TConstruct;
import twilightforest.entity.EntityTFSlimeBeetle;
import twilightforest.entity.boss.EntityTFSnowQueen;
import twilightforest.item.TFItems;
import twilightforest.world.TFWorld;

import static koldunec.vint.objectbuilders.LootObjectsBuilder.LootEntryBuilder;
import static koldunec.vint.objectbuilders.LootObjectsBuilder.LootPoolBuilder;

public class MainTFModule {

    public static void processTable(LootTableLoadEvent e){
        LootTable table = e.getTable();
        String path = e.getName().getResourcePath();
        switch(path){
            case "structures/useless":
                insertLootUseless(e);
                break;
            case "entities/naga":
                insertItemAsPool(table, TFItems.steeleaf_ingot, 0 , 2, 3);
                break;
            case "entities/ice_shooter":           // todo looting?
                insertItemAsPool(table, ItemRegister.FROZEN_CORE, 0 , 1, 1);
                break;
            case "entities/snow_queen":
                insertItemAsPool(table, ItemRegister.FROZEN_CORE, 0 , 3, 4);
                if(IntegrationHelper.isLoadedFuture)
                    insertItemAsPool(table, Sidemod_Items.BlueIce(), 0, 12, 16);
                break;
            case "structures/darktower_cache/common":
                insertLootDarktower_Common(table);
                break;
            case "structures/darktower_cache/rare":
                insertLootDarktower_Rare(table);
                break;
        }
    }


    public static void insertLootUseless(LootTableLoadEvent e){
        LootTable table = e.getTable();
        TFLootHelper USELESS = new TFLootHelper(table);
        USELESS.insertLootMain(LootEntryBuilder(ItemRegister.TRANSFORMATION_DUST, 2, 4, 7, "vint_trans"));
        USELESS.insertLootMain(LootEntryBuilder(Items.BONE, 1, 2, 5, "minecraft:bone"));
        USELESS.insertLootMain(LootEntryBuilder(Items.ROTTEN_FLESH, 1, 2, 5, "minecraft:rotten_flesh"));
        USELESS.insertLootMain(LootEntryBuilder(Items.SPIDER_EYE, 1, 2, 3, "minecraft:spider_eye"));
        USELESS.insertLootMain(LootEntryBuilder(Item.getItemFromBlock(Blocks.LOG), 1, 5, 7, "minecraft:log"));
        USELESS.main.removeEntry("minecraft:feather");
        USELESS.main.removeEntry("minecraft:flower_pot");
        USELESS.main.removeEntry("minecraft:wheat_seeds");
        USELESS.main.removeEntry("minecraft:red_flower");
        USELESS.main.removeEntry("minecraft:yellow_flower");
        USELESS.main.setRolls(new RandomValueRange(2,3));
        if(IntegrationHelper.isLoadedFuture){
            USELESS.insertLootMain(LootEntryBuilder(IntegrationHelper.idFuture+":bamboo", 1, 2,3, "bamboo"));
        }
    }

    public static void insertLootDarktower_Common(LootTable table){
        TFLootHelper TABLE = new TFLootHelper(table);
        TABLE.main.removeEntry("minecraft:stick");
        TABLE.insertLootMain(LootEntryBuilder(TFItems.borer_essence, 1, 4, 8, "tf_borer"));
        TABLE.insertLootMain(LootEntryBuilder(ItemRegister.TRANSFORMATION_DUST, 1, 7, 12, "vint_transform"));
    }

    public static void insertLootDarktower_Rare(LootTable table){
        TFLootHelper TABLE = new TFLootHelper(table);
        TABLE.insertLootMain(LootEntryBuilder(ItemRegister.BORER_REED, 1, 1, 1, "vint_borer_reed"));
    }

    public static void insertItemAsPool(LootTable table, Item i, int meta, int min, int max){
        if(i==null)
            return;
        table.addPool(
                LootPoolBuilder(
                        LootEntryBuilder(i, meta, 10, min, max, "vint_"+i.getRegistryName()), 1, "vint_pool_"+i.getRegistryName()
                )
        );
    }

    public static void updateGlass(){
        TFBlocks.auroralized_glass.setHardness(0.3F);
    }

    public static boolean checkBeetle(Entity e){
        return e instanceof EntityTFSlimeBeetle;
    }

    public static void checkQueenAndPass(LivingDropsEvent e){
        int bound = 2;
        for(int i=0; i<bound; ++i)
            e.getEntity().entityDropItem(
                    new ItemStack(
                            ItemRegister.SCROLL_TWILIGHT,
                            1,
                            vint.random.nextInt(EnumTwilightScrollTypes.values().length)),
                    0.5F);
        if(e.getEntityLiving() instanceof EntityTFSnowQueen)
            TinkerIntegration.ProcessQueen(e);
    }

    public static boolean isTF(World w){
        return TFWorld.isTwilightForest(w);
    }

    public static Object getTwilitTrait(){
        return TConstruct.twilit;
    }
}
