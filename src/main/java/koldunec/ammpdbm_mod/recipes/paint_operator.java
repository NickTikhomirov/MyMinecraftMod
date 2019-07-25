package koldunec.ammpdbm_mod.recipes;

import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.init.PotionRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class paint_operator extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        if (inv.getWidth() == 3 && inv.getHeight() == 3) {
            if(!inv.getStackInRowAndColumn(1,1).getItem().equals(ItemRegister.PAINT_OPERATOR))
                return false;
            if(!inv.getStackInRowAndColumn(1,0).isEmpty() ||
                    !inv.getStackInRowAndColumn(1,2).isEmpty())
                return false;
            if(!inv.getStackInRowAndColumn(2,0).isEmpty() ||
                !inv.getStackInRowAndColumn(2,2).isEmpty())
                return false;
            if(!isPaint(inv.getStackInRowAndColumn(2,1))) return false;
            ItemStack base = inv.getStackInRowAndColumn(0,1);
            if(!isBase(base)) return false;
            ItemStack i = inv.getStackInRowAndColumn(0,0);
            if(!i.isEmpty() && ! i.getItem().equals(base.getItem())) return false;
            i = inv.getStackInRowAndColumn(0,2);
            if(!i.isEmpty() && ! i.getItem().equals(base.getItem())) return false;
            return true;
        }
        else
        {
            return false;
        }
    }



    private static boolean isPaint(ItemStack i){
        return i.getItem().equals(Items.DYE) || i.getItem().equals(ItemRegister.ANOTHER_DYE);
    }

    private static boolean isBase(ItemStack i){
        return i.getItem().equals(Item.getItemFromBlock(Blocks.WOOL)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.STAINED_GLASS)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE)) ||
                i.getItem().equals(Items.BED) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.CARPET)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.CONCRETE_POWDER)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.CONCRETE));
    }

    private static int getMeta(ItemStack i){
        if(i.getItem().equals(ItemRegister.ANOTHER_DYE)){
            int o = i.getItemDamage();
            if(o==4) return 15;
            if(o==3) return 4;
            if(o==2) return 3;
            if(o==1) return 2;
            return 0;
        }
        return i.getItemDamage();
    }




    public ItemStack getCraftingResult(InventoryCrafting inv) {
        int amount = 1;
        int result_meta = getMeta(inv.getStackInRowAndColumn(0,1));
        result_meta += getMeta(inv.getStackInRowAndColumn(2,1));
        if(!inv.getStackInRowAndColumn(0,0).isEmpty()) {
            amount++;
            result_meta += getMeta(inv.getStackInRowAndColumn(0,0));
        }
        if(!inv.getStackInRowAndColumn(0,2).isEmpty()) {
            amount++;
            result_meta += getMeta(inv.getStackInRowAndColumn(0,2));
        }
        result_meta = result_meta%16;
        return new ItemStack(inv.getStackInRowAndColumn(0,1).getItem(), amount,result_meta);
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
