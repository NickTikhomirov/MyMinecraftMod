package koldunec.vint.potions;


import koldunec.vint.vint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.Iterator;

public class potionmindprotection extends potion_base {

    private final ResourceLocation sprite = new ResourceLocation(vint.MODID, "textures/gui/effectmindprotection.png");

    public potionmindprotection(int liquidColorIn) {
        super("mindprotection",false, liquidColorIn);
    }


    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if(entityLivingBase.world.isRemote) return;
        Iterator<PotionEffect> ite = entityLivingBase.getActivePotionEffects().iterator();
        while(ite.hasNext()){
            PotionEffect g = ite.next();
            Boolean k = false;
            if(g.getPotion() == Potion.getPotionById(9)) k = true;  //nausea
            if(g.getPotion() == Potion.getPotionById(15)) k = true; //blindness
            if(amplifier>0){
                if(g.getPotion() == Potion.getPotionById(2)) k = true;  //slowness
                if(g.getPotion() == Potion.getPotionById(4)) k = true;  //fatigue
                if(g.getPotion() == Potion.getPotionById(18)) k = true; //weakness
            }
            if(k) entityLivingBase.removePotionEffect(g.getPotion());
        }
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
