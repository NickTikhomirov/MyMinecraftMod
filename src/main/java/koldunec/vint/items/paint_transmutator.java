package koldunec.vint.items;

import koldunec.vint.items.baseItems.base_item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class paint_transmutator extends base_item {
    public paint_transmutator(){
        super("p_trans",1);
        setMaxDamage(255);
        //setContainerItem(this);
        setNoRepair();
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)target;
            EnumDyeColor enumdyecolor = entitysheep.getFleeceColor();
            int meta = enumdyecolor.getMetadata();
            meta++;
            enumdyecolor = EnumDyeColor.byMetadata(meta);

            if (!entitysheep.getSheared())
            {
                entitysheep.setFleeceColor(enumdyecolor);
                stack.damageItem(1,playerIn);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack res = itemStack.copy();
        res.setItemDamage(res.getItemDamage()+1);
        if(res.getItemDamage()>=res.getMaxDamage())
            return ItemStack.EMPTY;
        return res;
    }
}
