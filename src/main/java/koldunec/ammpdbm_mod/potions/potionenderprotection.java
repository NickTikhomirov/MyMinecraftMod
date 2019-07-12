package koldunec.ammpdbm_mod.potions;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Iterator;

import static net.minecraft.init.MobEffects.LEVITATION;
import static net.minecraft.init.MobEffects.WEAKNESS;

public class potionenderprotection extends potion_base {

    private final ResourceLocation sprite = new ResourceLocation(ammpdbm_mod.MODID, "textures/gui/effectenderprotection.png");

    public potionenderprotection(int liquidColorIn) {
        super("enderprotection",false, liquidColorIn);
    }


    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if(entityLivingBase instanceof EntityEnderman){
            if(ammpdbm_mod.random.nextInt(3)!=0) return;
            entityLivingBase.attackEntityFrom(DamageSource.MAGIC,2+amplifier*0.5F);
            if(amplifier>0) entityLivingBase.addPotionEffect(new PotionEffect(WEAKNESS,amplifier-1,60));
        } else if(entityLivingBase instanceof EntityShulker){
            if(ammpdbm_mod.random.nextInt(3)!=0) return;
            entityLivingBase.attackEntityFrom(DamageSource.MAGIC,2+amplifier);
        } else if(entityLivingBase instanceof EntityEndermite){
            entityLivingBase.attackEntityFrom(DamageSource.MAGIC,500);
        } else if(entityLivingBase instanceof EntitySilverfish){
            entityLivingBase.addPotionEffect(new PotionEffect(LEVITATION,60,5));
        } else
            entityLivingBase.removeActivePotionEffect(MobEffects.LEVITATION);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 10 == 0;
    }


    @Override
    public ResourceLocation getSprite() {
        return sprite;
    }
}
