package koldunec.vint.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.utils.TagUtil;

public class ThirstHelper {
    public static int CONST_TEMPERATURE = 14;
    public static int CONST_TIME_TEMP = 100;
    public static int CONST_COST_TEMP = 0;
    public static int CONST_PROB_REMOVE_COLD = 20;


    public static String ID = "TemperatureTime";

    public static void check(ItemStack stack) {
        NBTTagCompound tag = TagUtil.getTagSafe(stack);
        if (!tag.hasKey(ID))
            tag.setInteger(ID, CONST_TIME_TEMP);
    }


    public static int getTime(ItemStack stack) {
        check(stack);
        return TagUtil.getTagSafe(stack).getInteger(ID);
    }

    public static void setTime(ItemStack stack, int amount) {
        check(stack);
        TagUtil.getTagSafe(stack).setInteger(ID, amount);
    }

}
