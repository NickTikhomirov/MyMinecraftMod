package koldunec.vint.items.baseItems;

import koldunec.vint.vint;
import net.minecraft.item.ItemFood;



public class base_food extends ItemFood {

    public base_food(String name, int stackMAX, int foodpoints, float satpoints, boolean forWolves) {
        super(foodpoints,satpoints,forWolves);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(stackMAX);
    }
}
