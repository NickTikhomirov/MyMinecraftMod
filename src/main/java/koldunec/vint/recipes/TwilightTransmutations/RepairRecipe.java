package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

public class RepairRecipe implements ITwilightResult {
    public ItemStack result = new ItemStack(Blocks.SAND);
    public HashSet<Item> repairable = new HashSet<>();
    public HashMap<Item,Integer> materials = new HashMap<>();
    public int step;

    public RepairRecipe(Item tool, ItemStack material){
        this(tool, material,1);
    }

     public RepairRecipe(Item tool, ItemStack material, int amount){
        repairable.add(tool);
        materials.put(material.getItem(),material.getMetadata());
        step = amount;
    }

    public void put(Item tool, ItemStack material){
        repairable.add(tool);
        materials.put(material.getItem(),material.getMetadata());
    }

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        Integer i = materials.get(base.getItem());
        if(i==null)
            return false;
        int ii = i;
        return repairable.contains(catalyst.getItem()) && base.getMetadata()==ii;
    }

    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        return result;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) {
        ItemStack copy = catalyst.copy();
        copy.setItemDamage(catalyst.getItemDamage()-step);
        return copy;
    }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile) {
        return 50;
    }
}
