package koldunec.vint.utils;

import koldunec.vint.init.ItemRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import java.util.HashMap;

public class DyeHelper {
    public enum DyeOrDyeable{
        NONE, DYE, DYEABLE;

        public static DyeOrDyeable decide(ItemStack istack){
            if(isDyeable(istack)) return  DYEABLE;
            if(isDye(istack)) return DYE;
            return NONE;
        }
    }


    public static ItemStack getDyeByIndex(int i, int amount){
        i%=16;
        switch(i){
            case 0: return new ItemStack(ItemRegister.ANOTHER_DYE, amount,0); //black
            case 2: return new ItemStack(ItemRegister.ANOTHER_DYE, amount,1); //green
            case 3: return new ItemStack(ItemRegister.ANOTHER_DYE, amount,2); //brown
            case 4: return new ItemStack(ItemRegister.ANOTHER_DYE, amount,3); //blue
            case 15: return new ItemStack(ItemRegister.ANOTHER_DYE, amount,4); //white
            default:
                return new ItemStack(Items.DYE, 1,i); //others
        }
    }

    public static ItemStack getDyeByIndex(int i){
        return getDyeByIndex(i,1);
    }


    public static boolean isDyeable(ItemStack i){
        return i.getItem().equals(Item.getItemFromBlock(Blocks.WOOL)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.STAINED_GLASS)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.CARPET)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.CONCRETE_POWDER)) ||
                i.getItem().equals(Item.getItemFromBlock(Blocks.CONCRETE));
    }



    public static String getDyeName(ItemStack istack){
        for(String s: entryToMeta.keySet()) {
            for(ItemStack item: OreDictionary.getOres(s))
                if (item.getItem()==istack.getItem() && item.getItemDamage()==istack.getItemDamage())
                    return s;
        }
        return null;
    }

    public static boolean isDye(ItemStack istack){
        return getDyeName(istack)!=null;
    }


    public static Integer dyeToInt(ItemStack istack){
        String entry = getDyeName(istack);
        if(entry==null)
            return null;
        return entryToMeta.get(entry);
    }

    public static final HashMap<String, Integer> entryToMeta = new HashMap<String,Integer>(){{
        put("dyeBlack", 0);
        put("dyeRed", 1);
        put("dyeGreen", 2);
        put("dyeBrown", 3);
        put("dyeBlue", 4);
        put("dyePurple", 5);
        put("dyeCyan", 6);
        put("dyeLightGray", 7);
        put("dyeGray", 8);
        put("dyePink", 9);
        put("dyeLime", 10);
        put("dyeYellow", 11);
        put("dyeLightBlue", 12);
        put("dyeMagenta", 13);
        put("dyeOrange", 14);
        put("dyeWhite", 15);
    }};
}
