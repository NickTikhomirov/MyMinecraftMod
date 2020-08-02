package koldunec.vint.utils;

import koldunec.vint.init.ItemRegister;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class BehaviourDispenseFood extends BehaviorDefaultDispenseItem {
    private static int getFoodAmount(ItemStack i){
        return ((ItemFood) i.getItem()).getHealAmount(i);
    }

    private static ItemStack getFishStack(ItemStack i){
        int amount = i.getCount()* getFoodAmount(i);
        if(amount==0)
            return ItemStack.EMPTY;
        return new ItemStack(ItemRegister.FISHY, amount);
    }

    @Override
    protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        if(!(stack.getItem() instanceof ItemFood))
            return super.dispenseStack(source, stack);
        EnumFacing enumfacing = source.getBlockState().getValue(BlockDispenser.FACING);
        IPosition iposition = BlockDispenser.getDispensePosition(source);
        ItemStack temp = stack.splitStack(1);
        if(temp.getItem().hasContainerItem(temp))
            doDispense(source.getWorld(), temp.getItem().getContainerItem(temp), 6, enumfacing, iposition);
        doDispense(source.getWorld(), getFishStack(temp), 6, enumfacing, iposition);
        return stack;
    }
}
