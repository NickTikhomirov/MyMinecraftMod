package koldunec.vint.items;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.tileentities.EntityStore;
import koldunec.vint.utils.EnumTwilightScrollTypes;
import koldunec.vint.utils.VanillaHelper;
import koldunec.vint.vint;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TwilightScroll extends SimpleItems.SimpleItem {
    public static ArrayList<ItemStack> SCROLLS = new ArrayList<>();

    public TwilightScroll(){
        super("twilight_scroll", 64);
        setHasSubtypes(true);
        for(EnumTwilightScrollTypes type : EnumTwilightScrollTypes.values())
            SCROLLS.add(new ItemStack(this, 1, type.ordinal()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + EnumTwilightScrollTypes.getByMeta(i).getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == vint.magicTab)
            items.addAll(SCROLLS);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add("Use this on the stORE block");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!IntegrationHelper.isLoadedTwilight)
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        if(worldIn.getBlockState(pos).getBlock().equals(BlockRegister.STORE)
                && player.isSneaking()
                && !worldIn.isRemote){
            ItemStack stack = player.getHeldItem(hand);
            EntityStore entityStore = (EntityStore) worldIn.getTileEntity(pos);
            if(entityStore==null || !entityStore.isEmpty())
                return EnumActionResult.FAIL;
            VanillaHelper.FillStORE(entityStore, worldIn, EnumTwilightScrollTypes.getByMeta(stack.getMetadata()).getLootTable());
            stack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
