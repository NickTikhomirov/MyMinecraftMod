package koldunec.ammpdbm_mod.broomitems.baseItems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.item.ItemFood;



public class base_food extends ItemFood {

    public base_food(String name, int stackMAX, int foodpoints, float satpoints, boolean forWolves) {
        super(foodpoints,satpoints,forWolves);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setMaxStackSize(stackMAX);
    }
}
