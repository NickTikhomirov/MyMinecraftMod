package koldunec.vint.broomitems;

import koldunec.vint.broomitems.baseItems.base_food;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

import static net.minecraft.init.MobEffects.*;

public class effectFreezer extends base_food {
    public effectFreezer(){
        super("effect_freezer",64,2,1F,false);
        setAlwaysEdible();
    }

    @Override
    public void onFoodEaten(ItemStack i, World w, EntityPlayer p){
        if(w.isRemote) return;
        boolean b_luck = p.isPotionActive(MobEffects.LUCK);
        boolean b_unluck = p.isPotionActive(MobEffects.UNLUCK);
        if(b_luck == b_unluck){
            p.addPotionEffect(generate(w.rand));
        } else if(b_luck){
            p.addPotionEffect(generateGood(w.rand));
        } else {
            p.addPotionEffect(generateBad(w.rand));
        }
    }

    private static PotionEffect generate(Random r){
        if(r.nextBoolean()) return generateGood(r);
        return generateBad(r);
    }

    private static PotionEffect generateGood(Random r){
        switch(r.nextInt(5)){
            case 0: return new PotionEffect(SPEED,1200,1);
            case 1: return new PotionEffect(HASTE,1200,2);
            case 2: return new PotionEffect(NIGHT_VISION,120000);
            case 3: return new PotionEffect(RESISTANCE,1200,1);
            case 4: return new PotionEffect(HEALTH_BOOST,1200,1);
        }
        return new PotionEffect(GLOWING,1200);
    }

    private static PotionEffect generateBad(Random r){
        switch(r.nextInt(5)){
            case 0: return new PotionEffect(BLINDNESS,2400);
            case 1: return new PotionEffect(WITHER,300,1);
            case 2: return new PotionEffect(SLOWNESS,900,3);
            case 3: return new PotionEffect(JUMP_BOOST,600,119);
            case 4: return new PotionEffect(NAUSEA,900);
        }
        return new PotionEffect(GLOWING,1200);
    }


    @Override
    public int getMaxItemUseDuration(ItemStack i){
        return 14;
    }
}
