package koldunec.ammpdbm_mod.events;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.init.PotionRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LootTable_Event {
    @SubscribeEvent
    public void onLootTablesLoaded(LootTableLoadEvent e) {
        if(ammpdbm_mod.isLoadedTwilight) {
            ResourceLocation r = new ResourceLocation("twilightforest","structures/hill_1/common");
            if (e.getName().equals(r)) {
                final LootPool pool2 = e.getTable().getPool("main");
                pool2.addEntry(new LootEntryItem(ItemRegister.TRANSFORMATION_DUST, 1, 0, new LootFunction[] {new SetCount(new LootCondition[0], new RandomValueRange(1, 5))}, new LootCondition[0], "loottable:dusttras"));
            }
        }

        if(e.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)) {
            ItemStack a  = new ItemStack(Items.SPAWN_EGG);
            ItemMonsterPlacer.applyEntityIdToItemStack(a,new ResourceLocation("minecraft","cave_spider"));
            //e.getTable().getPool("main").removeEntry("minecraft:arrow");
            e.getTable().getPool("main").setRolls(new RandomValueRange(2,2));
            e.getTable().getPool("main").addEntry(new LootEntryItem(Items.TIPPED_ARROW, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(5, 10)), new SetNBT(new LootCondition[0], PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionRegister.MINDDEVOUR_TYPE_STANDARD).getTagCompound())}, new LootCondition[0], "loottable:tipped_surprise"));
            e.getTable().getPool("main").addEntry(new LootEntryItem(Items.SPAWN_EGG, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3)), new SetNBT(new LootCondition[0],  a.getTagCompound())}, new LootCondition[0], "loottable:spider_surprise"));
        }
        if(LootTableList.CHESTS_IGLOO_CHEST.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,5,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(3,7))}, new LootCondition[0],"loottable:supergrass"));
        }
        if(LootTableList.CHESTS_VILLAGE_BLACKSMITH.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(
                    ItemRegister.SCROLL,
                    10,
                    1,
                    new LootFunction[]{
                            new SetCount(new LootCondition[0], new RandomValueRange(1,1)),
                            new SetMetadata(new LootCondition[0],new RandomValueRange(1,1))
                    },
                    new LootCondition[0],"loottable:scroll"));
        }
        if(LootTableList.CHESTS_DESERT_PYRAMID.equals(e.getName())){
            e.getTable().getPool("pool1").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,10,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1,3))}, new LootCondition[0],"loottable:supergrass"));
        }
        if(LootTableList.GAMEPLAY_FISHING_JUNK.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,5,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1,3))}, new LootCondition[0],"loottable:supergrass"));
        }
        if(LootTableList.GAMEPLAY_FISHING_TREASURE.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(
                    ItemRegister.SCROLL,
                    10,
                    1,
                    new LootFunction[]{
                            new SetCount(new LootCondition[0], new RandomValueRange(1,1)),
                            new SetMetadata(new LootCondition[0],new RandomValueRange(3,3))
                    },
                    new LootCondition[0],"loottable:scroll"));
        }
    }
}
