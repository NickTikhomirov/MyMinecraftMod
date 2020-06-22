package koldunec.vint.proxy;


import koldunec.vint.client.GuiHandler;
import koldunec.vint.helpers.ConfigHelper;
import koldunec.vint.init.*;
import koldunec.vint.init.others.LootRegister;
import koldunec.vint.init.others.TileRegister;
import koldunec.vint.vint;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
        public void preInit(FMLPreInitializationEvent event) {
                ConfigHelper.init();
                IntegrationHelper.init();
                EntityRegister.register();
                TileRegister.register();
                ItemRegister.register();
                BlockRegister.register();
                CompatibilityRegister.preInit();
                EventRegister.register();
        }

        public void init(FMLInitializationEvent event) {
                NetworkRegistry.INSTANCE.registerGuiHandler(vint.instance,new GuiHandler());
                LootRegister.register();
                GeneratorRegister.register();
                CraftingRegister.register();
                CompatibilityRegister.init();
        }

        public void postInit(FMLPostInitializationEvent event) {
                CompatibilityRegister.postInit();
                BlockRegister.DEFAULT_INIT = null;
                ItemRegister.DEFAULT_INIT = null;
        }
}
