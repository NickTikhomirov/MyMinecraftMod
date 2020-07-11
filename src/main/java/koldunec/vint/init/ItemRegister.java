package koldunec.vint.init;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.items.agriculture.*;
import koldunec.vint.items.tools.*;
import koldunec.vint.items.baseItems.base_fuel;
import koldunec.vint.items.*;
import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.items.crafting_tools.cheasel;
import koldunec.vint.items.crafting_tools.cleaner;
import koldunec.vint.items.curinggrass.Vitasaria_Item;
import koldunec.vint.items.curinggrass.supercuringgrass;
import koldunec.vint.items.throwables.cursedRock;
import koldunec.vint.items.throwables.inertstone;
import koldunec.vint.items.saviour;
import koldunec.vint.items.gd_apple;
import koldunec.vint.objectbuilders.ObjectBuilder;
import koldunec.vint.utils.routers.Sideclass_Items;
import koldunec.vint.objectbuilders.SimpleItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

public class ItemRegister{
    public static HashSet<Item> DEFAULT_INIT = new HashSet<>();

    public static Item VITASARIA_SEEDS = put(new CuringSeeds());
    public static Item VITASARIA = put(new Vitasaria_Item());
    public static Item SUPERCURING_GRASS= put(new supercuringgrass());
    public static Item POWDER_REED = new ReedPowderItem();

    public static Item BAMBOO_HOE = put(ObjectBuilder.BuildBambooHoe());

    public static Item GHAST_SEEDS = put(new GhastSeeds());

    public static Item ROUND_STONE = put(new inertstone("round_stone",64));
    public static Item WOODEN_RUNE = put(new base_item("wooden_rune",64));
    public static Item SOUL_FRUIT = put(new SoulFruit());
    public static Item NETHER_CRYSTAL = put(new base_item("nether_crystal",64));
    public static Item SOUL = put(new base_item("small_soul",64));
    public static Item RED_POWDER = put(new SimpleItems.SimpleItem("red_powder", 64));
    public static Item MOSS = put(new Moss());
    public static Item DUST = put(new SimpleItems.SimpleItem("dust",64));

    public static Item GOLDEN_DIAMOND = put(new base_item("dg0",64));
    public static Item DIAMOND_GOLD = put(new base_item("dg1",64));
    public static Item DIAMONDGOLDEN_GOLDEN_DIAMOND = put(new base_item("dg2",64));

    public static Item GOLDEN_POTATO = put(new potatogp());
    public static Item FLESH = put(new flesh());
    public static Item EYE_C = put(new SimpleItems.SimpleFood("curedeye",3,0.8F));
    public static Item FISH_C = put(new SimpleItems.SimpleFood("curedfish",3, 0.8F));
    public static Item POTATO_C = put(new SimpleItems.SimpleFood("curedpotato",3,1.2F));
    public static Item CHICKEN_C = put(new SimpleItems.SimpleFood("curedchicken",3,1.2F));
    public static Item CHORUS_C = put(new SimpleItems.SimpleFood("curedfruit",4,2.4F));
    public static Item FISHY = put(new SimpleItems.SimpleFood("fishy",1,1));
    public static Item GOLDENDIAMOND_APPLE = put(new gd_apple());
    public static Item DIAMONDGOLDEN_APPLE = put(new dg_apple());
    public static Item POTION_MIX = put(new effectFreezer());
    public static Item ANOTHER_DYE = new another_dye_please_dont_blame_me();
    public static Item HALFDUST = put(new base_item("halfdust",64));

    public static Item BROOM = put(new Broom());
    public static Item MAGICBALL = put(new cursedRock("magic_ball",24));
    public static Item LASERCORE = put(new LaserCore());
    public static Item CHLESIS = Sideclass_Items.ChlesisLoader();
    public static Item xyAMULET = new xyAmulet();
    public static Item SAVIOUR = new saviour();
    public static Item MAGIC_PROTECTOR = put(new base_item("amulet0",16));
    public static Item PAINT_TRANSMUTATOR = put(new PaintStone());
    public static Item SOUL_SHEARS = put(new soul_shears());
    public static Item SPONGE_OF_CONCEPTUALIZATION = put(new cleaner());

    public static Item SCROLL = new Scroll();
    public static Item SCROLL_SHROOM = new MushroomScroll();
    public static Item SCROLL_REROLL = new RerollScroll();
    public static Item NETHER_DRINK = put(new NetherDrink());

    public static Item HONEY_CRYSTAL = put(new base_item("honey_crystal",64));
    public static Item BAMBOO_BREAD = put(new SimpleItems.SimpleFood("bamboo_bread",1,1.5F));
    public static Item HONEY_BERRY = put(new SimpleItems.SimpleFood("honey_berry", 4,4F));

    //twilight items
    public static Item BORER_REED = new ReedBorerItem();
    public static Item SHELL = new Shell();
    public static Item TRANSFORMATION_DUST = new base_item("dusttrans", 64);
    public static Item CARMINITE_AXE = new CarminiteAxe();
    public static Item CARMINITE_PICKAXE = Sideclass_Items.CarminitePickLoader();
    public static Item FROZEN_CORE = new SimpleItems.SimpleItem("frozen_core", 64);
    public static Item AURORA_CORE = new SimpleItems.SimpleItem("aurora_core", 64);

    public static void register() {
        for(Item i: DEFAULT_INIT)
            setRegister(i);

        setRegister(ANOTHER_DYE);
        setRegister(SCROLL);
        setRegister(POWDER_REED);

        if(IntegrationHelper.isLoadedPrimitive)
            setRegister(SCROLL_SHROOM);

        if(IntegrationHelper.isLoaded("ebwizardry"))
            setRegister(SCROLL_REROLL);

        if(IntegrationHelper.isLoadedChisel)
            setRegister(CHLESIS);

        if(IntegrationHelper.isLoadedTwilight){
            setRegister(TRANSFORMATION_DUST);
            setRegister(CARMINITE_AXE);
            setRegister(CARMINITE_PICKAXE);
            setRegister(SHELL);
            setRegister(BORER_REED);
            setRegister(FROZEN_CORE);
            setRegister(AURORA_CORE);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for(Item i: DEFAULT_INIT)
            setRender(i);

        if(IntegrationHelper.isLoadedPrimitive)
            setRender(SCROLL_SHROOM);

        if(IntegrationHelper.isLoaded("ebwizardry"))
            setRender(SCROLL_REROLL);

        if(IntegrationHelper.isLoadedChisel)
            setRender(CHLESIS);

        if(IntegrationHelper.isLoadedTwilight) {
            setRender(TRANSFORMATION_DUST);
            setRender(CARMINITE_AXE);
            setRender(CARMINITE_PICKAXE);
            setRender(SHELL);
            setRender(BORER_REED);
            setRender(FROZEN_CORE);
            setRender(AURORA_CORE);
        }
    }

    private static Item put(Item i){
        DEFAULT_INIT.add(i);
        return i;
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