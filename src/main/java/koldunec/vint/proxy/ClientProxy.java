package koldunec.vint.proxy;


import koldunec.vint.compatibility.TinkerBook.MaterialDocumenter;
import koldunec.vint.compatibility.TinkerBook.ModifierAppender;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.entities.entityBitcoin;
import koldunec.vint.entities.entityMagicBall;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.client.SnowballRenderFactory;
import koldunec.vint.entities.entityStone;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import slimeknights.tconstruct.library.book.TinkerBook;


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
        ItemRegister.registerRender();
        BlockRegister.registerRender();
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        if(IntegrationHelper.isLoadedTinkers) {
            TinkerBook.INSTANCE.addTransformer(new ModifierAppender());
            TinkerBook.INSTANCE.addTransformer(new MaterialDocumenter());
        }
    }
}
