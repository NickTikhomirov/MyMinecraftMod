package koldunec.vint.recipes.TwilightTransmutations;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeInput {
    public Item base;
    public Item catalyst;
    public int basemeta = 0;
    public int catalystmeta = 0;
    public RecipeInput(ItemStack b, ItemStack cat){
        base = b.getItem();
        basemeta = b.getItemDamage();
        catalyst = cat.getItem();
        catalystmeta = cat.getItemDamage();
    }

    @Override
    public int hashCode() {
        if(catalyst==null)
            return base.hashCode()^basemeta;
        else
            return (base.hashCode()^catalyst.hashCode())|((basemeta^catalystmeta)<<2);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj.getClass()!= RecipeInput.class)
            return false;
        RecipeInput r = (RecipeInput)obj;
        return
                base.equals(r.base)
             && catalyst.equals(r.catalyst)
             && catalystmeta==r.catalystmeta
             && basemeta==r.basemeta;
    }
}
