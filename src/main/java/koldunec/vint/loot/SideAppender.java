package koldunec.vint.loot;

import koldunec.vint.objectbuilders.OtherBuilder;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;

public class SideAppender {

    public static void flameSpewerInjection(LootTable table){
        table.addPool(OtherBuilder.LootPoolBuilder(
                OtherBuilder.LootEntryBuilder(
                       "tconstruct:edible",
                       4,
                       1,
                       2,
                       4,
                       "loottable:slime"
                ),
                1,
                "slime"
        ));
    }



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

}
