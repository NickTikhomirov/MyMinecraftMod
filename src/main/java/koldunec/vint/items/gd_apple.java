package koldunec.vint.items;

import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.potions.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class gd_apple extends SimpleItems.SimpleFood  {
     public gd_apple(){
         super("gd_apple",4,4);
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
