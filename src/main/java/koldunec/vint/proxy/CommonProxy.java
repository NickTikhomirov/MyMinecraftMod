package koldunec.vint.proxy;


import koldunec.vint.compatibility.OtherTweaker;
import koldunec.vint.containers.ContainerChlesis;
import koldunec.vint.init.*;
import koldunec.vint.recipes.AlternativeCarving;
import koldunec.vint.vint;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

        public void preInit(FMLPreInitializationEvent event)
        {
                EntityRegister.register();
                ItemRegister.register();
                BlockRegister.register();
                OredictRegister.oreRegisterItems();
                OredictRegister.oreRegisterBlocks();
                EventRegister.register();
        }

        public void init(FMLInitializationEvent event)
        {
                NetworkRegistry.INSTANCE.registerGuiHandler(vint.instance,new GuiHandler());
                OredictRegister.oreRegisterSideItems();
                LootRegister.register();
                GeneratorRegister.register();
                CraftingRegister.register();
                OtherTweaker.composterInit();
                ContainerChlesis.initMyCarving();
        }

        public void postInit(FMLPostInitializationEvent event) {
        }
}
