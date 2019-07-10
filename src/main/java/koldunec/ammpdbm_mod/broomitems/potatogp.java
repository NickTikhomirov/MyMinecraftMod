package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;


public class potatogp extends base_food {

    public potatogp() {
        super("potatogp",64,1,1,false);
    }


    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer player){
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 3000, 3));
    }
}
