package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
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
            return i.getItem().getRegistryName().getResourcePath().equals("borer_essence");
        }

        @Override
        public int getFuelValue(ItemStack i) {
            if(i.getItem().equals(ItemRegister.TRANSFORMATION_DUST))
                return 20;
            return 80;
        }
    }

    // called in TransmutationsRegister
    public static void init(){
        REGISTRY.add(new DefaultFuelHandler());
    }
}
