package koldunec.vint.compatibility.TwilightForest;

import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;

public class TFLootHelper {
    public LootTable table;
    public LootPool main;

    public TFLootHelper(LootTable t){
        table = t;
        main = table.getPool("main");
    }

    public void insertLootMain(LootEntry e){
        main.addEntry(e);
    }


}
