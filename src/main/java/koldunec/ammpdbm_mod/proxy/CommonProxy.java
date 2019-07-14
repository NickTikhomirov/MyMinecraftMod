package koldunec.ammpdbm_mod.proxy;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.entities.entityBitcoin;
import koldunec.ammpdbm_mod.entities.entityMagicBall;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.CraftingRegister;
import koldunec.ammpdbm_mod.init.EventHandler;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.entities.entityStone;
import koldunec.ammpdbm_mod.world.generate.GenerateOre;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

        public void preInit(FMLPreInitializationEvent event)
        {
                EntityRegistry.registerModEntity(new ResourceLocation("ammpdbm_mod", "magic_ball"), entityMagicBall.class, "ammpdbm:magic_ball", 0, ammpdbm_mod.instance, 64, 20, true);
                EntityRegistry.registerModEntity(new ResourceLocation("ammpdbm_mod", "round_stone"), entityStone.class, "ammpdbm:round_stone", 1, ammpdbm_mod.instance, 64, 20, true);
                EntityRegistry.registerModEntity(new ResourceLocation("ammpdbm_mod", "bitcoin5000"), entityBitcoin.class, "ammpdbm:bitcoin5000", 2, ammpdbm_mod.instance, 64, 20, true);

                ItemRegister.register();
                BlockRegister.register();
                MinecraftForge.EVENT_BUS.register(new EventHandler());
        }

        public void init(FMLInitializationEvent event)
        {
                GameRegistry.registerWorldGenerator(new GenerateOre(), 0); //Класс генератора и его ID (для каждого генератора нужен уникальный ID)
                CraftingRegister.register();

        }

        public void postInit(FMLPostInitializationEvent event) {

        }


}
