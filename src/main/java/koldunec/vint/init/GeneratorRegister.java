package koldunec.vint.init;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.natura.NaturaShroomBuilder;
import koldunec.vint.world.generate.*;
import koldunec.vint.world.nether.*;
import koldunec.vint.world.structure_builders.TowerDecorators.MiddleDecorator;
import koldunec.vint.world.structure_builders.TowerDecorators.SideDecorator;
import koldunec.vint.world.structure_builders.TowerDecorators.TopDecorator;
import koldunec.vint.world.structure_builders.TreeRouter;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GeneratorRegister {

    public static void register(){
        GameRegistry.registerWorldGenerator(new GenerateOre(), 0);
        GameRegistry.registerWorldGenerator(new SkyIsleGenerator(),10);

        if(IntegrationHelper.isLoadedNatura){
            GameRegistry.registerWorldGenerator(new NetherGenerator(), 1);
            GameRegistry.registerWorldGenerator(new OasisGenerator(),2);
            GameRegistry.registerWorldGenerator(new NetherTreeGenerator(),3);
            GameRegistry.registerWorldGenerator(new NetherLightGenerator(), 4);
            GameRegistry.registerWorldGenerator(new Mushroomer(), 4);
            GameRegistry.registerWorldGenerator(new NaturaDecoratorBerries(),9);

            TreeRouter.init();
            NaturaShroomBuilder.init();
        }

        // nether towers
        SideDecorator.init();
        MiddleDecorator.init();
        TopDecorator.init();
    }

    public static void postInit(){
        if(IntegrationHelper.isLoadedNatura)
            TreeRouter.postInit();
    }
}
