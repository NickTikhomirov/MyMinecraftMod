package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.events.EventHandler;
import koldunec.ammpdbm_mod.events.LootTable_Event;
import koldunec.ammpdbm_mod.events.TechEvents;
import koldunec.ammpdbm_mod.events.ThosePitifulDeaths;
import net.minecraftforge.common.MinecraftForge;

public class EventRegister {
    public static void register(){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new TechEvents());
        MinecraftForge.EVENT_BUS.register(new LootTable_Event());
        MinecraftForge.EVENT_BUS.register(new ThosePitifulDeaths());
    }
}
