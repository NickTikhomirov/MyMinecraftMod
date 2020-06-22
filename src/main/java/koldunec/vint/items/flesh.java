package koldunec.vint.items;

import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.potions.PotionRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;


public class flesh extends SimpleItems.SimpleFood  {
    public flesh(){
        super("flesh",4,0.8F);
    }

    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer player){
        if(player.world.isRemote) return;
        if(player.isPotionActive(PotionRegister.HUMANITY))
            player.removePotionEffect(PotionRegister.HUMANITY);
        if(w.provider.getDimension()==-1) {
            player.extinguish();
            player.getFoodStats().addStats(3,0.7F);
        }
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
            if(playerIn.isBurning() && worldIn.provider.getDimension()==-1) pep = true;
            if(pep){
                playerIn.setActiveHand(handIn);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
            } else return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return super.getMaxItemUseDuration(stack);
    }
}
