package koldunec.ammpdbm_mod.proxy;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.entities.entityBitcoin;
import koldunec.ammpdbm_mod.entities.entityMagicBall;
import koldunec.ammpdbm_mod.events.LootTable_Event;
import koldunec.ammpdbm_mod.events.TechEvents;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.CraftingRegister;
import koldunec.ammpdbm_mod.events.EventHandler;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.entities.entityStone;
import koldunec.ammpdbm_mod.world.generate.GenerateOre;
import koldunec.ammpdbm_mod.world.generate.llama_island;
import koldunec.ammpdbm_mod.world.generate.nether_island;
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
                MinecraftForge.EVENT_BUS.register(new TechEvents());
                MinecraftForge.EVENT_BUS.register(new LootTable_Event());

        }

        public void init(FMLInitializationEvent event)
        {
                GameRegistry.registerWorldGenerator(new GenerateOre(), 0);
                if(ammpdbm_mod.isLoadedProjectX && ammpdbm_mod.isLoadedSulfurTorches)
                        GameRegistry.registerWorldGenerator(new nether_island(), 10);
                GameRegistry.registerWorldGenerator(new llama_island(),10);
                CraftingRegister.register();

        }

        public void postInit(FMLPostInitializationEvent event) {

        }


}
