package koldunec.vint.utils;

import koldunec.vint.init.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class magicTab extends CreativeTabs {
    public magicTab(String label){
        super(label);
    }

    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(ItemRegister.PAINT_TRANSMUTATOR);
    }
}
