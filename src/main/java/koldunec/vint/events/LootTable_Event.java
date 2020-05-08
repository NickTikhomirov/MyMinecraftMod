package koldunec.vint.events;

import koldunec.vint.helpers.VanillaHelper;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.init.LootRegister;
import koldunec.vint.init.PotionRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
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
        ResourceLocation name = e.getName();

        if(name.equals(LootRegister.LLAMA_ISLAND)){
            LootInjections.llamaIsleIntegrations(e.getTable());
            return;
        }

        if(IntegrationHelper.isLoadedTinkers && IntegrationHelper.isLoadedPrimitive){
            if(name.equals(new ResourceLocation("primitivemobs","entities/flame_spewer")))
                LootInjections.flameSpewerInjection(e.getTable());
            return;
        }

        if(IntegrationHelper.isLoadedTwilight) {
            ResourceLocation r = new ResourceLocation("twilightforest","structures/hill_1/common");
            if (name.equals(r)) {
                final LootPool pool2 = e.getTable().getPool("main");
                pool2.addEntry(new LootEntryItem(ItemRegister.TRANSFORMATION_DUST, 1, 0, new LootFunction[] {new SetCount(new LootCondition[0], new RandomValueRange(1, 5))}, new LootCondition[0], "loottable:dusttras"));
            }
            ResourceLocation naga = new ResourceLocation("twilightforest", "entities/naga");
            if(e.getName().equals(naga)){
                e.getTable().addPool(
                        new LootPool(
                                new LootEntry[]{new LootEntryItem(Item.getByNameOrId("twilightforest:steeleaf_ingot"),1,1, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(4,6))}, new LootCondition[0], "leaves")},
                                new LootCondition[0],
                                new RandomValueRange(1,1),
                                new RandomValueRange(0,0),
                                "leaves"
                        )
                );
            }

            return;
        }

        if(name.equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)) {
            ItemStack egg = VanillaHelper.getEggFor(new ResourceLocation("minecraft", "cave_spider"));
            LootPool pool = e.getTable().getPool("main");
            //e.getTable().getPool("main").removeEntry("minecraft:arrow");
            pool.setRolls(new RandomValueRange(2,2));
            pool.addEntry(new LootEntryItem(Items.TIPPED_ARROW, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(5, 10)), new SetNBT(new LootCondition[0], PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionRegister.MINDDEVOUR_TYPE_STANDARD).getTagCompound())}, new LootCondition[0], "loottable:tipped_surprise"));
            pool.addEntry(new LootEntryItem(Items.SPAWN_EGG, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3)), new SetNBT(new LootCondition[0],  egg.getTagCompound())}, new LootCondition[0], "loottable:spider_surprise"));
            return;
        }
        if(LootTableList.CHESTS_IGLOO_CHEST.equals(e.getName())){
            LootInjections.addDefault(
                    e.getTable().getPool("main"),
                    ItemRegister.SUPERCURING_GRASS,
                    5,
                    new RandomValueRange(3,7),
                    "loottable:supergrass");
            return;
        }
        if(LootTableList.CHESTS_VILLAGE_BLACKSMITH.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(
                    ItemRegister.SCROLL,
                    10,
                    1,
                    new LootFunction[]{
                            LootInjections.setCount(1,1),
                            LootInjections.setMeta(1)
                    },
                    new LootCondition[0],"loottable:scroll"));
            return;
        }
        if(LootTableList.CHESTS_DESERT_PYRAMID.equals(e.getName())){
            e.getTable().getPool("pool1").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,10,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1,3))}, new LootCondition[0],"loottable:supergrass"));
            return;
        }
        if(LootTableList.GAMEPLAY_FISHING_JUNK.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,5,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1,3))}, new LootCondition[0],"loottable:supergrass"));
            return;
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
            return;
        }
    }
}
