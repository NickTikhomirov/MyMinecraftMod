package koldunec.vint.items.agriculture;

import koldunec.vint.items.baseItems.base_item;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SoulFruit extends base_item {
    public SoulFruit(){
        super("soul_fruit",64);
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.EAT;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.isPotionActive(MobEffects.HEALTH_BOOST)) {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);

    }


    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            PotionEffect old = entityplayer.getActivePotionEffect(MobEffects.HEALTH_BOOST);
            if(old==null)
                return stack;
            entityplayer.addPotionEffect(
                    new PotionEffect(
                            MobEffects.HEALTH_BOOST,
                            old.getDuration()+2400,
                            old.getAmplifier()));
            if (entityplayer instanceof EntityPlayerMP)
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
        }
        stack.shrink(1);
        return stack;
    }
}
