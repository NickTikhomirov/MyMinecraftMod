package koldunec.vint.proxy;



import koldunec.vint.init.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

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
                OredictRegister.oreRegisterSideItems();
                LootRegister.register();
                GeneratorRegister.register();
                CraftingRegister.register();
        }

        public void postInit(FMLPostInitializationEvent event) {

        }
}
