package koldunec.ammpdbm_mod;

import koldunec.ammpdbm_mod.proxy.CommonProxy;
import koldunec.ammpdbm_mod.utils.magicTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.util.Random;


@Mod(
        modid = ammpdbm_mod.MODID,
        name = ammpdbm_mod.NAME,
        version = ammpdbm_mod.VERSION,

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
                "after:natura"
)
public class ammpdbm_mod
{
    @Mod.Instance("ammpdbm_mod")
    public static ammpdbm_mod instance;
    public static final String MODID = "ammpdbm_mod";
    public static final String NAME = "Another Magic Mod Please Don't Blame Me";
    public static final String VERSION = "0.1";
    @SidedProxy(clientSide = "koldunec.ammpdbm_mod.proxy.ClientProxy", serverSide = "koldunec.ammpdbm_mod.proxy.CommonProxy")
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

        proxy.preInit(event);


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}
