package koldunec.vint.init;


import koldunec.vint.items.CarminitePick;
import koldunec.vint.items.blockitems.ReedBorerItem;
import koldunec.vint.items.*;
import koldunec.vint.items.baseItems.base_food;
import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.items.crafting_tools.cheasel;
import koldunec.vint.items.crafting_tools.cleaner;
import koldunec.vint.items.curinggrass.curinggrass;
import koldunec.vint.items.curinggrass.curingseeds;
import koldunec.vint.items.curinggrass.supercuringgrass;
import koldunec.vint.items.gunpowder_reed.reed_item;
import koldunec.vint.items.throwables.bitcoin5000;
import koldunec.vint.items.throwables.cursedRock;
import koldunec.vint.items.throwables.inertstone;
import koldunec.vint.items.saviour;
import koldunec.vint.items.gd_apple;
import koldunec.vint.items.tools.reliquarist_sword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

public class ItemRegister{
    public static HashSet<Item> DEFAULT_INIT = new HashSet<>();

    public static Item CURINGSEEDS = put(new curingseeds());
    public static Item CURING_GRASS= put(new curinggrass());
    public static Item SUPERCURING_GRASS= put(new supercuringgrass());
    public static Item POWDER_REED = new reed_item();

    public static Item VANILLA_POWDER = put(new base_item("vanilla_powder",64));

    public static Item GHAST_SEEDS = put(new ghast_seeds());
    public static Item BITCOIN = put(new bitcoin());
    public static Item BITCOIN5000 = put(new bitcoin5000("bitcoin5000",64));

    public static Item ESSENCE_RAINBOW= put(new base_item("rEssence", 64));
    public static Item FLINTBASE = put(new flint_base());
    public static Item MAGIC_FLINTS = new flints();
    public static Item ROUND_STONE = new inertstone("round_stone",64);
    public static Item WOODEN_RUNE = put(new base_item("wooden_rune",64));
    public static Item SOUL_FRUIT = put(new soul_fruit());
    public static Item NETHER_CRYSTAL = put(new base_item("nether_crystal",64));
    public static Item SOUL = put(new base_item("small_soul",64));
    public static Item RED_POWDER = put(new oreProspector());

    public static Item GOLDEN_DIAMOND = put(new base_item("dg0",64));
    public static Item DIAMOND_GOLD = put(new base_item("dg1",64));
    public static Item DIAMONDGOLDEN_GOLDEN_DIAMOND = put(new base_item("dg2",64));

    public static Item GOLDEN_POTATO = put(new potatogp());
    public static Item FLESH = put(new flesh());
    public static Item EYE_C = put(new base_food("curedeye",64,3,0.8F,false));
    public static Item FISH_C = put(new base_food("curedfish",64,3, 0.8F,false));
    public static Item POTATO_C = put(new base_food("curedpotato",64,3,1.2F,false));
    public static Item CHICKEN_C = put(new base_food("curedchicken",64,3,1.2F,false));
    public static Item CHORUS_C = put(new base_food("curedfruit",64,4,2.4F,false));
    public static Item FISHY = put(new base_food("fishy",64,1,1,false));
    public static Item GOLDENDIAMOND_APPLE = put(new gd_apple());
    public static Item DIAMONDGOLDEN_APPLE = put(new dg_apple());
    public static Item POTION_MIX = put(new effectFreezer());
    public static Item ANOTHER_DYE = new another_dye_please_dont_blame_me();
    public static Item HALFDUST = put(new base_item("halfdust",64));

    public static Item RELIQUARISTS_SWORD = new reliquarist_sword(MaterialRegister.thaumicMix,"ambersword");
    public static Item DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD = put(new reliquarist_sword(MaterialRegister.diamondgolden_golden_diamond, "dggd_sword"));
    public static Item BROOM = put(new Broom("broom"));
    public static Item MAGICBALL = put(new cursedRock("magic_ball",24));
    public static Item LASERCORE = new LaserCore();
    public static Item CHLESIS = new Chlesis();
    public static Item xyAMULET = new xyAmulet();
    public static Item SAVIOUR = new saviour();
    public static Item MAGIC_PROTECTOR = put(new base_item("amulet0",16));
    public static Item PAINT_TRANSMUTATOR = put(new paint_transmutator());
    public static Item SOUL_SHEARS = put(new soul_shears());
    public static Item SPONGE_OF_CONCEPTUALIZATION = put(new cleaner());
    public static Item CHISEl_OF_CONCEPTUALIZATION = put(new cheasel());
    public static Item SCROLL = new scroll();
    public static Item NETHER_DRINK = put(new nether_drink());

    public static Item HONEY_CRYSTAL = new base_item("honey_crystal",64);
    public static Item BAMBOO_BREAD = new base_food("bamboo_bread",64,1,1.5F,false);
    public static Item HONEY_BERRY = new base_food("honey_berry",64, 4,4F,false);

    //twilight items
    public static Item BORER_REED = new ReedBorerItem();
    public static Item SCROLL_ISLE = new scroll2();
    public static Item SHELL = new shell();
    public static Item TRANSFORMATION_DUST = new base_item("dusttrans", 64);
    public static Item CARMINITE_AXE = new CarminiteAxe();
    public static Item CARMINITE_PICKAXE = new CarminitePick();

    public static Item ALUMINUM = new base_item("aluminum", 64);

    public static void register() {
        for(Item i: DEFAULT_INIT)
            setRegister(i);

        setRegister(LASERCORE);
        setRegister(CHLESIS);
        setRegister(ROUND_STONE);
        setRegister(RELIQUARISTS_SWORD);
        setRegister(SCROLL_ISLE);

        setRegister(ANOTHER_DYE);
        setRegister(MAGIC_FLINTS);
        setRegister(SCROLL);
        setRegister(POWDER_REED);

        if(IntegrationHelper.isLoadedTwilight){
            setRegister(TRANSFORMATION_DUST);
            setRegister(CARMINITE_AXE);
            setRegister(CARMINITE_PICKAXE);
            setRegister(SHELL);
            setRegister(BORER_REED);
        }
        if(IntegrationHelper.isLoadedFuture){
            setRegister(BAMBOO_BREAD);
            if(IntegrationHelper.isLoadedTinkers)
                setRegister(HONEY_CRYSTAL);
            if(IntegrationHelper.isLoadedNatura)
                setRegister(HONEY_BERRY);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for(Item i: DEFAULT_INIT)
            setRender(i);

        setRender(LASERCORE);
        setRender(CHLESIS);
        setRender(ROUND_STONE);
        setRender(RELIQUARISTS_SWORD);

        if(IntegrationHelper.isLoadedTwilight) {
            setRender(TRANSFORMATION_DUST);
            setRender(CARMINITE_AXE);
            setRender(CARMINITE_PICKAXE);
            setRender(SHELL);
            setRender(BORER_REED);
        }
        if(IntegrationHelper.isLoadedFuture){
            setRender(BAMBOO_BREAD);
            if(IntegrationHelper.isLoadedTinkers)
                setRender(HONEY_CRYSTAL);
            if(IntegrationHelper.isLoadedNatura)
                setRender(HONEY_BERRY);
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