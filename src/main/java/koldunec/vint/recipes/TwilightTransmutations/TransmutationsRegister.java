package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.DurabilityConversion;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.ITwilightResult;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.MWTransform;
import koldunec.vint.tileentities.TileTower;
import koldunec.vint.tileentities.containers.ContainerTower;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

import java.util.ArrayList;
import java.util.HashMap;

public class TransmutationsRegister {

    /**
     * If you need to add recipe that with NBT of ItemStacks, you must make your own ITwilightResult
     */
    public static HashMap<RecipeInput, ITwilightResult> RECIPES_1 = new HashMap<>();
    public static ArrayList<ITwilightResult> RECIPES_2 = new ArrayList<ITwilightResult>();

    public static ITwilightResult getResult(ItemStack base, ItemStack catalyst, TileTower tile){
        RecipeInput input = new RecipeInput(base,catalyst);
        ITwilightResult result = RECIPES_1.get(input);
        if(result!=null)
            return result;
        for(ITwilightResult i: RECIPES_2)
            if(i.canProcess(base,catalyst,tile))
                return i;
        return null;
    }

    public static ITwilightResult getResult(RecipeInput input){
        return RECIPES_1.get(input);
    }

    public static void put(ItemStack in, ITwilightResult out, ItemStack cata){
        if(in==null || out==null)
            return;
        if(in.isEmpty())
            return;
        RecipeInput input = new RecipeInput(in, cata);
        if(getResult(input)!=null)
            return;
        RECIPES_1.put(new RecipeInput(in,cata), out);
    }

    public static void put(RecipeInput in, ITwilightResult out){
        if(in==null || out==null)
            return;
        if(in.getBase().equals(Items.AIR) || in.getCatalyst().equals(Items.AIR))
            return;
        if(getResult(in)!=null)
            return;
        RECIPES_1.put(in, out);
    }


    public static void init(){
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        FuelHelper.init();
        TwilightTransmutationsData.initCarminite();
        TwilightTransmutationsData.initCinder();
        TwilightTransmutationsData.initTransform();
        TwilightTransmutationsData.initDragonBreath();
        TwilightTransmutationsData.initIce();
        TwilightTransmutationsData.initRepairs();
        TwilightTransmutationsData.initNether();
        TwilightTransmutationsData.initOthers();
        TwilightTransmutationsIntegration.initIntegration();
        TwilightTransmutationsData.initWoods(new ItemStack(TFItems.magic_map_focus), 100);
        TwilightTransmutationsData.initWoods(new ItemStack(TFBlocks.magic_log_core, 1, 1), 50);

        ContainerTower.CATALYSTS_FOR_TRANSFER.put(TFItems.magic_map_focus, 0);

        RECIPES_2.add(new MWTransform());
        MWTransform.assignJEI();
        DurabilityConversion.selfRegister();
    }
}
