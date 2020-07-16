package koldunec.vint.compatibility.TwilightForest;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

import static koldunec.vint.objectbuilders.LootObjectsBuilder.LootEntryBuilder;
import static koldunec.vint.objectbuilders.LootObjectsBuilder.LootPoolBuilder;

public class MainTFModule {

    public static void processTable(LootTableLoadEvent e){
        LootTable table = e.getTable();
        String path = e.getName().getResourcePath();
        switch(path){
            case "useless":
                insertLootUseless(table);
                break;
            case "entities/naga":
                insertItemAsPool(table, TFItems.steeleaf_ingot, 0 , 2, 3);
                break;
            case "entities/ice_exploder":         // todo looting?
                insertItemAsPool(table, ItemRegister.FROZEN_CORE, 0 , 1, 1);
                break;
            case "entities/ice_shooter":           // todo looting?
                insertItemAsPool(table, ItemRegister.FROZEN_CORE, 0 , 1, 1);
                break;
            case "entities/snow_queen":
                insertItemAsPool(table, ItemRegister.FROZEN_CORE, 0 , 3, 4);
                insertItemAsPool(table, ItemRegister.AURORA_CORE, 0 , 1, 1);
                break;

        }
    }


    public static void insertLootUseless(LootTable table){
        TFLootHelper USELESS = new TFLootHelper(table);
        USELESS.insertLootMain(LootEntryBuilder(ItemRegister.TRANSFORMATION_DUST, 2, 4, 7, "vint_trans"));
        if(IntegrationHelper.isLoadedFuture){
            USELESS.insertLootMain(LootEntryBuilder(IntegrationHelper.idFuture+"bamboo", 1, 2,3, "bamboo"));
        }
    }

    public static void insertItemAsPool(LootTable table, Item i, int meta, int min, int max){
        table.addPool(
                LootPoolBuilder(
                        LootEntryBuilder(i, meta, 10, min, max, "vint_"+i.getRegistryName()), 1, "vint_pool_"+i.getRegistryName()
                )
        );
    }

    public static void updateGlass(){
        TFBlocks.auroralized_glass.setHardness(0.3F);
    }
}
