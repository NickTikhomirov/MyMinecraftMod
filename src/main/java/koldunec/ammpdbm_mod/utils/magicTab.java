package koldunec.ammpdbm_mod.utils;

import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class magicTab extends CreativeTabs {
    public magicTab(String label){
        super(label);
    }

    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(ItemRegister.ESSENCE_RAINBOW);
    }
}
