package koldunec.vint.objectbuilders;

import koldunec.vint.vint;
import net.minecraft.item.ItemFood;

public class SimpleItems {
    public static class SimpleFood extends ItemFood{

        public SimpleFood(String name, int amount, float saturation) {
            super(amount, saturation, false);
            setRegistryName(name);
            setUnlocalizedName(name);
            setCreativeTab(vint.magicTab);
        }
    }
}
