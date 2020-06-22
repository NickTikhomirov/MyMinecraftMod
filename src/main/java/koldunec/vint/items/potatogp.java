package koldunec.vint.items;

import koldunec.vint.objectbuilders.SimpleItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;


public class potatogp extends SimpleItems.SimpleFood  {

    public potatogp() {
        super("potatogp",1,1);
    }


    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer player){
        if(player.world.isRemote) return;
        player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 3000, 3));
    }
}
