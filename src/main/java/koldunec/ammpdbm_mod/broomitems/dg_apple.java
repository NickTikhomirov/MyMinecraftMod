package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class dg_apple extends base_food {
     public dg_apple(){
         super("dg_apple",64,4,4,false);
         setAlwaysEdible();
     }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(worldIn.isRemote) return;
       player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,1800,1));
       player.extinguish();
       super.onFoodEaten(stack, worldIn, player);
    }
}
