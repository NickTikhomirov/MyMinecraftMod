package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import net.minecraft.item.ItemStack;

public class paint_operator extends base_item {
    public paint_operator(){
        super("paint_operator",1);
        this.setContainerItem(this);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(this,1);
    }
}
