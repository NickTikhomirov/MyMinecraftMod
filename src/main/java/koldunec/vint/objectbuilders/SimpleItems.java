package koldunec.vint.objectbuilders;

import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class SimpleItems {
    public static class SimpleItem extends Item {
        public SimpleItem(String name, int amount){
            super();
            setRegistryName(name);
            setUnlocalizedName(name);
            setMaxStackSize(amount);
            setCreativeTab(vint.magicTab);
        }
    }


    public static class SimpleFood extends ItemFood{

        public SimpleFood(String name, int amount, float saturation) {
            super(amount, saturation, false);
            setRegistryName(name);
            setUnlocalizedName(name);
            setCreativeTab(vint.magicTab);
        }
    }
}
