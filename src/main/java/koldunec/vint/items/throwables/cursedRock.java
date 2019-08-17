package koldunec.vint.items.throwables;

import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.entities.entityMagicBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class cursedRock extends base_item {

    public cursedRock(String name, int stackMax){
        super(name,stackMax);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        player.getCooldownTracker().setCooldown(this, 10);

        if (!world.isRemote) {
            entityMagicBall b = new entityMagicBall(world, player);
            b.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(b);
        }

        //player.addStat(StatList.getObjectUseStats(this));
        //Возвращаем успешное действие, то-есть при ПКМ рука дёрнется как будто игрок кинул предмет
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
