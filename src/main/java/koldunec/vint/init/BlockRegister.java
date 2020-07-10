package koldunec.vint.init;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.blocks.*;
import koldunec.vint.blocks.plants.BorerReed;
import koldunec.vint.blocks.plants.GhastPod;
import koldunec.vint.blocks.plants.GlowCactus;
import koldunec.vint.blocks.plants.TorchBerry;
import koldunec.vint.items.*;
import koldunec.vint.items.baseItems.basic_block;
import koldunec.vint.items.curinggrass.curingCrops;
import koldunec.vint.items.gunpowder_reed.block_gunreed;
import koldunec.vint.objectbuilders.ObjectBuilder;
import koldunec.vint.objectbuilders.TriDirectionaBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

public class BlockRegister {
    public static HashSet<Block> DEFAULT_INIT = new HashSet<>();

    public static Block BASALT_RAW = put(TriDirectionaBuilder.BuildStone("basalt_raw"));
    public static Block BASALT_PILLAR = put(TriDirectionaBuilder.BuildStone("basalt_pillar"));
    public static Block FRESH_DEBRIS = put(TriDirectionaBuilder.BuildStone("debris"));
    public static Block BLACKSTONE = put(ObjectBuilder.BuildRock("blackstone"));
    public static Block BLACKCOBBLE = put(ObjectBuilder.BuildRock("blackcobble"));
    public static Block BLACKSTONE_BRICKS = put(ObjectBuilder.BuildRock("blackstone_bricks"));
    public static Block BLACKSTONE_POLISHED = put(ObjectBuilder.BuildRock("blackstone_polished"));
    public static Block BLACKSTONE_CHISELED = put(ObjectBuilder.BuildRock("blackstone_chiseled"));

    public static Block RED_NYLIUM = put(new BaseNylium("red_nylium"));
    public static Block BLUE_NYLIUM = put(new BaseNylium("blue_nylium"));
    public static Block GOLD_NYLIUM = put(new BaseNylium("gold_nylium"));
    public static Block WARPED_WART = put(ObjectBuilder.BuildWarpedWart());

    public static Block RED_NY_LOG = put(TriDirectionaBuilder.BuildLog("red_nether_log"));
    public static Block RED_NY_LOG_STRIP = put(TriDirectionaBuilder.BuildLog("red_nether_log_strip"));
    public static Block BLUE_NY_LOG = put(TriDirectionaBuilder.BuildLog("blue_nether_log"));
    public static Block BLUE_NY_LOG_STRIP = put(TriDirectionaBuilder.BuildLog("blue_nether_log_strip"));

    public static Block RED_NETHER_MUSH = put(new NetherShroom("red_nether_shroom"));
    public static Block BLUE_NETHER_MUSH = put(new NetherShroom("blue_nether_shroom"));

    public static Block RED_PLANKS = put(ObjectBuilder.BuildDefaultPlanks("red_planks",false));
    public static Block BLUE_PLANKS = put(ObjectBuilder.BuildDefaultPlanks("blue_planks",false));

    public static Block NETHER_CACTUS = put(new GlowCactus());

    public static Block WATER_PORTAL = new basic_block("portal_water", Material.IRON,"pickaxe",1,5F, 30.0F,0);
    public static Block CURING_CROPS = new curingCrops("curing_crops");
    public static Block TORCH_CROPS = new TorchBerry();
    public static Block GHAST_POD = new GhastPod();
    public static Block SHROOMLIGHT = put(ObjectBuilder.BuildShroomLight());

    public static Block LLAMA_SPAWNER = new LlamaFlower();
    public static Block STORE = put(new stORE());
    public static Block REED_GUNPOWDER = new block_gunreed();
    public static Block REED_BORER = new BorerReed();

    public static Block BRICKS_Light = new basic_block("block_bricks_light",Material.ROCK, "pickaxe",0,2F ,30F,255);
    public static Block BRICKS_Dark = new basic_block("block_bricks_dark",Material.ROCK, "pickaxe",0,2F ,30F,255);
    public static Block COBBLE_MOSS_old = new basic_block("block_oldmoss",Material.ROCK, "pickaxe",0,2F ,30F,255);
    public static Block COBBLE_MOSS_red = new basic_block("block_redmoss",Material.ROCK,"pickaxe",0,2F,30F,255);
    public static Block COBBLE_old = new basic_block("block_oldstone",Material.ROCK,"pickaxe",0,2F,30F,255);
    public static Block PLANKS_Light = put(ObjectBuilder.BuildDefaultPlanks("block_planks_light", true));
    public static Block PLANKS_uncommon = put(ObjectBuilder.BuildDefaultPlanks("block_planks_uncommon", true));

    public static Block ORE_EGG = put(new eggore("ore_egg",64));
    public static Block ORE_RAINBOW = put(new RainbowOre("ore_rainbow"));

    public static Block TOWER_FURNACE = new TowerFurnace(false);
    public static Block TOWER_FURNACE_LIT = new TowerFurnace(true);
    public static Block FAKE_IRON = put(ObjectBuilder.BuildFake("fake_iron"));
    public static Block FAKE_GOLD = put(ObjectBuilder.BuildFake("fake_gold"));
    public static Block FAKE_DIAMOND = put(ObjectBuilder.BuildFake("fake_diamond"));
    public static Block FAKE_NETHERITE = put(ObjectBuilder.BuildFake("fake_netherite"));
    public static Block FAKE_EMERALD = put(ObjectBuilder.BuildFake("fake_emerald"));


    public static void register() {
        for(Block b: DEFAULT_INIT)
            registerBlock(b);


        registerBlock(WATER_PORTAL);
        registerBlock(BRICKS_Light);
        registerBlock(BRICKS_Dark);
        registerBlock(COBBLE_MOSS_old);

        registerBlock(COBBLE_MOSS_red);
        registerBlock(COBBLE_old);
        registerBlockWithMeta(LLAMA_SPAWNER);
        ForgeRegistries.BLOCKS.register(CURING_CROPS);
        ForgeRegistries.BLOCKS.register(GHAST_POD);
        ForgeRegistries.BLOCKS.register(REED_GUNPOWDER);
        if(IntegrationHelper.isLoadedTwilight) {
            ForgeRegistries.BLOCKS.register(REED_BORER);
            ForgeRegistries.BLOCKS.register(TORCH_CROPS);
            registerBlock(TOWER_FURNACE);
            registerBlock(TOWER_FURNACE_LIT);
        }


    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for(Block b: DEFAULT_INIT)
            setRender(b);

        setRender(WATER_PORTAL);
        setRender(BRICKS_Dark);
        setRender(BRICKS_Light);
        setRender(COBBLE_MOSS_old);
        setRender(COBBLE_MOSS_red);
        setRender(COBBLE_old);
        setRender(LLAMA_SPAWNER,0);
        setRender(LLAMA_SPAWNER,1);

        if(IntegrationHelper.isLoadedTwilight) {
            setRender(TOWER_FURNACE);
            setRender(TOWER_FURNACE_LIT);
        }

    }

    private static void registerBlock(Block block){
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    private static void registerBlockWithMeta(Block block){
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setHasSubtypes(true));
    }

    private static Block put(Block b){
        DEFAULT_INIT.add(b);
        return b;
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}