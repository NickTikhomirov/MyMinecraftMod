package koldunec.vint.init.others;

import koldunec.vint.vint;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootRegister {
    public static final ResourceLocation LLAMA_ISLAND = get("llama_island");
    public static final ResourceLocation TWILIGHT_CHEST = get("twi_chest");
    public static final ResourceLocation TWILIGHT_LOOT = get("twi_loot");
    public static final ResourceLocation GRAVEYARD_POTIONS = get("grave_potions");

    public static final ResourceLocation TWILIGHT_HILL_SCROLL = get("hill_scrolled");
    public static final ResourceLocation TWILIGHT_ISLE_SCROLL = get("isle_scrolled");
    public static final ResourceLocation TWILIGHT_GRAVE_RARE_FIXED = get("grave_rare_fixed");


    public static void register(){
        LootTableList.register(LLAMA_ISLAND);
        LootTableList.register(TWILIGHT_CHEST);
        LootTableList.register(TWILIGHT_LOOT);
        LootTableList.register(TWILIGHT_HILL_SCROLL);
        LootTableList.register(TWILIGHT_ISLE_SCROLL);
        LootTableList.register(GRAVEYARD_POTIONS);
    }

    private static ResourceLocation get(String name){
        return new ResourceLocation(vint.MODID, name);
    }
}
