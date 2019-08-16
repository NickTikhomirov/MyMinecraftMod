package koldunec.vint.broomitems.crafting_tools;

import koldunec.vint.broomitems.baseItems.base_item;
import net.minecraft.item.ItemStack;

public class cheasel extends base_item {
    public cheasel(){
        super("cheasel",1);
        //setMaxDamage(1024);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack res = itemStack.copy();
        //res.setItemDamage(res.getItemDamage()+1);
        //if(res.getItemDamage()>=res.getMaxDamage())
        //    return ItemStack.EMPTY;
        return res;
    }
}
