package koldunec.vint.recipes;

import koldunec.vint.utils.DyeHelper;
import koldunec.vint.init.ItemRegister;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class paint_operator extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    public Item OperatorItem = ItemRegister.PAINT_TRANSMUTATOR;

    public boolean matches(InventoryCrafting inv, World worldIn) {
        if (!canFit(inv.getWidth(),inv.getHeight()))
            return false;
        if(!inv.getStackInRowAndColumn(1,1).getItem().equals(OperatorItem))
            return false;
        if(!(
                isSlotEmpty(inv,1,0)
             && isSlotEmpty(inv,1,2)
             && isSlotEmpty(inv,2,0)
             && isSlotEmpty(inv,2,2))
        ) return false;
        if(!DyeHelper.isDye(inv.getStackInRowAndColumn(2,1)))
            return false;
        ItemStack base = inv.getStackInRowAndColumn(0,1);
        ItemStack top = inv.getStackInRowAndColumn(0,0);
        ItemStack bot = inv.getStackInRowAndColumn(0,2);
        switch(DyeHelper.DyeOrDyeable.decide(base)){
            case NONE: return false;
            case DYEABLE: {
                if(!top.isEmpty() && !top.getItem().equals(base.getItem())) return false;
                if(!bot.isEmpty() && !bot.getItem().equals(base.getItem())) return false;
                break;
            }
            case DYE:{
                if(!top.isEmpty() && !DyeHelper.isDye(top)) return false;
                if(!bot.isEmpty() && !DyeHelper.isDye(bot)) return false;
                break;
            }
        }
        return true;
    }

    private boolean isSlotEmpty(InventoryCrafting inv, int row, int column){
        return inv.getStackInRowAndColumn(row,column).isEmpty();
    }



    private static int getMeta(ItemStack i){
        Integer id = DyeHelper.dyeToInt(i);
        return id==null? i.getItemDamage():id;
    }


    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack base = inv.getStackInRowAndColumn(0,1);
        boolean isDyeMode = DyeHelper.isDye(base);
        int amount = 1;
        int result_meta = getMeta(base);
        result_meta += getMeta(inv.getStackInRowAndColumn(2,1));
        for(int i: new int[]{0,2}) {
            if (inv.getStackInRowAndColumn(0, i).isEmpty())
                continue;
            ++amount;
            result_meta += getMeta(inv.getStackInRowAndColumn(0, i));
        }
        result_meta = result_meta%16;
        if(isDyeMode)
            return DyeHelper.getDyeByIndex(result_meta, amount);
        return new ItemStack(base.getItem(), amount, result_meta);
    }

    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }


    public boolean isDynamic(){
        return true;
    }


    public boolean canFit(int width, int height)
    {
        return width >= 2 && height >= 2;
    }
}
