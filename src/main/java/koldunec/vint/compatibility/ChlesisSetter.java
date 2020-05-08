package koldunec.vint.compatibility;


import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.recipes.AlternativeCarving;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ChlesisSetter {

    public static void init(){
        initStone();
        initDiorite();
        initBasalt();
        initGranite();
        initSandStone();
    }

    private static void initStone(){
        String s = "Stone";
        register(Blocks.STONE,0, s);
        register(Blocks.STONE,5, s);
        register(Block.getBlockFromName("chisel:limestone2"),7,s);
        if(IntegrationHelper.isLoadedQuark){
            register(Block.getBlockFromName("quark:limestone"), 0, s);
            register(Block.getBlockFromName("quark:biome_cobblestone"), 2, s);
        }
    }

    private static void initBasalt(){
        String s = "Basalt";
        register(BlockRegister.BASALT_RAW,0,s);
        register(BlockRegister.BASALT_PILLAR,0,s);
        if(IntegrationHelper.isLoadedProjectRed_exploration)
            register(Block.getBlockFromName(IntegrationHelper.idProjectRed+":stone"),3,s);  //Basalt
        if(IntegrationHelper.isLoaded("railcraft"))
            register(Block.getBlockFromName("railcraft:generic"), 8, s);   //abyssal stone
        if(IntegrationHelper.isLoaded("netherex"))
            register(Block.getBlockFromName("netherex:basalt"), 0, s);
        if(IntegrationHelper.isLoadedChisel)
            register(Block.getBlockFromName("chisel:basalt2"),7,s);

    }

    private static void initDiorite(){
        String s = "Diorite";
        register(Blocks.STONE,3, s);
        register(Block.getBlockFromName("chisel:marble2"),7,s);
        if(IntegrationHelper.isLoadedProjectRed_exploration){
            register(Block.getBlockFromName(IntegrationHelper.idProjectRed+":stone"),0,s);  //marble
        }
        if(IntegrationHelper.isLoaded("railcraft")){
            register(Block.getBlockFromName("railcraft:generic"), 9, s);   //quarried stone
        }
        if(IntegrationHelper.isLoaded("astralsorcery")){
            register(Block.getBlockFromName("astralsorcery:blockmarble"),0, s);
        }
        if(IntegrationHelper.isLoadedQuark){
            register(Block.getBlockFromName("quark:marble"), 0, s);
        }
    }


    private static void initGranite(){
        String s = "Granite";
        register(Blocks.STONE,1, s);
        if(IntegrationHelper.isLoadedQuark){
            register(Block.getBlockFromName("quark:slate"), 0, s);
            register(Block.getBlockFromName("quark:biome_cobblestone"), 0, s);
            register(Block.getBlockFromName("quark:biome_cobblestone"), 1, s);
            register(Block.getBlockFromName("quark:jasper"), 0, s);
        }

    }


    private static void initSandStone(){
        String s = "Sandstone";
        register(Blocks.SANDSTONE,0, s);
        register(Blocks.RED_SANDSTONE,0, s);
        register(Blocks.HARDENED_CLAY,0, s);
        register(Blocks.BRICK_BLOCK,0, s);
        register(BlockRegister.BRICKS_Dark,0,s);
        register(BlockRegister.BRICKS_Light,0,s);
        register(Block.getBlockFromName("chisel:brownstone"),0,s);
        if(IntegrationHelper.isLoadedQuark){
            register(Block.getBlockFromName("quark:sandy_bricks"), 0, s);
        }
        if(IntegrationHelper.isLoaded("railcraft")){
            register(Block.getBlockFromName("railcraft:brick_sandy"), 2, s);
            register(Block.getBlockFromName("railcraft:brick_red_sandy"), 2, s);
        }
        if(IntegrationHelper.isLoadedTinkers){
            register(Block.getBlockFromName("tconstruct:dried_clay"),0,s);
            register(Block.getBlockFromName("tconstruct:dried_clay"),1,s);
        }
    }



    static void register(Block b, int meta, String name){
        AlternativeCarving.add(new ItemStack(b, 1, meta), name);
    }



}
