package koldunec.vint.events;

import koldunec.vint.helpers.IntegrationHelper;
import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;

public class LootInjections {

    static IntegrationHelper integrationHelper = vint.integrationHelper;


    public static void llamaIsleIntegrations(LootTable table){
        if(integrationHelper.isLoaded("randomthings"))
            addDefault(
                    table.getPool("seeds"),
                    "randomthings:lotusseeds",
                    5,
                    new RandomValueRange(5,6),
                    "loottable:lotus"
            );

        if(integrationHelper.isLoaded("growthcraft_hops")){
            table.addPool(
                    new LootPool(
                            new LootEntry[]{
                                    new LootEntryItem(
                                            Item.getByNameOrId("growthcraft_hops:hop_seeds"),
                                            5,
                                            0,
                                            new LootFunction[]{setCount(5,6)},
                                            new LootCondition[0],
                                            "loottable:lotus")
                            },
                            new LootCondition[0],
                            new RandomValueRange(1,1),
                            new RandomValueRange(0,0),
                            "hops"
                    ));
        }
    }

    public static void flameSpewerInjection(LootTable table){
        table.addPool(new LootPool(
                new LootEntry[]{
                        new LootEntryItem(
                                Item.getByNameOrId("tconstruct:edible"),
                                1,0,
                                new LootFunction[]{
                                        setMeta(4),
                                        setCount(2,4)},
                                new LootCondition[0],
                                "loottable:slime")
                },
                new LootCondition[0],
                new RandomValueRange(1,1),
                new RandomValueRange(0,0),
                "slime"
        ));
    }




    // TOOLS

    public static void addDefault(LootPool pool, Item item, int weightin, RandomValueRange count, String entryname){
        pool.addEntry(new LootEntryItem(
                item,
                weightin,
                0,
                new LootFunction[]{new SetCount(new LootCondition[0], count)},
                new LootCondition[0],
                entryname)
        );
    }

    public static void addDefault(LootPool pool, String itemName, int weightin, RandomValueRange count, String entryname){
        addDefault(pool,Item.getByNameOrId(itemName),weightin,count,entryname);
    }

    public static SetMetadata setMeta(int i){
        return new SetMetadata(new LootCondition[0],new RandomValueRange(i,i));
    }
    public static SetCount setCount(int i, int j){
        return new SetCount(new LootCondition[0],new RandomValueRange(i,j));
    }

}
