package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootRegister {
    public static final ResourceLocation LLAMA_ISLAND = new ResourceLocation(ammpdbm_mod.MODID,"llama_island");

    public static void register(){
        LootTableList.register(LLAMA_ISLAND);
    }
}
