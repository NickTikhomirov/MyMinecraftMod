package koldunec.vint.events;

import koldunec.vint.compatibility.TwilightForest.MainTFModule;
import koldunec.vint.utils.VanillaHelper;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.init.others.LootRegister;
import koldunec.vint.loot.SideAppender;
import koldunec.vint.loot.VintAppender;
import koldunec.vint.objectbuilders.LootObjectsBuilder;
import koldunec.vint.potions.PotionRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
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
            VintAppender.AppendLlama(e.getTable());
            return;
        }

        if(IntegrationHelper.isLoadedTinkers && IntegrationHelper.isLoadedPrimitive){
            if(name.equals(new ResourceLocation("primitivemobs","entities/flame_spewer"))) {
                SideAppender.flameSpewerInjection(e.getTable());
                return;
            }
        }

        if(IntegrationHelper.isLoadedTwilight) {
            if(name.getResourceDomain().equals("twilightforest"))
                MainTFModule.processTable(e);
        }
        if(IntegrationHelper.isLoadedTea){
            if(LootTableList.CHESTS_IGLOO_CHEST.equals(e.getName())) {
                e.getTable().addPool(LootObjectsBuilder.LootPoolBuilder(
                        LootObjectsBuilder.LootEntryBuilder("simplytea:tea_sapling", 5, 1, 1, "tea"), 1, "tea"
                ));
                return;
            }
        }

        if(name.equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)) {
            ItemStack egg = VanillaHelper.getEggFor(new ResourceLocation("minecraft", "cave_spider"));
            LootPool pool = e.getTable().getPool("main");
            pool.setRolls(new RandomValueRange(2,2));
            pool.addEntry(new LootEntryItem(Items.TIPPED_ARROW, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(5, 10)), new SetNBT(new LootCondition[0], PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionRegister.MINDDEVOUR_TYPE_STANDARD).getTagCompound())}, new LootCondition[0], "loottable:tipped_surprise"));
            pool.addEntry(new LootEntryItem(Items.SPAWN_EGG, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3)), new SetNBT(new LootCondition[0],  egg.getTagCompound())}, new LootCondition[0], "loottable:spider_surprise"));
            return;
        }
        if(LootTableList.CHESTS_VILLAGE_BLACKSMITH.equals(e.getName())){
            e.getTable().getPool("main").addEntry(LootObjectsBuilder.LootEntryBuilder(
                    ItemRegister.SCROLL,
                    1, 10, 1, 1,
                    "loottable:scroll"
            ));
            return;
        }
        if(LootTableList.GAMEPLAY_FISHING_TREASURE.equals(e.getName())){
            e.getTable().getPool("main").addEntry(
                    LootObjectsBuilder.LootEntryBuilder(
                        ItemRegister.SCROLL,
                        3, 10, 1, 1,
                        "loottable:scroll"
            ));
            return;
        }
    }
}
