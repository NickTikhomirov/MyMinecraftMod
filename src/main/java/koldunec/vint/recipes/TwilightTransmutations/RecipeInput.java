package koldunec.vint.recipes.TwilightTransmutations;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeInput {
    public Item base;
    public Item catalyst;
    public int basemeta = 0;
    public int catalystmeta = 0;

    public RecipeInput(Item _base, int _basemeta, Item _catalyst, int _catalystmeta){
        base = _base;
        basemeta = _basemeta;
        catalyst = _catalyst;
        catalystmeta = _catalystmeta;
    }

    public RecipeInput(ItemStack b, ItemStack cat){
        this(b.getItem(), b.getItemDamage(), cat.getItem(), cat.getItemDamage());
    }

    public RecipeInput(Block _base, int _basemeta, Item _catalyst, int _catalystmeta){
        this(Item.getItemFromBlock(_base), _basemeta, _catalyst, _catalystmeta);
    }

    public RecipeInput(Item _base, Item _catalyst){
        this(_base, 0, _catalyst, 0);
    }

    public RecipeInput(Block _base, Item _catalyst){
        this(_base, 0, _catalyst, 0);
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
