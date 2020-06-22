package koldunec.vint.items;

import koldunec.vint.objectbuilders.SimpleItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class dg_apple extends SimpleItems.SimpleFood {
     public dg_apple(){
         super("dg_apple",4,4);
         setAlwaysEdible();
     }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
       if(worldIn.isRemote) return;
       player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,3600,1));
       player.extinguish();
       super.onFoodEaten(stack, worldIn, player);
    }
}
