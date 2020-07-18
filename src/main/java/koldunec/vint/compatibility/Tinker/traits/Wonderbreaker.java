package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.compatibility.Tinker.ColorConstants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Wonderbreaker extends AbstractTrait {
    public Wonderbreaker() {
        super("wonderbreaker", ColorConstants.SPECTRE_COLOR);
    }

    @Override
    public int onToolHeal(ItemStack tool, int amount, int newAmount, EntityLivingBase entity) {
        if(entity!=null)
            return 0;
        return newAmount;
    }
}
