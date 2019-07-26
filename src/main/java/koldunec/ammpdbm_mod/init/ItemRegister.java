package koldunec.ammpdbm_mod.init;


import koldunec.ammpdbm_mod.broomitems.CarminitePick;
import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.*;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_fuel;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import koldunec.ammpdbm_mod.broomitems.curinggrass.curinggrass;
import koldunec.ammpdbm_mod.broomitems.curinggrass.curingseeds;
import koldunec.ammpdbm_mod.broomitems.curinggrass.supercuringgrass;
import koldunec.ammpdbm_mod.broomitems.throwables.bitcoin5000;
import koldunec.ammpdbm_mod.broomitems.throwables.cursedRock;
import koldunec.ammpdbm_mod.broomitems.throwables.inertstone;
import koldunec.ammpdbm_mod.broomitems.saviour;
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

    public static Item GHAST_SEEDS = new ghast_seeds();
    public static Item SPARKLES = new base_fuel("sparkles",64, (short)100);
    public static Item BITCOIN = new bitcoin();
    public static Item BITCOIN5000 = new bitcoin5000("bitcoin5000",64);

    public static Item ESSENCE_RAINBOW= new base_item("rEssence", 64);
    public static Item FLINTBASE = new flint_base();
    public static Item MAGIC_FLINTS = new flints();
    public static Item RUNIC_STICK = new base_item("runic_stick",64);
    public static Item ROUND_STONE = new inertstone("round_stone",64);
    public static Item SOUL_CRYSTAL_s = new base_item("soul_crystal",64);
    public static Item WOODEN_RUNE = new base_item("wooden_rune",64);
    public static Item SOUL_FRUIT = new base_item("soul_fruit",64);
    public static Item NETHER_CRYSTAL = new base_item("nether_crystal",64);
    public static Item SOUL = new base_item("small_soul",64);
    public static Item RED_POWDER = new oreProspector();

    public static Item GOLDEN_DIAMOND = new base_item("dg0",64);
    public static Item DIAMOND_GOLD = new base_item("dg1",64);
    public static Item DIAMONDGOLDEN_GOLDEN_DIAMOND = new base_item("dg2",64);

    public static Item GOLDEN_POTATO= new potatogp();
    public static Item FLESH = new flesh();
    public static Item EYE_C = new base_food("curedeye",64,3,0.8F,false);
    public static Item FISH_C = new base_food("curedfish",64,3, 0.8F,false);
    public static Item POTATO_C = new base_food("curedpotato",64,3,1.2F,false);
    public static Item CHICKEN_C = new base_food("curedchicken",64,3,1.2F,false);
    public static Item CHORUS_C = new base_food("curedfruit",64,4,2.4F,false);
    public static Item FISHY = new base_food("fishy",64,1,1,false);
    public static Item EFFECTSTORAGE = new effectFreezer();
    public static Item ANOTHER_DYE = new another_dye_please_dont_blame_me();

    public static Item BROOM = new broom("broom");
    public static Item MAGNETPICK = new magnetpick();
    public static Item MAGICBALL = new cursedRock("magic_ball",24);
    public static Item LASERCORE = new LaserCore();
    public static Item STEALER = new Stealer();
    public static Item xyAMULET = new xyAmulet();
    public static Item SAVIOUR = new saviour();
    public static Item MAGIC_PROTECTOR = new base_item("amulet0",16);
    public static Item PAINT_TRANSMUTATOR = new paint_transmutator();
    public static Item SOUL_SHEARS = new soul_shears();
    public static Item PAINT_OPERATOR = new paint_operator();

    public static Item NETHER_DRINK = new nether_drink();
    public static Item TRANSFORMATION_DUST = new base_item("dusttrans", 64);
    public static Item CARMINITE_AXE = new CarminiteAxe();
    public static Item CARMINITE_PICKAXE = new CarminitePick();

    public static Item ALUMINUM = new base_item("aluminum", 64);
    public static Item SULFUR = new base_item("sulfur", 64);

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
        setRegister(CHICKEN_C);
        setRegister(POTATO_C);
        setRegister(FISH_C);
        setRegister(ROUND_STONE);
        setRegister(MAGNETPICK);
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
        setRegister(PAINT_OPERATOR);
        setRegister(DIAMOND_GOLD);
        setRegister(GOLDEN_DIAMOND);
        setRegister(DIAMONDGOLDEN_GOLDEN_DIAMOND);

        OreDictionary.registerOre("dyeBlack",new ItemStack(ANOTHER_DYE,1,0));
        OreDictionary.registerOre("dyeGreen",new ItemStack(ANOTHER_DYE,1,1));
        OreDictionary.registerOre("dyeBrown",new ItemStack(ANOTHER_DYE,1,2));
        OreDictionary.registerOre("dyeBlue",new ItemStack(ANOTHER_DYE,1,3));
        OreDictionary.registerOre("dyeWhite",new ItemStack(ANOTHER_DYE,1,4));

        OreDictionary.registerOre("magicFlintWeak", new ItemStack(MAGIC_FLINTS, 1,0));
        OreDictionary.registerOre("magicFlintWeak", new ItemStack(MAGIC_FLINTS, 1,1));

        if(ammpdbm_mod.isLoadedTwilight){
            setRegister(TRANSFORMATION_DUST);
            setRegister(CARMINITE_AXE);
            setRegister(CARMINITE_PICKAXE);
        }
        if(ammpdbm_mod.isLoadedTinkers) {
            OreDictionary.registerOre("bone", new ItemStack(Item.getByNameOrId("tconstruct:materals"),1,17));
        }
        if(ammpdbm_mod.isLoadedProjectX) {
            if(ammpdbm_mod.isLoadedSulfurTorches) {
                setRegister(SULFUR);
                setRegister(ALUMINUM);
                setRegister(xyAMULET);
                OreDictionary.registerOre("nuggetAluminum",new ItemStack(ItemRegister.ALUMINUM));
                OreDictionary.registerOre("nuggetAluminium",new ItemStack(ItemRegister.ALUMINUM));
                OreDictionary.registerOre("dustSulfur",new ItemStack(ItemRegister.SULFUR));
            }

            OreDictionary.registerOre("dyeBlue", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 0));
            OreDictionary.registerOre("dyeLime", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 1));
            OreDictionary.registerOre("dyeRed", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 2));
            OreDictionary.registerOre("dyeBlack", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 3));
            OreDictionary.registerOre("dyeWhite", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 4));
        }
        if(ammpdbm_mod.isLoadedBaubles){
            setRegister(SAVIOUR);
        }

        MinecraftForge.addGrassSeed(new ItemStack(CURING_GRASS),10);
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
        setRender(NETHER_DRINK);
        setRender(POTATO_C);
        setRender(CHICKEN_C);
        setRender(FISH_C);
        setRender(ROUND_STONE);
        setRender(MAGNETPICK);
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
        setRender(PAINT_OPERATOR);
        setRender(DIAMOND_GOLD);
        setRender(GOLDEN_DIAMOND);
        setRender(DIAMONDGOLDEN_GOLDEN_DIAMOND);

        if(ammpdbm_mod.isLoadedBaubles)
            setRender(SAVIOUR);

        if(ammpdbm_mod.isLoadedTwilight) {
            setRender(TRANSFORMATION_DUST);
            setRender(CARMINITE_AXE);
            setRender(CARMINITE_PICKAXE);
        }
        if(ammpdbm_mod.isLoadedProjectX){
            if(ammpdbm_mod.isLoadedSulfurTorches) {
                setRender(ALUMINUM);
                setRender(SULFUR);
                setRender(xyAMULET);
            }
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