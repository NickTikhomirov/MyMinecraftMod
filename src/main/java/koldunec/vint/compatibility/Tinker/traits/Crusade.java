package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.compatibility.Tinker.ColorConstants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Crusade extends AbstractTrait {
    public Crusade() {
        super("crusade", ColorConstants.HONEY_COLOR);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        int data = target.getEntityWorld().provider.getDimension();
        if(data<-1 || data>1)
            newDamage+=4;
        return newDamage;
    }
}
