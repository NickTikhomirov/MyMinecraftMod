package koldunec.ammpdbm_mod.broomitems.throwables;

import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import koldunec.ammpdbm_mod.entities.entityStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class inertstone extends base_item {

    public inertstone(String name, int stackMax){
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
            entityStone b = new entityStone(world, player);
            b.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(b);
        }

        //player.addStat(StatList.getObjectUseStats(this));
        //Возвращаем успешное действие, то-есть при ПКМ рука дёрнется как будто игрок кинул предмет
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}