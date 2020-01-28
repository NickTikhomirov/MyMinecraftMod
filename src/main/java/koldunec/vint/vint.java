package koldunec.vint;

import koldunec.vint.proxy.CommonProxy;
import koldunec.vint.utils.magicTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
                "after:retroexchange;" +
                "after:baubles;" +
                "after:primitivemobs;" +
                "after:thaumcraft;" +
                "after:natura;" +
                "after:hypewear;" +
                "after:scalinghealth"
)
public class vint
{
    @Mod.Instance("vint")
    public static vint instance;
    public static final String MODID = "vint";
    public static final String NAME = "Various Interactions";
    public static final String VERSION = "0.1.0";
    @SidedProxy(clientSide = "koldunec.vint.proxy.ClientProxy", serverSide = "koldunec.vint.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabs magicTab = new magicTab("magicTab");
    public static Random random = new Random();
    public static boolean isLoadedTwilight = false;
    public static boolean isLoadedTinkers = false;
    public static boolean isLoadedProjectX = false;
    public static boolean isLoadedProjectRed_exploration = false;
    public static boolean isLoadedSulfurTorches = false;
    public static boolean isLoadedBaubles = false;
    public static boolean isLoadedPrimitive = false;
    public static boolean isLoadedThaumcraft = false;
    public static boolean isLoadedNatura = false;
    public static boolean isLoadedHype = false;
    public static boolean isLoadedScalingH = false;



    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest"))
            isLoadedTwilight = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("tconstruct"))
            isLoadedTinkers = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("projectx"))
            isLoadedProjectX = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("sulfurtorches"))
            isLoadedSulfurTorches = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("projectred-exploration"))
            isLoadedProjectRed_exploration = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("baubles"))
            isLoadedBaubles = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("primitivemobs"))
            isLoadedPrimitive = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("thaumcraft"))
            isLoadedThaumcraft = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("natura"))
            isLoadedNatura = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("hypewear"))
            isLoadedHype = true;
        if(net.minecraftforge.fml.common.Loader.isModLoaded("scalinghealth"))
            isLoadedScalingH = true;

        proxy.preInit(event);


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}
