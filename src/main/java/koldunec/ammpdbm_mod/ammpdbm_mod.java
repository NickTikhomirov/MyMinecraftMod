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


@Mod(modid = ammpdbm_mod.MODID, name = ammpdbm_mod.NAME, version = ammpdbm_mod.VERSION,dependencies = "after:twilightforest; after:tconstruct")
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



    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}
