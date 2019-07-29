package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class kvas extends base_food {
    public kvas(){
        super("kvas",64,2,2,false);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(ItemRegister.NETHER_CRYSTAL,1);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(worldIn.isRemote) return;
        player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,2400,2));
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED,2400,2));
        player.addPotionEffect(new PotionEffect(MobEffects.HASTE,2400,2));
        player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,2400,1));
        super.onFoodEaten(stack, worldIn, player);
    }
}
