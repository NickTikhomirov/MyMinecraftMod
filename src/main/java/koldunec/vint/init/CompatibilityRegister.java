package koldunec.vint.init;

import koldunec.vint.compatibility.ChlesisSetter;
import koldunec.vint.compatibility.OtherTweaker;
import koldunec.vint.compatibility.TinkerIntegration;
import koldunec.vint.containers.ContainerChlesis;
import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompatibilityRegister {


    public static void preInit(){
        oreRegisterItems();
        oreRegisterBlocks();
        if(IntegrationHelper.isLoadedTinkers)
            TinkerIntegration.preInit();
    }

    public static void init(){
        oreRegisterSideItems();
        OtherTweaker.trivia();
        if(IntegrationHelper.isLoadedTinkers)
            TinkerIntegration.init();


        if(IntegrationHelper.isLoadedChisel){
            ContainerChlesis.initMyCarving();
            ChlesisSetter.init();
            OtherTweaker.chiselUpgrade();
        }
    }

    public static void postInit(){
        if(IntegrationHelper.isLoadedTinkers)
            TinkerIntegration.postInit();
    }





    public static void oreRegisterItems(){
        OreDictionary.registerOre("dyeBlack",new ItemStack(ItemRegister.ANOTHER_DYE,1,0));
        OreDictionary.registerOre("dyeGreen",new ItemStack(ItemRegister.ANOTHER_DYE,1,1));
        OreDictionary.registerOre("dyeBrown",new ItemStack(ItemRegister.ANOTHER_DYE,1,2));
        OreDictionary.registerOre("dyeBlue",new ItemStack(ItemRegister.ANOTHER_DYE,1,3));
        OreDictionary.registerOre("dyeWhite",new ItemStack(ItemRegister.ANOTHER_DYE,1,4));
    }

    public static void oreRegisterBlocks(){

    }

    public static void oreRegisterSideItems(){
        if(IntegrationHelper.isLoadedProjectX){
            OreDictionary.registerOre("dyeBlue", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 0));
            OreDictionary.registerOre("dyeLime", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 1));
            OreDictionary.registerOre("dyeRed", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 2));
            OreDictionary.registerOre("dyeBlack", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 3));
            OreDictionary.registerOre("dyeWhite", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 4));
        }
    }
}
