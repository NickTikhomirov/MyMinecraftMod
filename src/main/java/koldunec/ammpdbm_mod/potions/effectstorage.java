package koldunec.ammpdbm_mod.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class effectstorage extends PotionEffect {
    public  static final Potion
            EFFECT_S = new potionstorage("effectstorage",false,0xFFDF84);

    public final Stack<PotionEffect>
            Contents = new Stack<>();

    public effectstorage(int dur){
        super(EFFECT_S,dur,0,false,true);
    }

    @Override
    public void performEffect(EntityLivingBase e){
        //e.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,1200,10));
        Collection<PotionEffect> o = e.getActivePotionEffects();
        for(PotionEffect a:o){
            if(a.equals(this));
            else {
                Contents.push(a);
                //o.remove(a);
                e.removeActivePotionEffect(a.getPotion());
            }
        }
        if(getDuration()<12){
            o.remove(this);
            for(PotionEffect a:Contents){
                e.addPotionEffect(a);
            }
            Contents.clear();
        }
        /*
        if(e instanceof EntityPlayer) {
            Collection<PotionEffect> p = e.getActivePotionEffects();
            if(Contents.containsKey(e)){
                EntityWolf Doggy = Contents.get(e);
                for(PotionEffect a: p){
                    if(a.getPotion().equals(this)) timeToReverse = a.getDuration()<12;
                    else {
                        Doggy.addPotionEffect(a);
                        e.removeActivePotionEffect(a.getPotion());
                    }
                }
            } else {
                EntityWolf Doggy = new EntityWolf(e.world);
                for(PotionEffect a: p){
                    if(a.getPotion().equals(this)) timeToReverse = a.getDuration()<12;
                    else {
                        Doggy.addPotionEffect(a);
                        e.removeActivePotionEffect(a.getPotion());
                    }
                    Contents.put(e,Doggy);
                }
            }
            if(timeToReverse) {
                EntityWolf Doggy = Contents.get(e);
                p = Doggy.getActivePotionEffects();
                for(PotionEffect a: p){
                    e.addPotionEffect(a);
                }
                //e.removeActivePotionEffect(this);
                Doggy.world.removeEntity(Doggy);
                Doggy.setDead();
                Contents.remove(e);

            }
        }
        */
    }
}
