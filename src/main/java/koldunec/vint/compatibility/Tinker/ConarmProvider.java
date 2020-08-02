package koldunec.vint.compatibility.Tinker;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterials;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.tools.TinkerTraits;

public class ConarmProvider {
    public static ITrait getSubterranean(){
        return ArmorTraits.subterranean;
    }

    public static ITrait getPetravidity(){
        return ArmorTraits.petravidity;
    }

    public static ITrait get(ITrait i){
        if(i.equals(TinkerTraits.ecological))
            return ArmorTraits.ecological;

        return null;
    }


    public static void addArmorTrait(Material material, ITrait trait) {
        ArmorMaterials.addArmorTrait(material, trait, (ITrait)null);
    }

    public static void addArmorTrait(Material material, ITrait trait, String type) {
        material.addTrait(trait, type);
    }
}
