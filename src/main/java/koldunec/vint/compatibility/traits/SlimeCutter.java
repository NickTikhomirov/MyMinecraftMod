package koldunec.vint.compatibility.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;


public class SlimeCutter extends AbstractTrait {

    public SlimeCutter() {
        super("slimecutter",TextFormatting.GREEN);
    }


    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(target instanceof EntitySlime)
            newDamage+=3F;
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }

}
