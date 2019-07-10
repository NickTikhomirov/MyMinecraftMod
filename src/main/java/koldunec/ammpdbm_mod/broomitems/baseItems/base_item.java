package koldunec.ammpdbm_mod.broomitems.baseItems;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.item.Item;


public class base_item extends Item {

    public base_item(String name, int stackMAX) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setMaxStackSize(stackMAX);
    }

}
