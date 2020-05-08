package koldunec.vint.init;

import koldunec.vint.tileentities.EntityStore;
import koldunec.vint.tileentities.TileLlama;
import koldunec.vint.vint;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {
    public static void register(){
        GameRegistry.registerTileEntity(EntityStore.class, new ResourceLocation(vint.MODID,"st_ore"));
        GameRegistry.registerTileEntity(TileLlama.class,new ResourceLocation(vint.MODID,"tilellama"));
    }
}