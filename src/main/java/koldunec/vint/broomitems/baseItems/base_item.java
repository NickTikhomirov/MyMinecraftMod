package koldunec.vint.broomitems.baseItems;


import koldunec.vint.vint;
import net.minecraft.item.Item;


public class base_item extends Item {

    public base_item(String name, int stackMAX) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(stackMAX);
    }

}
