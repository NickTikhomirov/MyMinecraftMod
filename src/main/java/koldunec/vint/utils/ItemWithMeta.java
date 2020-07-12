package koldunec.vint.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemWithMeta {
    public Item item;
    public int meta;

    public ItemWithMeta(Item i, int _meta){
        item = i;
        meta = _meta;
    }

    public ItemWithMeta(Item i){ this(i, 0);}

    public ItemWithMeta(Block b){ this(Item.getItemFromBlock(b));}

    public ItemWithMeta(ItemStack i){ this(i.getItem(), i.getMetadata());}

    public ItemStack BuildStack(){
        return new ItemStack(item, 1 , meta);
    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        if(obj.getClass()!=this.getClass())
            return false;
        ItemWithMeta cast = (ItemWithMeta) obj;
        return item==cast.item && meta==cast.meta;
    }

    public boolean equalsStack(ItemStack i){
        if(i==null)
            return false;
        return item.equals(i.getItem()) && meta==i.getMetadata();
    }

    public ItemWithMeta copy(){
        return new ItemWithMeta(item, meta);
    }
}
