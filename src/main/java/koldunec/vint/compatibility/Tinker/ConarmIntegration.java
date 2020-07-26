package koldunec.vint.compatibility.Tinker;

import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import koldunec.vint.compatibility.Tinker.traits_armour.Experimental;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class ConarmIntegration {
    public static AbstractTrait EXPERIMENTAL = new Experimental();



    public static AbstractMaterialStats getPlates(float modifier, float durability, float toughness){
        return new PlatesMaterialStats(modifier, durability, toughness);
    }

    public static AbstractMaterialStats getCore(float durability, float defense){
        return new CoreMaterialStats(durability, defense);
    }

    public static AbstractMaterialStats getTrim(float durability){
        return new TrimMaterialStats(durability);
    }
}
