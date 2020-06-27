package koldunec.vint.compatibility.Tinker.traits.tas_tic;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.utils.ThirstHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import toughasnails.api.TANPotions;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.temperature.TemperatureHandler;

public class Cool extends AbstractTrait {

    public Cool() {
        super("cool", TextFormatting.AQUA);
    }


    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if(!isSelected)
            return;
        if(!IntegrationHelper.isLoadedTough)
            return;
        if(world.isRemote)
            return;
        if(!(entity instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) entity;
        TemperatureHandler handler = (TemperatureHandler) TemperatureHelper.getTemperatureData(player);
        int time = ThirstHelper.getTime(tool);
        Temperature temperature = handler.getTemperature();
        int tempint = temperature.getRawValue();
        if(tempint> ThirstHelper.CONST_TEMPERATURE){
            if(time>0){
                ThirstHelper.setTime(tool, time - 1);
                return;
            }
            if(world.rand.nextInt(ThirstHelper.CONST_PROB_REMOVE_COLD)==0)
                player.removePotionEffect(TANPotions.hyperthermia);
            handler.setTemperature(new Temperature(tempint-1));
            ThirstHelper.setTime(tool,ThirstHelper.CONST_TIME_TEMP);
        } else {
            ThirstHelper.setTime(tool,ThirstHelper.CONST_TIME_TEMP);
        }
    }
}
