package koldunec.vint.init;

import koldunec.vint.vint;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootRegister {
    public static final ResourceLocation LLAMA_ISLAND = new ResourceLocation(vint.MODID,"llama_island");
    public static final ResourceLocation TWILIGHT_CHEST = new ResourceLocation(vint.MODID, "twi_chest");
    public static final ResourceLocation TWILIGHT_LOOT = new ResourceLocation(vint.MODID, "twi_loot");


    public static void register(){
        LootTableList.register(LLAMA_ISLAND);
        LootTableList.register(TWILIGHT_CHEST);
        LootTableList.register(TWILIGHT_LOOT);
    }
}
