package koldunec.vint.utils;

import koldunec.vint.vint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.utils.TagUtil;
import toughasnails.api.TANPotions;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.temperature.TemperatureHandler;
import toughasnails.thirst.ThirstHandler;

public class NailHelper {
    public static int CONST_COST_TEMP = 0;
    public static int CONST_TEMPERATURE = 14;
    public static int CONST_TIME_TEMP = 100;
    public static int CONST_PROB_REMOVE_COLD = 20;
    public static int CONST_PROB_REMOVE_HOT = 20;
    public static int CONST_PROB_BLOOD_HYDRATE = 4;

    public static String ID = "TemperatureTime";

    //todo extend system to be able to handle many temperature timers

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

    public static void hydrateWithBloodWithChance(EntityPlayer player){
        ThirstHandler handler = (ThirstHandler) ThirstHelper.getThirstData(player);
        if(vint.random.nextInt(CONST_PROB_BLOOD_HYDRATE)!=0)
            return;
        if(handler.isThirsty()){
            handler.addStats(2, 2F);
            handler.setExhaustion(0);
        }
    }

    public static void regulateTemperature(EntityPlayer player, ItemStack tool, boolean cool, boolean warm){
        TemperatureHandler handler = (TemperatureHandler) TemperatureHelper.getTemperatureData(player);
        int time = NailHelper.getTime(tool);
        Temperature temperature = handler.getTemperature();
        int tempint = temperature.getRawValue();
        if(cool && tempint > CONST_TEMPERATURE){
            if(time>0){
                NailHelper.setTime(tool, time - 1);
                return;
            }
            if(vint.random.nextInt(CONST_PROB_REMOVE_COLD)==0)
                player.removePotionEffect(TANPotions.hypothermia);
            handler.setTemperature(new Temperature(tempint + 1));
            NailHelper.setTime(tool, CONST_TIME_TEMP);
        } else if(warm && tempint < CONST_TEMPERATURE){
            if(time>0){
                NailHelper.setTime(tool, time - 1);
                return;
            }
            if(vint.random.nextInt(CONST_PROB_REMOVE_HOT)==0)
                player.removePotionEffect(TANPotions.hyperthermia);
            handler.setTemperature(new Temperature(tempint - 1));
            NailHelper.setTime(tool, CONST_TIME_TEMP);
        }
    }
}
