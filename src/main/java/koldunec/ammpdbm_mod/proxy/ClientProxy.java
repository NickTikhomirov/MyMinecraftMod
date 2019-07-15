package koldunec.ammpdbm_mod.proxy;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.entities.entityBitcoin;
import koldunec.ammpdbm_mod.entities.entityMagicBall;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.utils.SnowballRenderFactory;
import koldunec.ammpdbm_mod.entities.entityStone;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;


public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityMagicBall.class, new SnowballRenderFactory(ItemRegister.MAGICBALL));
        RenderingRegistry.registerEntityRenderingHandler(entityStone.class, new SnowballRenderFactory(ItemRegister.ROUND_STONE));
        RenderingRegistry.registerEntityRenderingHandler(entityBitcoin.class, new SnowballRenderFactory(ItemRegister.BITCOIN5000));

        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        //NetworkRegistry.INSTANCE.registerGuiHandler(ammpdbm_mod.instance,new GuiHandler());
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
