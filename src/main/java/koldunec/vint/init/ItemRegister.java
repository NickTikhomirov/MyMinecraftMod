package koldunec.vint.init;


import koldunec.vint.items.CarminitePick;
import koldunec.vint.items.blockitems.ReedBorerItem;
import koldunec.vint.items.*;
import koldunec.vint.items.baseItems.base_food;
import koldunec.vint.items.baseItems.base_fuel;
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
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegister{
    public static Item CURINGSEEDS = new curingseeds();
    public static Item CURING_GRASS= new curinggrass();
    public static Item SUPERCURING_GRASS= new supercuringgrass();
    public static Item POWDER_REED = new reed_item();
    public static Item BORER_REED = new ReedBorerItem();

    public static Item VANILLA_POWDER = new base_item("vanilla_powder",64);

    public static Item GHAST_SEEDS = new ghast_seeds();
    public static Item SPARKLES = new base_fuel("sparkles",64, (short)100);
    public static Item BITCOIN = new bitcoin();
    public static Item BITCOIN5000 = new bitcoin5000("bitcoin5000",64);

    public static Item ESSENCE_RAINBOW= new base_item("rEssence", 64);
    public static Item FLINTBASE = new flint_base();
    public static Item MAGIC_FLINTS = new flints();
    public static Item ROUND_STONE = new inertstone("round_stone",64);
    public static Item SOUL_CRYSTAL_s = new base_item("soul_crystal",64);
    public static Item WOODEN_RUNE = new base_item("wooden_rune",64);
    public static Item SOUL_FRUIT = new soul_fruit();
    public static Item NETHER_CRYSTAL = new base_item("nether_crystal",64);
    public static Item SOUL = new base_item("small_soul",64);
    public static Item RED_POWDER = new oreProspector();

    public static Item GOLDEN_DIAMOND = new base_item("dg0",64);
    public static Item DIAMOND_GOLD = new base_item("dg1",64);
    public static Item DIAMONDGOLDEN_GOLDEN_DIAMOND = new base_item("dg2",64);

    public static Item GOLDEN_POTATO = new potatogp();
    public static Item FLESH = new flesh();
    public static Item EYE_C = new base_food("curedeye",64,3,0.8F,false);
    public static Item FISH_C = new base_food("curedfish",64,3, 0.8F,false);
    public static Item POTATO_C = new base_food("curedpotato",64,3,1.2F,false);
    public static Item CHICKEN_C = new base_food("curedchicken",64,3,1.2F,false);
    public static Item CHORUS_C = new base_food("curedfruit",64,4,2.4F,false);
    public static Item FISHY = new base_food("fishy",64,1,1,false);
    public static Item GOLDENDIAMOND_APPLE = new gd_apple();
    public static Item DIAMONDGOLDEN_APPLE = new dg_apple();
    public static Item EFFECTSTORAGE = new effectFreezer();
    public static Item ANOTHER_DYE = new another_dye_please_dont_blame_me();
    public static Item SHELL = new shell();
    public static Item HALFDUST = new base_item("halfdust",64);

    public static Item RELIQUARISTS_SWORD = new reliquarist_sword(MaterialRegister.thaumicMix,"ambersword");
    public static Item DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD = new reliquarist_sword(MaterialRegister.diamondgolden_golden_diamond, "dggd_sword");
    public static Item BROOM = new Broom("broom");
    public static Item MAGICBALL = new cursedRock("magic_ball",24);
    public static Item LASERCORE = new LaserCore();
    public static Item STEALER = new Stealer();
    public static Item xyAMULET = new xyAmulet();
    public static Item SAVIOUR = new saviour();
    public static Item SHOVEL_AMULET = new magic_shovel();
    public static Item MAGIC_PROTECTOR = new base_item("amulet0",16);
    public static Item PAINT_TRANSMUTATOR = new paint_transmutator();
    public static Item SOUL_SHEARS = new soul_shears();
    public static Item SPONGE_OF_CONCEPTUALIZATION = new cleaner();
    public static Item CHISEl_OF_CONCEPTUALIZATION = new cheasel();
    public static Item SCROLL = new scroll();
    public static Item SCROLL_ISLE = new scroll2();

    public static Item NETHER_DRINK = new nether_drink();
    public static Item TRANSFORMATION_DUST = new base_item("dusttrans", 64);
    public static Item CARMINITE_AXE = new CarminiteAxe();
    public static Item CARMINITE_PICKAXE = new CarminitePick();

    public static Item ALUMINUM = new base_item("aluminum", 64);

    public static void register() {
        setRegister(VANILLA_POWDER);
        setRegister(CURINGSEEDS);
        setRegister(CURING_GRASS);
        setRegister(SUPERCURING_GRASS);
        setRegister(BITCOIN);
        setRegister(SPARKLES);
        setRegister(ESSENCE_RAINBOW);
        setRegister(FLINTBASE);
        setRegister(MAGIC_FLINTS);
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
        setRegister(CHICKEN_C);
        setRegister(POTATO_C);
        setRegister(FISH_C);
        setRegister(ROUND_STONE);
        setRegister(BITCOIN5000);
        setRegister(SOUL_CRYSTAL_s);
        setRegister(MAGIC_PROTECTOR);
        setRegister(WOODEN_RUNE);
        setRegister(GHAST_SEEDS);
        setRegister(PAINT_TRANSMUTATOR);
        setRegister(CHORUS_C);
        setRegister(SOUL_FRUIT);
        setRegister(SOUL_SHEARS);
        setRegister(NETHER_CRYSTAL);
        setRegister(SOUL);
        setRegister(RED_POWDER);
        setRegister(DIAMOND_GOLD);
        setRegister(GOLDEN_DIAMOND);
        setRegister(DIAMONDGOLDEN_GOLDEN_DIAMOND);
        setRegister(SPONGE_OF_CONCEPTUALIZATION);
        setRegister(GOLDENDIAMOND_APPLE);
        setRegister(DIAMONDGOLDEN_APPLE);
        setRegister(SCROLL);
        setRegister(CHISEl_OF_CONCEPTUALIZATION);
        setRegister(POWDER_REED);
        setRegister(RELIQUARISTS_SWORD);
        setRegister(SCROLL_ISLE);
        setRegister(SHOVEL_AMULET);
        setRegister(DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD);
        setRegister(HALFDUST);

        if(IntegrationHelper.isLoadedTwilight){
            setRegister(TRANSFORMATION_DUST);
            setRegister(CARMINITE_AXE);
            setRegister(CARMINITE_PICKAXE);
            setRegister(SHELL);
            setRegister(BORER_REED);
        }

        MinecraftForge.addGrassSeed(new ItemStack(CURING_GRASS),10);
        MinecraftForge.addGrassSeed(new ItemStack(ANOTHER_DYE,1,1),3);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        setRender(VANILLA_POWDER);
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
        setRender(EYE_C);
        setRender(NETHER_DRINK);
        setRender(POTATO_C);
        setRender(CHICKEN_C);
        setRender(FISH_C);
        setRender(ROUND_STONE);
        setRender(BITCOIN5000);
        setRender(SOUL_CRYSTAL_s);
        setRender(MAGIC_PROTECTOR);
        setRender(WOODEN_RUNE);
        setRender(GHAST_SEEDS);
        setRender(PAINT_TRANSMUTATOR);
        setRender(CHORUS_C);
        setRender(SOUL_FRUIT);
        setRender(SOUL_SHEARS);
        setRender(NETHER_CRYSTAL);
        setRender(SOUL);
        setRender(RED_POWDER);
        setRender(DIAMOND_GOLD);
        setRender(GOLDEN_DIAMOND);
        setRender(DIAMONDGOLDEN_GOLDEN_DIAMOND);
        setRender(SPONGE_OF_CONCEPTUALIZATION);
        setRender(GOLDENDIAMOND_APPLE);
        setRender(DIAMONDGOLDEN_APPLE);
        setRender(CHISEl_OF_CONCEPTUALIZATION);
        setRender(RELIQUARISTS_SWORD);
        setRender(SHOVEL_AMULET);
        setRender(DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD);
        setRender(HALFDUST);

        if(IntegrationHelper.isLoadedTwilight) {
            setRender(TRANSFORMATION_DUST);
            setRender(CARMINITE_AXE);
            setRender(CARMINITE_PICKAXE);
            setRender(SHELL);
            setRender(BORER_REED);
        }

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