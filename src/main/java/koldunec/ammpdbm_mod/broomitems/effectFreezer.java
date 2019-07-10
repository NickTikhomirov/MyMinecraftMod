package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import koldunec.ammpdbm_mod.potions.effectstorage;
import koldunec.ammpdbm_mod.potions.potionstorage;
import koldunec.ammpdbm_mod.init.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class effectFreezer extends base_food {
    public effectFreezer(){
        super("effect_freezer",64,2,1F,false);
        setAlwaysEdible();
    }

    @Override
    public void onFoodEaten(ItemStack i, World w, EntityPlayer p){
        if (!w.isRemote)
        {
            int dur = 120;
            if(p.isPotionActive(PotionRegister.EFFECT_STORAGE)){
                dur+=p.getActivePotionEffect(PotionRegister.EFFECT_STORAGE).getDuration();
            } else {
                if(((potionstorage)PotionRegister.EFFECT_STORAGE).Contents.containsKey(p))
                    ((potionstorage)PotionRegister.EFFECT_STORAGE).Contents.remove(p);
            }
            p.addPotionEffect(new effectstorage(dur));
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack i){
        return 14;
    }
}
