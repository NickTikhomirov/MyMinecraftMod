package koldunec.vint.compatibility;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.items.tools.Broom;
import koldunec.vint.items.agriculture.ReedPowderItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import svenhjol.charm.crafting.feature.Composter;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.common.carving.Carving;


public class OtherTweaker {
    public static void trivia(){
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.POWDER_REED,(ReedPowderItem)ItemRegister.POWDER_REED);
        if(IntegrationHelper.isLoadedQuark){
            Broom.affected_blocks.add(Block.getBlockFromName("quark:elder_sea_lantern"));
        }

        if(IntegrationHelper.isLoadedCharm){
            Composter.inputs.put("minecraft:rotten_flesh", 0.2F);
            Composter.inputs.put("minecraft:feather", 0.2F);
            Composter.inputs.put("minecraft:spider_eye",0.2F);
            Composter.inputs.put("vint:vanilla_powder",0.3F);
            Composter.inputs.put("minecraft:nether_wart", 0.5F);
            Composter.inputs.put("vint:curinggrass", 0.3F);
            Composter.inputs.put("natura:overworld_seeds", 0.3F);
            Composter.inputs.put("vint:radio_cactus", 0.65F);
            Composter.inputs.put("natura:materials[0]", 0.65F);     // barley
            Composter.inputs.put("minecraft:web", 1F);
            if(IntegrationHelper.isLoadedNatura){
                Composter.inputs.put("natura:overworld_seeds", 0.3F);
                Composter.inputs.put("natura:overworld_sapling", 0.4F);
                Composter.inputs.put("natura:overworld_sapling2", 0.4F);
            }
        }
    }

    public static void chiselUpgrade(){
        String carv = getGroupName(Block.getBlockFromName("chisel:basalt"),1);
        chiselInsert(carv, BlockRegister.BASALT_RAW,0);
        chiselInsert(carv, BlockRegister.BASALT_PILLAR,0);
        if(IntegrationHelper.isLoadedProjectRed_exploration)
            for(int i=2; i<5; ++i)
                chiselInsert(carv, IntegrationHelper.idProjectRed+":stone",i);


        if(IntegrationHelper.isLoadedCharm){
            carv = getGroupName(Blocks.GLOWSTONE,0);
            chiselInsert(carv,"charm:smooth_glowstone",0);
        }

        if(IntegrationHelper.isLoadedQuark){
            carv = getGroupName(Blocks.SANDSTONE,0);
            chiselInsert(carv,"quark:sandstone_new",0);
            chiselInsert(carv,"quark:sandstone_new",1);

            carv = getGroupName(Item.getByNameOrId("chisel:bookshelf_birch"),0);
            chiselInsert(carv,"quark:custom_bookshelf",1);
            carv = getGroupName(Item.getByNameOrId("chisel:bookshelf_acacia"),0);
            chiselInsert(carv,"quark:custom_bookshelf",3);
            carv = getGroupName(Item.getByNameOrId("chisel:bookshelf_darkoak"),0);
            chiselInsert(carv,"quark:custom_bookshelf",4);
            carv = getGroupName(Item.getByNameOrId("chisel:bookshelf_jungle"),0);
            chiselInsert(carv,"quark:custom_bookshelf",2);
            carv = getGroupName(Item.getByNameOrId("chisel:bookshelf_spruce"),0);
            chiselInsert(carv,"quark:custom_bookshelf",0);
        }

        if(IntegrationHelper.isLoaded("railcraft"))
            Chisel_with_Railcraft.init();
    }





    public static String getGroupName(Block b, int meta){
        return Carving.chisel.getGroup(new ItemStack(b,1,meta)).getName();
    }
    public static String getGroupName(Item b, int meta){
        return Carving.chisel.getGroup(new ItemStack(b,1,meta)).getName();
    }


    public static void chiselInsert(String s, String itemname, int meta){
        Carving.chisel.addVariation(s,CarvingUtils.variationFor(new ItemStack(Item.getByNameOrId(itemname),1,meta),10));
    }

    public static void chiselInsert(String s, Block i, int meta){
        Carving.chisel.addVariation(s,CarvingUtils.variationFor(new ItemStack(i,1,meta),10));
    }

    public static void chiselInsert(String s, Item i, int meta){
        Carving.chisel.addVariation(s,CarvingUtils.variationFor(new ItemStack(i,1,meta),10));
    }
}
