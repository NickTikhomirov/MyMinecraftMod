package koldunec.ammpdbm_mod.tileentities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class TowerFurnaceRecipes {

    private static final TowerFurnaceRecipes INSTANCE = new TowerFurnaceRecipes();
    private final Table<ItemStack,ItemStack,ItemStack> GhastingList = HashBasedTable.<ItemStack,ItemStack,ItemStack>create();

    public static TowerFurnaceRecipes getInstance(){
        return INSTANCE;
    }

    private TowerFurnaceRecipes(){
    }

    public void addGhastingRecipe(ItemStack input1, ItemStack input2, ItemStack result)
    {
        if(getGhastingResult(input1, input2) != ItemStack.EMPTY) return;
        this.GhastingList.put(input1, input2, result);
    }

    public ItemStack getGhastingResult(ItemStack input1, ItemStack input2)
    {
        for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.GhastingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        if(input2.getCount()>=ent.getKey().getCount()) return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public short getGhastingFuel(ItemStack input1, ItemStack input2)
    {
        for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.GhastingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        return (short) ent.getKey().getCount();
                    }
                }
            }
        }
        return 0;
    }

}
