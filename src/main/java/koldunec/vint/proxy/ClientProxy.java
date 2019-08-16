package koldunec.vint.proxy;


import koldunec.vint.vint;
import koldunec.vint.entities.entityBitcoin;
import koldunec.vint.entities.entityMagicBall;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.GuiHandler;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.utils.SnowballRenderFactory;
import koldunec.vint.entities.entityStone;
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
        NetworkRegistry.INSTANCE.registerGuiHandler(vint.instance,new GuiHandler());
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
