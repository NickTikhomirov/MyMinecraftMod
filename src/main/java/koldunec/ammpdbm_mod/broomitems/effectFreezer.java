package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import koldunec.ammpdbm_mod.potions.potionstorage;
import koldunec.ammpdbm_mod.init.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
            if(p.isPotionActive(PotionRegister.MAGICPROTECTION)){
                dur+=p.getActivePotionEffect(PotionRegister.MAGICPROTECTION).getDuration();
            } else {
                if(((potionstorage)PotionRegister.MAGICPROTECTION).Contents.containsKey(p))
                    ((potionstorage)PotionRegister.MAGICPROTECTION).Contents.remove(p);
            }
            p.addPotionEffect(new PotionEffect(PotionRegister.MAGICPROTECTION,dur));
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack i){
        return 14;
    }
}
