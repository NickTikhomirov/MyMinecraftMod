package koldunec.vint.items;

import koldunec.vint.init.ItemRegister;
import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.vint;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

import javax.annotation.Nullable;
import java.util.List;


public class shell extends base_item {

    public shell() {
        super("shell",64);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        if(!player.getHeldItem(handIn).getItem().equals(ItemRegister.SHELL))
            new ActionResult<>(EnumActionResult.FAIL,player.getHeldItem(handIn));
        if(!vint.isLoadedTwilight)
            new ActionResult<>(EnumActionResult.FAIL,player.getHeldItem(handIn));
        player.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.PASS,player.getHeldItem(handIn));
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
       return 60;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(worldIn.isRemote) return super.onItemUseFinish(stack, worldIn, entityLiving);
        if(entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entityLiving;
            ItemStack head = player.inventory.armorInventory.get(3);
            if(head.getItem().equals(TFItems.trophy) && head.getMetadata()==6){
                stack.shrink(1);
                ItemStack a = new ItemStack(Items.BOWL,1);
                ItemStack b = new ItemStack(TFItems.meef_stroganoff,1);
                if(!player.inventory.addItemStackToInventory(a))
                    player.dropItem(a,false);
                if(!player.inventory.addItemStackToInventory(b))
                    player.dropItem(b,false);
                return stack;
            }
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.DARK_RED+I18n.format("vint:flavor5"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    //@Override
    //public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        //return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    //}
}
