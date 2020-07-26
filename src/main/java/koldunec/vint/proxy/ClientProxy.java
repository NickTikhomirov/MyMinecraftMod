package koldunec.vint.proxy;


import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.TinkerBook.MaterialDocumenter;
import koldunec.vint.compatibility.Tinker.TinkerBook.ModifierAppender;
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
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        RenderingRegistry.registerEntityRenderingHandler(entityMagicBall.class, new SnowballRenderFactory(ItemRegister.MAGICBALL));
        RenderingRegistry.registerEntityRenderingHandler(entityStone.class, new SnowballRenderFactory(ItemRegister.ROUND_STONE));
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
        ItemRegister.registerRender();
        BlockRegister.registerRender();
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
