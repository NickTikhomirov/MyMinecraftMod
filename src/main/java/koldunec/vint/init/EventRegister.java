package koldunec.vint.init;

import koldunec.vint.events.*;
import koldunec.vint.vint;
import net.minecraftforge.common.MinecraftForge;

public class EventRegister {
    public static void register(){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new TechEvents());
        MinecraftForge.EVENT_BUS.register(new LootTable_Event());
        MinecraftForge.EVENT_BUS.register(new ThosePitifulDeaths());
        if(vint.integrationHelper.isLoadedTinkers && vint.integrationHelper.isLoadedTwilight)
            MinecraftForge.EVENT_BUS.register(new TinkerEventHandler());
    }
}
