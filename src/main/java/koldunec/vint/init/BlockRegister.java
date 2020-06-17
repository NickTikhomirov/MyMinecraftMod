package koldunec.vint.init;

import koldunec.vint.blocks.*;
import koldunec.vint.blocks.plants.BorerReed;
import koldunec.vint.blocks.plants.GlowCactus;
import koldunec.vint.blocks.plants.TorchBerry;
import koldunec.vint.items.*;
import koldunec.vint.items.baseItems.basic_block;
import koldunec.vint.items.baseItems.basic_planks;
import koldunec.vint.items.curinggrass.curingCrops;
import koldunec.vint.items.gunpowder_reed.block_gunreed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

public class BlockRegister {
    public static HashSet<Block> DEFAULT_INIT = new HashSet<>();

    public static Block BASALT_RAW = put(new BlockTriDirectional("basalt_raw"));
    public static Block BASALT_PILLAR = put(new BlockTriDirectional("basalt_pillar"));
    public static Block FRESH_DEBRIS = put(new BlockTriDirectional("debris"));

    public static Block RED_NYLIUM = put(new BaseNylium("red_nylium"));
    public static Block BLUE_NYLIUM = put(new BaseNylium("blue_nylium"));
    public static Block WARPED_WART = put(BaseNylium.BuildWarpedWart());

    public static Block RED_NY_LOG = put(new BlockTriDirectional("red_nether_log"));
    public static Block RED_NY_LOG_STRIP = put(new BlockTriDirectional("red_nether_log_strip"));
    public static Block BLUE_NY_LOG = put(new BlockTriDirectional("blue_nether_log"));
    public static Block BLUE_NY_LOG_STRIP = put(new BlockTriDirectional("blue_nether_log_strip"));

    public static Block RED_NETHER_MUSH = put(new NetherShroom("red_nether_shroom"));
    public static Block BLUE_NETHER_MUSH = put(new NetherShroom("blue_nether_shroom"));

    public static Block NETHER_CACTUS = put(new GlowCactus());

    public static Block WATER_PORTAL = new basic_block("portal_water", Material.IRON,"pickaxe",1,5F, 30.0F,0);
    public static Block CURING_CROPS = new curingCrops("curing_crops");
    public static Block TORCH_CROPS = new TorchBerry();
    public static Block GHAST_POD = new block_ghastpod();
    public static Block SHROOMLIGHT = put(new ShroomLight());

    public static Block LLAMA_SPAWNER = new LlamaFlower();
    public static Block STORE = put(new stORE());
    public static Block REED_GUNPOWDER = new block_gunreed();
    public static Block REED_BORER = new BorerReed();

    public static Block BRICKS_Light = new basic_block("block_bricks_light",Material.ROCK, "pickaxe",0,2F ,30F,255);
    public static Block BRICKS_Dark = new basic_block("block_bricks_dark",Material.ROCK, "pickaxe",0,2F ,30F,255);
    public static Block COBBLE_MOSS_old = new basic_block("block_oldmoss",Material.ROCK, "pickaxe",0,2F ,30F,255);
    public static Block COBBLE_MOSS_red = new basic_block("block_redmoss",Material.ROCK,"pickaxe",0,2F,30F,255);
    public static Block COBBLE_old = new basic_block("block_oldstone",Material.ROCK,"pickaxe",0,2F,30F,255);
    public static Block PLANKS_Light = new basic_planks("block_planks_light", 2F ,15F);
    public static Block PLANKS_uncommon = new basic_planks("block_planks_uncommon", 2F ,15F);

    public static Block ORE_BIT = put(new bitore("ore_bit",2,5,6));
    public static Block ORE_EGG = put(new eggore("ore_egg",64));
    public static Block ORE_RAINBOW = put(new rainbow_ore("ore_rainbow",1,2,3));

    public static Block ORE_ALUMINUM = new bitore("ore_al",4,7,200);
    public static Block ALBLOCK = new basic_block("block_aluminum",Material.IRON,"pickaxe",1,1F,50F,10);

    //public static Block TOWER_FURNACE = new TowerFurnace(2F,30F);


    public static void register() {
        for(Block b: DEFAULT_INIT)
            registerBlock(b);


        registerBlock(WATER_PORTAL);
        registerBlock(BRICKS_Light);
        registerBlock(BRICKS_Dark);
        registerBlock(PLANKS_Light);
        registerBlock(PLANKS_uncommon);
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
        }


    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for(Block b: DEFAULT_INIT)
            setRender(b);

        setRender(WATER_PORTAL);
        setRender(BRICKS_Dark);
        setRender(BRICKS_Light);
        setRender(PLANKS_Light);
        setRender(PLANKS_uncommon);
        setRender(COBBLE_MOSS_old);
        setRender(COBBLE_MOSS_red);
        setRender(COBBLE_old);
        setRender(LLAMA_SPAWNER,0);
        setRender(LLAMA_SPAWNER,1);

        //if(vint.isLoadedTwilight)
            //setRender(TOWER_FURNACE);

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