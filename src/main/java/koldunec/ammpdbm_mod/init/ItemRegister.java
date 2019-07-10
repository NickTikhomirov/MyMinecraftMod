package koldunec.ammpdbm_mod.init;


import koldunec.ammpdbm_mod.broomitems.*;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_fuel;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import koldunec.ammpdbm_mod.broomitems.curinggrass.curinggrass;
import koldunec.ammpdbm_mod.broomitems.curinggrass.curingseeds;
import koldunec.ammpdbm_mod.broomitems.curinggrass.supercuringgrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRegister
{
    public static Item CURINGSEEDS = new curingseeds();
    public static Item CURING_GRASS= new curinggrass();
    public static Item SUPERCURING_GRASS= new supercuringgrass();

    public static Item SPARKLES = new base_fuel("sparkles",64, (short)100);
    public static Item BITCOIN = new bitcoin();

    public static Item ESSENCE_RAINBOW= new base_item("rEssence", 64);
    public static Item FLINTBASE = new flint_base();
    public static Item MAGIC_FLINTS = new flints();
    public static Item RUNIC_STICK = new base_item("runic_stick",64);

    public static Item GOLDEN_POTATO= new potatogp();
    public static Item FLESH = new flesh();
    public static Item EYE_C = new base_food("curedeye",64,3,0.8F,false);
    public static Item FISHY = new base_food("fishy",64,1,1,false);
    public static Item EFFECTSTORAGE = new effectFreezer();
    public static Item ANOTHER_DYE = new another_dye_please_dont_blame_me();

    public static Item BROOM = new broom("broom");
    public static Item MAGICBALL = new cursedRock("magic_ball",24);
    public static Item LASERCORE = new LaserCore();
    public static Item STEALER = new Stealer();

    public static Item NETHER_DRINK = new nether_drink();
    public static Item TRANSFORMATION_DUST = new base_item("dusttrans", 64);

    public static void register()
    {
        setRegister(CURINGSEEDS);
        setRegister(CURING_GRASS);
        setRegister(SUPERCURING_GRASS);
        setRegister(BITCOIN);
        setRegister(SPARKLES);
        setRegister(ESSENCE_RAINBOW);
        setRegister(FLINTBASE);
        setRegister(MAGIC_FLINTS);
        setRegister(RUNIC_STICK);
        setRegister(GOLDEN_POTATO);
        setRegister(FLESH);
        setRegister(FISHY);
        setRegister(ANOTHER_DYE);
        setRegister(NETHER_DRINK);
        setRegister(EFFECTSTORAGE);
        setRegister(BROOM);
        setRegister(MAGICBALL);
        setRegister(LASERCORE);
        setRegister(STEALER);
        setRegister(EYE_C);

        MinecraftForge.addGrassSeed(new ItemStack(CURING_GRASS),10);

        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest")){
            setRegister(TRANSFORMATION_DUST);
        }

        OreDictionary.registerOre("bone",new ItemStack(Item.getByNameOrId("tconstruct:necrotic_bone"),1,0));
        OreDictionary.registerOre("dyeBlack",new ItemStack(ANOTHER_DYE,1,0));
        OreDictionary.registerOre("dyeGreen",new ItemStack(ANOTHER_DYE,1,1));
        OreDictionary.registerOre("dyeBrown",new ItemStack(ANOTHER_DYE,1,2));
        OreDictionary.registerOre("dyeBlue",new ItemStack(ANOTHER_DYE,1,3));
        OreDictionary.registerOre("dyeWhite",new ItemStack(ANOTHER_DYE,1,4));

        OreDictionary.registerOre("magicFlintWeak", new ItemStack(MAGIC_FLINTS, 1,0));
        OreDictionary.registerOre("magicFlintWeak", new ItemStack(MAGIC_FLINTS, 1,1));

    }

    @SideOnly(Side.CLIENT)
    public static void registerRender()
    {
        setRender(BROOM);
        setRender(BITCOIN);
        setRender(CURING_GRASS);
        setRender(ESSENCE_RAINBOW);
        setRender(SUPERCURING_GRASS);
        setRender(GOLDEN_POTATO);
        setRender(SPARKLES);
        setRender(MAGICBALL);
        setRender(EFFECTSTORAGE);
        setRender(FLESH);
        setRender(LASERCORE);
        setRender(CURINGSEEDS);
        setRender(STEALER);
        setRender(FISHY);
        setRender(FLINTBASE);
        setRender(RUNIC_STICK);
        setRender(EYE_C);

        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest"))
            setRender(TRANSFORMATION_DUST);
        setRender(NETHER_DRINK);
    }

    private static void setRegister(Item item)
    {
        ForgeRegistries.ITEMS.register(item);
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}