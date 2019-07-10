package koldunec.ammpdbm_mod.proxy;


import koldunec.ammpdbm_mod.utils.entityMagicBall;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.utils.SnowballRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;


public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityMagicBall.class, new SnowballRenderFactory(ItemRegister.MAGICBALL));
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        ItemRegister.registerRender();
        BlockRegister.registerRender();
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}
