package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.world.generate.GenerateOre;
import koldunec.ammpdbm_mod.world.generate.llama_island;
import koldunec.ammpdbm_mod.world.generate.nether_island;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GeneratorRegister {
    public static void register(){
        GameRegistry.registerWorldGenerator(new GenerateOre(), 0);
        if(ammpdbm_mod.isLoadedProjectX && ammpdbm_mod.isLoadedSulfurTorches)
            GameRegistry.registerWorldGenerator(new nether_island(), 10);
        GameRegistry.registerWorldGenerator(new llama_island(),10);
    }
}
