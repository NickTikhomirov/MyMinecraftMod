package koldunec.vint.compatibility.Tinker.traits.tas_tic;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.compatibility.OtherIntegration.NailHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import slimeknights.tconstruct.library.utils.ToolHelper;

public class Warm extends AbstractTrait {

    public Warm() {
        super("warm", TextFormatting.DARK_RED);
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
        if(ToolHelper.getTraits(tool).contains(TinkerIntegration.COOL))
            return;  // if tool is multitemperatural, warm's logic is handled by cool
        NailHelper.regulateTemperature((EntityPlayer) entity, tool, false, true);
    }
}
