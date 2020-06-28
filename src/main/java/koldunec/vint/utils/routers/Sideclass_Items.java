package koldunec.vint.utils.routers;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.items.Chlesis;
import koldunec.vint.items.tools.CarminitePick;
import koldunec.vint.objectbuilders.SimpleItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Sideclass_Items {
    public static Item ChlesisLoader(){
        if(IntegrationHelper.isLoadedChisel)
            return new Chlesis();
        return new SimpleItems.SimpleItem("chlesis", 1);
    }

    public static Item CarminitePickLoader(){
        if(IntegrationHelper.isLoadedTwilight)
            return new CarminitePick();
        return Items.AIR;
    }
}
