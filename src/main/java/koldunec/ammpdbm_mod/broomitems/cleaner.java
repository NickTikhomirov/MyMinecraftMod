package koldunec.ammpdbm_mod.broomitems;

import com.google.common.collect.Sets;
import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import static koldunec.ammpdbm_mod.toolmaterials.magicFlint.magicFlint;

public class cleaner extends ItemTool {
    public cleaner(){
        super(magicFlint, Sets.newHashSet());
        setContainerItem(this);
        setRegistryName("cleaner");
        setUnlocalizedName("cleaner");
        setMaxStackSize(1);
        setCreativeTab(ammpdbm_mod.magicTab);
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
