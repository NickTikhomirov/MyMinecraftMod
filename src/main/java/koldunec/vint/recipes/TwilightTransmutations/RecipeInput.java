package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.utils.ItemWithMeta;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeInput {
    public ItemWithMeta base;
    public ItemWithMeta catalyst;

    public RecipeInput(Item _base, int _basemeta, Item _catalyst, int _catalystmeta){
        base = new ItemWithMeta(_base, _basemeta);
        catalyst = new ItemWithMeta(_catalyst, _catalystmeta);
    }

    public RecipeInput(ItemStack b, ItemStack cat){
        this(b.getItem(), b.getItemDamage(), cat.getItem(), cat.getItemDamage());
    }

    public RecipeInput(Block _base, int _basemeta, Item _catalyst, int _catalystmeta){
        this(Item.getItemFromBlock(_base), _basemeta, _catalyst, _catalystmeta);
    }

    public RecipeInput(ItemWithMeta _base, ItemWithMeta _catalyst){
        base = _base;
        catalyst = _catalyst;
    }

    public RecipeInput(Item _base, Item _catalyst){
        this(_base, 0, _catalyst, 0);
    }

    public RecipeInput(Block _base, Item _catalyst){
        this(_base, 0, _catalyst, 0);
    }


    @Override
    public int hashCode() {
        return (getBase().hashCode()^ getCatalyst().hashCode())|((getBasemeta() ^ getCatalystmeta())<<2);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj.getClass()!= RecipeInput.class)
            return false;
        RecipeInput r = (RecipeInput)obj;
        return
                base.equals(r.base) && catalyst.equals(r.catalyst);
    }

    public Item getBase() { return base.item; }
    public void setBase(Item base) { this.base.item = base; }
    public Item getCatalyst() { return catalyst.item; }
    public void setCatalyst(Item catalyst) { this.catalyst.item = catalyst; }
    public int getBasemeta() { return base.meta; }
    public void setBasemeta(int basemeta) { base.meta = basemeta; }
    public int getCatalystmeta() { return catalyst.meta; }
    public void setCatalystmeta(int catalystmeta) { catalyst.meta = catalystmeta; }
}
