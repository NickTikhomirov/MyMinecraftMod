package koldunec.vint.objectbuilders;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;

public class OtherBuilder {
    public static LootPool LootPoolBuilder(LootEntry entry, RandomValueRange range, String name){
        return new LootPool(
            new LootEntry[]{entry},
            new LootCondition[0],
            range,
            new RandomValueRange(0,0),
            name
        );
    }

    public static LootPool LootPoolBuilder(LootEntry entry, int count, String name){
        return LootPoolBuilder(entry, new RandomValueRange(count, count), name);
    }

    public static LootEntry LootEntryBuilder(Item i, int weight, int count_min, int count_max, String name){
        return new LootEntryItem(
                i,
                weight,
                0,
                new LootFunction[]{setCount(count_min, count_max)},
                new LootCondition[0],
                name
        );
    }

    public static LootEntry LootEntryBuilder(Item i, int meta, int weight, int count_min, int count_max, String name){
        return new LootEntryItem(
                i,
                weight,
                0,
                new LootFunction[]{setCount(count_min, count_max), setMeta(meta)},
                new LootCondition[0],
                name
        );
    }


   // String-builders
    public static LootEntry LootEntryBuilder(String i, int weight, int count_min, int count_max, String name){
        return LootEntryBuilder(Item.getByNameOrId(i),weight,count_min,count_max,name);
    }

    public static LootEntry LootEntryBuilder(String i, int meta, int weight, int count_min, int count_max, String name){
        return LootEntryBuilder(Item.getByNameOrId(i),meta, weight,count_min,count_max,name);
    }


    // Loot functions
    public static SetMetadata setMeta(int i){
        return new SetMetadata(new LootCondition[0],new RandomValueRange(i,i));
    }

    public static SetCount setCount(int i, int j){
        return new SetCount(new LootCondition[0],new RandomValueRange(i,j));
    }
}