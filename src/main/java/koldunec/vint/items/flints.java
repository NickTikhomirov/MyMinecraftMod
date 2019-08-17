package koldunec.vint.items;

import koldunec.vint.vint;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class flints extends Item {
    public enum flintTypes{
        WEAK, VOID;

        public static flintTypes getByMeta(int meta){
            for (flints.flintTypes type : values()){
                if (type.ordinal() == meta)
                    return type;
            }
            return null;
        }

        public String getName(){
            return toString().toLowerCase();
        }
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + flintTypes.getByMeta(i).getName();
    }

    public flints(){
        this.setRegistryName("flints");
        this.setUnlocalizedName("magic_flint");
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(1);
        this.hasSubtypes=true;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == vint.magicTab){
            for (flintTypes type : flintTypes.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }
}
