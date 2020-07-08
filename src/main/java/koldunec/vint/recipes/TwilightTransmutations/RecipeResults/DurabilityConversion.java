package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.item.TFItems;

public class DurabilityConversion implements ITwilightResult {

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        if(!base.getItem().equals(TFItems.ore_magnet))
            return false;
        Item icata = catalyst.getItem();
        return catalyst.getItemDamage()>0 && (icata.equals(TFItems.phantom_helmet) || icata.equals(TFItems.phantom_chestplate));
    }

    @Override
    public ItemStack getBaseStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        if(base.getItemDamage()==base.getMaxDamage())
            return ItemStack.EMPTY;
        ItemStack copy = base.copy();
        copy.setItemDamage(copy.getItemDamage()+1);
        return copy;
    }

    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        return new ItemStack(TFItems.armor_shard);
    }

    @Override
    public boolean emptyOutputChance(ItemStack base, ItemStack catalyst, TileTower tile) {
        return base.getItemDamage()!=base.getMaxDamage();
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) {
        if(base.getMaxDamage()==0)
            return catalyst;
        Item icata = catalyst.getItem();
        ItemStack copy = catalyst.copy();
        int restore = 0;
        if(icata.equals(TFItems.phantom_helmet))
            restore = copy.getMaxDamage()/(2*base.getMaxDamage());
        else if(icata.equals(TFItems.phantom_chestplate))
            restore = copy.getMaxDamage()/(3*base.getMaxDamage());
        copy.setItemDamage(copy.getItemDamage() - restore);
        return copy;
    }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile) {
        return 50;
    }
}
