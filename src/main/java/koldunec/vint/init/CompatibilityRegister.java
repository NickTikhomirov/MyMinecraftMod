package koldunec.vint.init;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.ChlesisSetter;
import koldunec.vint.compatibility.OtherTweaker;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.compatibility.TwilightForest.MainTFModule;
import koldunec.vint.tileentities.containers.ContainerChlesis;
import koldunec.vint.utils.GemHelper;
import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class CompatibilityRegister {
    public static Item FUTURE_BAMBOO;

    public static void preInit(){
        oreRegisterItems();
        oreRegisterBlocks();
        grassRegisterItems();
        GemHelper.preInit();
        if(IntegrationHelper.isLoadedTinkers) {
            vint.LOGGER.info("Started processing interactions with Tinkers Construct");
            TinkerIntegration.preInit();
        }
    }

    public static void init(){
        if(IntegrationHelper.isLoadedFuture)
            FUTURE_BAMBOO = Item.getByNameOrId(IntegrationHelper.idFuture+":bamboo");
        oreRegisterSideItems();
        OtherTweaker.trivia();
        if(IntegrationHelper.isLoadedTwilight)
            MainTFModule.updateGlass();
        if(IntegrationHelper.isLoadedTinkers)
            TinkerIntegration.init();

        if(IntegrationHelper.isLoadedChisel){
            ContainerChlesis.initMyCarving();
            ChlesisSetter.init();
            OtherTweaker.chiselUpgrade();
        }
    }

    public static void postInit(){
        GemHelper.postInit();
        if(IntegrationHelper.isLoadedTinkers)
            TinkerIntegration.postInit();

    }



    private static void grassRegisterItems(){
        MinecraftForge.addGrassSeed(new ItemStack(ItemRegister.VITASARIA_SEEDS),10);
        MinecraftForge.addGrassSeed(new ItemStack(ItemRegister.ANOTHER_DYE,1,1),3);
    }


    private static void oreRegisterItems(){
        OreDictionary.registerOre("dyeBlack",new ItemStack(ItemRegister.ANOTHER_DYE,1,0));
        OreDictionary.registerOre("dyeGreen",new ItemStack(ItemRegister.ANOTHER_DYE,1,1));
        OreDictionary.registerOre("dyeBrown",new ItemStack(ItemRegister.ANOTHER_DYE,1,2));
        OreDictionary.registerOre("dyeBlue",new ItemStack(ItemRegister.ANOTHER_DYE,1,3));
        OreDictionary.registerOre("dyeWhite",new ItemStack(ItemRegister.ANOTHER_DYE,1,4));
    }

    private static void oreRegisterBlocks(){

    }

    private static void oreRegisterSideItems(){
        if(IntegrationHelper.isLoadedProjectX){
            OreDictionary.registerOre("dyeBlue", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 0));
            OreDictionary.registerOre("dyeLime", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 1));
            OreDictionary.registerOre("dyeRed", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 2));
            OreDictionary.registerOre("dyeBlack", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 3));
            OreDictionary.registerOre("dyeWhite", new ItemStack(Item.getByNameOrId("projectx:xycronium_nugget"), 1, 4));
        }
        if(IntegrationHelper.isLoadedTwilight){
            OreDictionary.registerOre("ingotIronwood", TFItems.ironwood_ingot);
            OreDictionary.registerOre("blockIronwood", new ItemStack(TFBlocks.block_storage, 1, 0));
        }
        if(IntegrationHelper.isLoadedFuture && IntegrationHelper.isLoaded("culinaryconstruct")){
            OreDictionary.registerOre("bread",ItemRegister.BAMBOO_BREAD);
        }
    }
}
