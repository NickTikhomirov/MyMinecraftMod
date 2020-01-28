package koldunec.vint.init;

import koldunec.vint.vint;
import koldunec.vint.world.generate.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GeneratorRegister {
    public static void register(){
        GameRegistry.registerWorldGenerator(new GenerateOre(), 0);
        if(vint.isLoadedProjectX && vint.isLoadedSulfurTorches)
            GameRegistry.registerWorldGenerator(new nether_island(), 10);
        GameRegistry.registerWorldGenerator(new llama_island(),10);
        GameRegistry.registerWorldGenerator(new cacti(),5);
        if(vint.isLoadedTwilight){
            GameRegistry.registerWorldGenerator(new twili_island(),10);
        }
    }
}
