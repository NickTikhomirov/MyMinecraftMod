package koldunec.vint.items.curinggrass;

import koldunec.vint.items.baseItems.base_food;
import koldunec.vint.potions.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static net.minecraft.init.MobEffects.*;


public class supercuringgrass extends base_food {

    public supercuringgrass() {
        super("supercuringgrass",64,1,1,false);
    }


    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer player){
        if(player.isPotionActive(Potion.getPotionById(17))) player.removePotionEffect(Potion.getPotionById(17));
        player.heal(6F);
        player.addPotionEffect(new PotionEffect(PotionRegister.MINDPROTECTION,40,1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.canEat(false))
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else {
            Boolean pep = false;
            if(playerIn.isPotionActive(HUNGER)) pep = true;
            if(playerIn.isPotionActive(NAUSEA)) pep = true;
            if(playerIn.isPotionActive(BLINDNESS)) pep = true;
            if(playerIn.isPotionActive(SLOWNESS)) pep = true;
            if(playerIn.isPotionActive(MINING_FATIGUE)) pep = true;
            if(playerIn.isPotionActive(WEAKNESS)) pep = true;
            if(playerIn.shouldHeal()) pep = true;
            if(pep){
                playerIn.setActiveHand(handIn);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
            } else return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }
}
