package koldunec.vint.compatibility;

import net.minecraft.init.Blocks;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.common.carving.Carving;

import java.util.HashSet;

import static koldunec.vint.compatibility.OtherTweaker.chiselInsert;
import static koldunec.vint.compatibility.OtherTweaker.getGroupName;

public class Chisel_with_Railcraft {

    static HashSet<String> tasks = new HashSet<String>(){{
        add("brick_sandy");
        add("brick_bloodstained");
        add("brick_frostbound");
        add("brick_infernal");
        add("brick_quarried");
        add("brick_bleachedbone");
        add("brick_abyssal");
        add("brick_red_sandy");
        add("brick_pearlized");
    }};


    public static void init(){
        // Granite
        String carv = getGroupName(Blocks.STONE,1);
        for(int i=0; i<6; ++i) {
            if(i==2) continue;          //all questions to railcraft devs
            chiselInsert(carv, "railcraft:brick_granite", i);
        }
        // Diorite
        carv = getGroupName(Blocks.STONE,3);
        for(int i=0; i<6; ++i) {
            if (i == 2) continue;
            chiselInsert(carv, "railcraft:brick_diorite", i);
        }
        // Andesite
        carv = getGroupName(Blocks.STONE,5);
        for(int i=0; i<6; ++i) {
            if (i == 2) continue;
            chiselInsert(carv, "railcraft:brick_andesite", i);
        }
        //Nether Bricks
        carv = getGroupName(Blocks.NETHER_BRICK,0);
        for(int i=1; i<6; ++i)
            chiselInsert(carv, "railcraft:brick_nether", i);

        carv = "red_nether_brick";
        Carving.chisel.addGroup(CarvingUtils.getDefaultGroupFor(carv));
        chiselInsert(carv, Blocks.RED_NETHER_BRICK,0);
        for(int i=1; i<6; ++i){
            chiselInsert(carv,"railcraft:brick_red_nether",i);
        }

        for(String item: tasks) {
            Carving.chisel.addGroup(CarvingUtils.getDefaultGroupFor("railvint_"+item));
            for (int i = 0; i < 6; ++i)
                chiselInsert("railvint_"+item, "railcraft:"+item, i);
        }
        tasks.clear();
        tasks = null;
    }



}
