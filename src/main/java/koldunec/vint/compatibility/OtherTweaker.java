package koldunec.vint.compatibility;

import koldunec.vint.helpers.IntegrationHelper;
import koldunec.vint.vint;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import svenhjol.charm.crafting.feature.Composter;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.api.carving.ICarvingGroup;
import team.chisel.common.carving.Carving;


public class OtherTweaker {
    public static IntegrationHelper integrationHelper = vint.integrationHelper;

    public static void composterInit(){
        if(!integrationHelper.isLoaded("charm"))
            return;

        Composter.inputs.put("minecraft:rotten_flesh", 0.1F);
        Composter.inputs.put("minecraft:feather", 0.1F);
        Composter.inputs.put("vint:vanilla_powder",0.3F);
        Composter.inputs.put("minecraft:nether_wart", 0.5F);
        Composter.inputs.put("vint:curinggrass", 0.3F);
        Composter.inputs.put("vint:radio_cactus", 0.65F);
        Composter.inputs.put("minecraft:web", 1F);
    }

    public static void chiselUpgrade(){
        if(!integrationHelper.isLoaded("chisel"))
            return;
        if(integrationHelper.isLoaded("charm")){
            String carv = Carving.chisel.getGroup(new ItemStack(Blocks.GLOWSTONE)).getName();
            chiselInsert(carv,Item.getByNameOrId("charm:smooth_glowstone"),0);
        }
        if(integrationHelper.isLoaded("quark")){
            String carv = Carving.chisel.getGroup(new ItemStack(Blocks.SANDSTONE)).getName();
            chiselInsert(carv,Item.getByNameOrId("quark:sandstone_new"),0);
            chiselInsert(carv,Item.getByNameOrId("quark:sandstone_new"),1);
            carv = Carving.chisel.getGroup(new ItemStack(Blocks.BOOKSHELF)).getName();
            for(int i=0; i<5; ++i)
                chiselInsert(carv,Item.getByNameOrId("quark:custom_bookshelf"),i);
        }
    }


    public static void chiselInsert(String s, Item i, int meta){
        Carving.chisel.addVariation(s,CarvingUtils.variationFor(new ItemStack(i,1,meta),10));
    }
}
