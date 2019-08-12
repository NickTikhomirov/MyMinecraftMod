package koldunec.ammpdbm_mod.broomitems.gunpowder_reed;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.BlockRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

public class reed_item extends ItemBlockSpecial {
    public reed_item(){
        super(BlockRegister.REED_GUNPOWDER);
        this.setRegistryName("gunreed");
        this.setUnlocalizedName("gunreed");
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setMaxStackSize(64);
        this.hasSubtypes=true;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + block_gunreed.reedTypes.intToType(stack.getMetadata()).getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == ammpdbm_mod.magicTab){
            for (block_gunreed.reedTypes type : block_gunreed.reedTypes.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }


}
