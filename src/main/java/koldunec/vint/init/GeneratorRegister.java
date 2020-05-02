package koldunec.vint.init;

import koldunec.vint.vint;
import koldunec.vint.world.generate.*;
import koldunec.vint.world.nether.NaturaDecoratorBerries;
import koldunec.vint.world.nether.NetherGenerator;
import koldunec.vint.world.nether.NetherTreeGenerator;
import koldunec.vint.world.nether.OasisGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.common.registry.GameRegistry;

//if(vint.isLoadedProjectX && vint.isLoadedSulfurTorches)
//    GameRegistry.registerWorldGenerator(new nether_island(), 10);

public class GeneratorRegister {

    public static void register(){
        GameRegistry.registerWorldGenerator(new GenerateOre(), 0);
        GameRegistry.registerWorldGenerator(new SkyIsleGenerator(),10);
        GameRegistry.registerWorldGenerator(new NetherGenerator(), 1);
        GameRegistry.registerWorldGenerator(new OasisGenerator(),2);
        GameRegistry.registerWorldGenerator(new NetherTreeGenerator(),3);
        GameRegistry.registerWorldGenerator(new NaturaDecoratorBerries(),9);

    }
}
