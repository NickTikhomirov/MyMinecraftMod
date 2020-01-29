package koldunec.vint.init;

import koldunec.vint.vint;
import koldunec.vint.world.generate.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

//if(vint.isLoadedProjectX && vint.isLoadedSulfurTorches)
//    GameRegistry.registerWorldGenerator(new nether_island(), 10);

public class GeneratorRegister {
    public static void register(){
        GameRegistry.registerWorldGenerator(new GenerateOre(), 0);
        GameRegistry.registerWorldGenerator(new SkyIsleGenerator(),10);
        GameRegistry.registerWorldGenerator(new cacti(),5);
    }
}
