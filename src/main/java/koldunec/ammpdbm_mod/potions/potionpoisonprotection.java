package koldunec.ammpdbm_mod.potions;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.Iterator;

import static net.minecraft.init.MobEffects.*;

public class potionpoisonprotection extends potion_base {

    private final ResourceLocation sprite = new ResourceLocation(ammpdbm_mod.MODID, "textures/gui/effectpoisonprotection.png");

    public potionpoisonprotection(int liquidColorIn) {
        super("poisonprotection",false, liquidColorIn);
    }


    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if(amplifier==0) {
            entityLivingBase.removeActivePotionEffect(NAUSEA);
            entityLivingBase.removeActivePotionEffect(POISON);
            entityLivingBase.removeActivePotionEffect(HUNGER);
        } else {
            effect_transform(entityLivingBase,NAUSEA);
            effect_transform(entityLivingBase,POISON);
            effect_transform(entityLivingBase,HUNGER);
        }
    }

    private void effect_transform(EntityLivingBase e, Potion p){
        if(!e.isPotionActive(p)) return;
        if(p.equals(NAUSEA)){
            PotionEffect enemy = e.getActivePotionEffect(NAUSEA);
            if(!e.getEntityWorld().isRemote)
                e.addPotionEffect(new PotionEffect(WATER_BREATHING,enemy.getDuration()));
            e.removeActivePotionEffect(NAUSEA);
        }
        if(p.equals(POISON)){
            PotionEffect enemy = e.getActivePotionEffect(POISON);
            if(!e.getEntityWorld().isRemote)
                e.addPotionEffect(new PotionEffect(REGENERATION,enemy.getDuration(), enemy.getAmplifier()));
            e.removeActivePotionEffect(POISON);
        }
        if(p.equals(HUNGER)){
            PotionEffect enemy = e.getActivePotionEffect(HUNGER);
            if(!e.getEntityWorld().isRemote)
                e.addPotionEffect(new PotionEffect(SATURATION,enemy.getDuration(), enemy.getAmplifier()));
            e.removeActivePotionEffect(HUNGER);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public ResourceLocation getSprite() {
        return sprite;
    }
}
