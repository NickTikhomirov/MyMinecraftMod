package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.compatibility.Tinker.ColorConstants;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Merciful extends AbstractTrait {
    public Merciful() {
        super("merciful", ColorConstants.GREEN_LOG_COLOR);
    }

    public boolean check(EntityLivingBase e){
        if(e instanceof EntityAgeable)
            if(e.isChild())
                return true;
        return false;
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if(!check(target))
            return;
        target.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 10, 5));
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(!check(target))
            return;
        target.clearActivePotions();
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(check(target))
            return 0;
        return newDamage;
    }
}
