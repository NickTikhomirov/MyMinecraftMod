package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import koldunec.ammpdbm_mod.init.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class gd_apple extends base_food {
     public gd_apple(){
         super("gd_apple",64,4,4,false);
         setAlwaysEdible();
     }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
       if(worldIn.isRemote) return;
       //TextComponentTranslation a = new TextComponentTranslation("ammpdbm_mod.chat.msg0");
       //a.getStyle().setColor(TextFormatting.RED);
       //player.sendMessage(a);
       player.addPotionEffect(new PotionEffect(PotionRegister.HUMANITY,2400,1));
       player.addPotionEffect(new PotionEffect(PotionRegister.WITHERPROTECTION,2400,0));
       player.addPotionEffect(new PotionEffect(PotionRegister.POISONPROTECTION,2400,0));
       player.extinguish();
       super.onFoodEaten(stack, worldIn, player);
    }
}
