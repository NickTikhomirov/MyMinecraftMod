package koldunec.ammpdbm_mod.broomitems.curinggrass;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_food;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static net.minecraft.init.MobEffects.HUNGER;


public class curinggrass extends base_food {

    public curinggrass() {
        super("curinggrass",64,1,1,false);
    }


    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer player){
        if(player.isPotionActive(Potion.getPotionById(17))) player.removePotionEffect(Potion.getPotionById(17));
        player.heal(1F);
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
            if(playerIn.isPotionActive(HUNGER)){
                playerIn.setActiveHand(handIn);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
            } else return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }
}
