package koldunec.vint.broomitems.crafting_tools;

import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class cleaner extends Item {
    public cleaner(){
        //super(magicFlint, Sets.newHashSet());
        setContainerItem(this);
        setRegistryName("cleaner");
        setUnlocalizedName("cleaner");
        setMaxStackSize(1);
        setCreativeTab(vint.magicTab);
        setMaxDamage(255);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack res = itemStack.copy();
        res.setItemDamage(res.getItemDamage()+1);
        if(res.getItemDamage()>=res.getMaxDamage())
            return ItemStack.EMPTY;
        return res;
    }
}
