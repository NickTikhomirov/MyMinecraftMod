package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.init.ItemRegister;
import koldunec.vint.tileentities.TileTower;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Repairing recipe that targets only one tool, that takes catalyst's place
 * The resulting item is trash (default: dust)
 */

public class RepairRecipe implements ITwilightResult {
    public ItemStack result = new ItemStack(ItemRegister.DUST);
    public Item repairable;
    public HashMap<Item,Integer> materials = new HashMap<>();
    public int step;

    public RepairRecipe(Item tool, ItemStack material){
        this(tool, material,1);
    }

     public RepairRecipe(Item tool, ItemStack material, int amount){
        repairable = tool;
        materials.put(material.getItem(),material.getMetadata());
        step = amount;
    }

    public RepairRecipe(Item tool, ItemStack material, ItemStack trash){
        this(tool, material,1 ,trash);
    }

    public RepairRecipe(Item tool, ItemStack material, int amount, ItemStack trash){
        repairable = tool;
        materials.put(material.getItem(),material.getMetadata());
        step = amount;
        result = trash;
    }

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        Integer i = materials.get(base.getItem());
        if(i==null)
            return false;
        int ii = i;
        return repairable.equals(catalyst.getItem()) && catalyst.getItemDamage()>0 && base.getMetadata()==ii;
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
