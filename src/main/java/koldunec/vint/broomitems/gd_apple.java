package koldunec.vint.broomitems;

import koldunec.vint.broomitems.baseItems.base_food;
import koldunec.vint.init.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class gd_apple extends base_food {
     public gd_apple(){
         super("gd_apple",64,4,4,false);
         setAlwaysEdible();
     }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
       if(worldIn.isRemote) return;
       //TextComponentTranslation a = new TextComponentTranslation("vint.chat.msg0");
       //a.getStyle().setColor(TextFormatting.RED);
       //player.sendMessage(a);
       player.addPotionEffect(new PotionEffect(PotionRegister.HUMANITY,2400,1));
       player.addPotionEffect(new PotionEffect(PotionRegister.WITHERPROTECTION,2400,0));
       player.addPotionEffect(new PotionEffect(PotionRegister.POISONPROTECTION,2400,0));
       player.extinguish();
       super.onFoodEaten(stack, worldIn, player);
    }
}
