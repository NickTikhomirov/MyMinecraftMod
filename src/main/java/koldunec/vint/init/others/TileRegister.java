package koldunec.vint.init.others;

import koldunec.vint.tileentities.*;
import koldunec.vint.vint;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {
    public static void register(){
        GameRegistry.registerTileEntity(EntityStore.class, new ResourceLocation(vint.MODID,"st_ore"));
        GameRegistry.registerTileEntity(TileLlama.class,new ResourceLocation(vint.MODID,"tilellama"));
        GameRegistry.registerTileEntity(TileTower.class,new ResourceLocation(vint.MODID,"twilight_reactor"));
        GameRegistry.registerTileEntity(TileEntityDryer.class, new ResourceLocation(vint.MODID, "tinker_dryer"));
        GameRegistry.registerTileEntity(TileEntityFlopper.class, new ResourceLocation(vint.MODID, "flopper"));
    }
}
