package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.broomitems.*;
import koldunec.ammpdbm_mod.broomitems.baseItems.basic_block;
import koldunec.ammpdbm_mod.broomitems.baseItems.basic_planks;
import koldunec.ammpdbm_mod.broomitems.curinggrass.curingCrops;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRegister
{
    public static Block WATER_PORTAL = new basic_block("portal_water", Material.IRON,"pickaxe",1,5F, 30.0F);
    public static Block BLOCK_CHARCOAL = new basic_block("block_charcoal",Material.ROCK, "pickaxe",0,5F ,30F );
    public static Block CURING_CROPS = new curingCrops("curing_crops");

    public static Block OLD_ROSE = new magic_flower("rose");
    public static Block OLD_ROSE_b = new magic_flower("rose_b");


    public static Block BRICKS_Light = new basic_block("block_bricks_light",Material.ROCK, "pickaxe",0,2F ,30F);
    public static Block BRICKS_Dark = new basic_block("block_bricks_dark",Material.ROCK, "pickaxe",0,2F ,30F);
    public static Block COBBLE_MOSS_old = new basic_block("block_oldmoss",Material.ROCK, "pickaxe",0,2F ,30F);
    public static Block PLANKS_Light = new basic_planks("block_planks_light", 2F ,15F);
    public static Block PLANKS_uncommon = new basic_planks("block_planks_uncommon", 2F ,15F);

    public static Block ORE_BIT = new bitore("ore_bit", ItemRegister.BITCOIN,2,5,6);
    public static Block ORE_SPARKLE = new bitore("sparkleore",ItemRegister.SPARKLES,5,7,11);
    public static Block ORE_EGG = new eggore("ore_egg",64);
    public static Block ORE_RAINBOW = new rainbow_ore("ore_rainbow",ItemRegister.ESSENCE_RAINBOW,1,2,3);


    //public static Block TOWER_FURNACE = new TowerFurnace(2F,30F);


    public static void register()
    {
        registerBlock(WATER_PORTAL);
        registerBlock(ORE_BIT);
        registerBlock(ORE_EGG);
        registerBlock(ORE_SPARKLE);
        registerBlock(BLOCK_CHARCOAL);
        //registerBlock(OLD_ROSE);
        //registerBlock(OLD_ROSE);
        registerBlock(BRICKS_Light);
        registerBlock(BRICKS_Dark);
        registerBlock(PLANKS_Light);
        registerBlock(PLANKS_uncommon);
        registerBlock(COBBLE_MOSS_old);
        registerBlock(ORE_RAINBOW);
        ForgeRegistries.BLOCKS.register(CURING_CROPS);
        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest"));

    }

    @SideOnly(Side.CLIENT)
    public static void registerRender()
    {
        setRender(WATER_PORTAL);
        setRender(ORE_BIT);
        setRender(ORE_EGG);
        setRender(ORE_SPARKLE);
        setRender(BLOCK_CHARCOAL);
        //setRender(OLD_ROSE);
        //setRender(OLD_ROSE_b);
        setRender(BRICKS_Dark);
        setRender(BRICKS_Light);
        setRender(PLANKS_Light);
        setRender(PLANKS_uncommon);
        setRender(COBBLE_MOSS_old);
        setRender(ORE_RAINBOW);
        //setRender(CURING_CROPS);

    }

    private static void registerBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}