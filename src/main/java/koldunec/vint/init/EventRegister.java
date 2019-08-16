package koldunec.vint.init;

import koldunec.vint.events.EventHandler;
import koldunec.vint.events.LootTable_Event;
import koldunec.vint.events.TechEvents;
import koldunec.vint.events.ThosePitifulDeaths;
import net.minecraftforge.common.MinecraftForge;

public class EventRegister {
    public static void register(){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new TechEvents());
        MinecraftForge.EVENT_BUS.register(new LootTable_Event());
        MinecraftForge.EVENT_BUS.register(new ThosePitifulDeaths());
    }
}
