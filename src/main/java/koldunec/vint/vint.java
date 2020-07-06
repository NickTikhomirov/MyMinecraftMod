package koldunec.vint;

import koldunec.vint.proxy.CommonProxy;
import koldunec.vint.utils.magicTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@Mod(
        modid = vint.MODID,
        name = vint.NAME,
        version = vint.VERSION,

        dependencies =
                "after:twilightforest; " +
                "after:tconstruct;" +
                "after:projectx;" +
                "after:projectred-exploration;" +
                "after:sulfurtorches;" +
                "after:baubles;" +
                "after:primitivemobs;" +
                "after:thaumcraft;" +
                "after:natura;" +
                "after:hypewear;" +
                "after:scalinghealth;"+
                "after:chisel"
)
public class vint{
    @Mod.Instance("vint")
    public static vint instance;
    public static final String MODID = "vint";
    public static final String NAME = "Various Interactions";
    public static final String VERSION = "0.4";
    @SidedProxy(clientSide = "koldunec.vint.proxy.ClientProxy", serverSide = "koldunec.vint.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabs magicTab = new magicTab("magicTab");
    public static Random random = new Random();

    public static final Logger LOGGER = LogManager.getLogger(vint.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){proxy.postInit(event);}
}
