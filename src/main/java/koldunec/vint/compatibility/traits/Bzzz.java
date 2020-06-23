package koldunec.vint.compatibility.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Bzzz extends AbstractTrait {
    public Bzzz(){
        super("bzzz", TextFormatting.GOLD);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        super.onHit(tool, player, target, damage, isCritical);
    }


    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase victim, float damage, float newDamage, boolean isCritical) {
        if(!(victim instanceof EntityPlayer))
            if(!(victim instanceof EntityVillager))
                return super.damage(tool, player, victim, damage, newDamage, isCritical);
        victim.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20*5));
        return 0;
    }
}
