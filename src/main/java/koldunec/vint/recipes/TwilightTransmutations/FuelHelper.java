package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.utils.routers.Sidemod_Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class FuelHelper {

    public static ArrayList<IFuelHandler> REGISTRY = new ArrayList<>();

    public static int CountFuelValue(ItemStack i){
        for(IFuelHandler handler: REGISTRY)
            if(handler.canHandle(i))
                return handler.getFuelValue(i);
        return 0;
    }


    public static boolean IsValidFuel(ItemStack i){
        return CountFuelValue(i)>0;
    }


    // called in TransmutationsRegister
    public static void init(){
        REGISTRY.add(new DefaultFuelHandler());
    }

    public interface IFuelHandler{
        boolean canHandle(ItemStack i);
        int getFuelValue(ItemStack i);
    }

    public static class DefaultFuelHandler implements IFuelHandler{
        @Override
        public boolean canHandle(ItemStack i) {
            if(!IntegrationHelper.isLoadedTwilight)
                return false;
            if(i.getItem().equals(ItemRegister.TRANSFORMATION_DUST))
                return true;
            if(i.getItem().equals(Sidemod_Items.TransformPowder()))
                return true;
            return i.getItem().equals(Sidemod_Items.Borer());
        }

        @Override
        public int getFuelValue(ItemStack i) {
            if(i.getItem().equals(ItemRegister.TRANSFORMATION_DUST))
                return 50;
            if(i.getItem().equals(Sidemod_Items.TransformPowder()))
                return 500;
            return 100;
        }
    }
}
