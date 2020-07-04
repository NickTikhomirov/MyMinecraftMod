package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.tileentities.TileTower;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeOutput implements ITwilightResult {
    public ItemStack result;
    public int time;

    public RecipeOutput(ItemStack res, int f){
        result = res;
        time = f;
    }

    public RecipeOutput(Item i, int meta, int f){
        this(new ItemStack(i,1,meta), f);
    }

    public RecipeOutput(Block i, int meta, int f){
        this(new ItemStack(i,1,meta), f);
    }

    public RecipeOutput(Item i, int f){
        this(new ItemStack(i,1), f);
    }

    public RecipeOutput(Block i, int f){
        this(new ItemStack(i,1), f);
    }


    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tower) {
        return result;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tower) {
        return catalyst;
    }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tower) {
        return time;
    }
}
