package koldunec.vint.items;

import koldunec.vint.vint;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class another_dye_please_dont_blame_me extends Item {
    public enum dyeTypes{
        BLACK, GREEN, BROWN, BLUE, WHITE;

        public static dyeTypes getByMeta(int meta){
            for (dyeTypes type : values()){
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
        return super.getUnlocalizedName() + "." + dyeTypes.getByMeta(i).getName();
    }

    public another_dye_please_dont_blame_me(){
        this.setRegistryName("dye");
        this.setUnlocalizedName("dye");
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(64);
        this.hasSubtypes=true;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == vint.magicTab){
            for (dyeTypes type : dyeTypes.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }
}
