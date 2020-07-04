package koldunec.vint.loot;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.objectbuilders.LootObjectsBuilder;
import net.minecraft.world.storage.loot.*;

public class VintAppender {

    public static void AppendLlama(LootTable table){
        boolean randomthings = IntegrationHelper.isLoaded("randomthings");
        boolean growcraft = IntegrationHelper.isLoaded("growthcraft_hops");

        if(randomthings)
            SideAppender.addDefault(
                    table.getPool("seeds"),
                    "randomthings:lotusseeds",
                    5,
                    new RandomValueRange(5,6),
                    "loottable:lotus"
            );

        if(growcraft){
            LootEntry entry = LootObjectsBuilder.LootEntryBuilder(
                    "growthcraft_hops:hop_seeds",
                    5,
                    5,
                    6,
                    "loottable:hops"
            );
            table.addPool(LootObjectsBuilder.LootPoolBuilder(entry, 1, "hops"));
        }

        if(IntegrationHelper.isLoadedTea){
            LootEntry entry = LootObjectsBuilder.LootEntryBuilder(
                    "simplytea:tea_sapling",
                    5,
                    3,
                    4,
                    "loottable:tea"
            );
            table.addPool(LootObjectsBuilder.LootPoolBuilder(entry, 1, "tea"));
        }
    }
}
