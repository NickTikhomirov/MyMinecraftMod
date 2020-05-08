package koldunec.vint.init;

import koldunec.vint.events.*;
import koldunec.vint.vint;
import net.minecraftforge.common.MinecraftForge;

public class EventRegister {
    public static void register(){
        MinecraftForge.EVENT_BUS.register(new VintMainEventHandler());
        MinecraftForge.EVENT_BUS.register(new TechEvents());
        MinecraftForge.EVENT_BUS.register(new LootTable_Event());
        MinecraftForge.EVENT_BUS.register(new ThosePitifulDeaths());
    }
}
