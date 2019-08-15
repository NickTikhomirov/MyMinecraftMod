package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.shared.TinkerCommons;

public class OredictRegister {

    public static void oreRegisterItems(){
        OreDictionary.registerOre("dyeBlack",new ItemStack(ItemRegister.ANOTHER_DYE,1,0));
        OreDictionary.registerOre("dyeGreen",new ItemStack(ItemRegister.ANOTHER_DYE,1,1));
        OreDictionary.registerOre("dyeBrown",new ItemStack(ItemRegister.ANOTHER_DYE,1,2));
        OreDictionary.registerOre("dyeBlue",new ItemStack(ItemRegister.ANOTHER_DYE,1,3));
        OreDictionary.registerOre("dyeWhite",new ItemStack(ItemRegister.ANOTHER_DYE,1,4));
        OreDictionary.registerOre("magicFlintWeak", new ItemStack(ItemRegister.MAGIC_FLINTS, 1,0));
        OreDictionary.registerOre("magicFlintWeak", new ItemStack(ItemRegister.MAGIC_FLINTS, 1,1));
    }

    public static void oreRegisterBlocks(){

    }

    public static void oreRegisterSideItems(){
        //this code crashes if executed in preInit, but works fine in init
        //yeah, you hear me right, this code WORKS... finally
        //removed because of adding conceptualization recipe
        //if(ammpdbm_mod.isLoadedTinkers) {
        //    OreDictionary.registerOre("bone", TinkerCommons.matNecroticBone.copy());
        //}
        if(ammpdbm_mod.isLoadedProjectX){
            OreDictionary.registerOre("dyeBlue", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 0));
            OreDictionary.registerOre("dyeLime", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 1));
            OreDictionary.registerOre("dyeRed", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 2));
            OreDictionary.registerOre("dyeBlack", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 3));
            OreDictionary.registerOre("dyeWhite", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 4));
        }
        if(ammpdbm_mod.isLoadedSulfurTorches) {
            OreDictionary.registerOre("nuggetAluminum",new ItemStack(ItemRegister.ALUMINUM));
            OreDictionary.registerOre("nuggetAluminium",new ItemStack(ItemRegister.ALUMINUM));
            OreDictionary.registerOre("dustSulfur",new ItemStack(ItemRegister.SULFUR));
        }
    }
}
