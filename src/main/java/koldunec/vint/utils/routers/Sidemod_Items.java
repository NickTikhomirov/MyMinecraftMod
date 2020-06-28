package koldunec.vint.utils.routers;

import koldunec.vint.IntegrationHelper;
import net.daveyx0.primitivemobs.core.PrimitiveMobsItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Sidemod_Items {
    public static Item Camodye(){
        if(IntegrationHelper.isLoadedPrimitive)
            return PrimitiveMobsItems.CAMOUFLAGE_DYE;
        return Items.AIR;
    }
}
