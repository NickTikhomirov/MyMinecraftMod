package koldunec.vint.broomitems.baseItems;


import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class base_fuel extends Item {

    public short duration = 0;

    public base_fuel(String name, int stackMAX, short duration_ticks) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(stackMAX);
        this.duration = duration_ticks;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return duration;
    }

}
