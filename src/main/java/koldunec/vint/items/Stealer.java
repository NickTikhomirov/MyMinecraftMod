package koldunec.vint.items;

import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.utils.ParticleSpawner;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;

public class Stealer extends base_item {

    public Stealer(){
        super("stealer",1);
    }


    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(playerIn.world.isRemote) return false;
        InventoryEnderChest a = playerIn.getInventoryEnderChest();
        if(target instanceof EntityVillager){
            InventoryBasic b = ((EntityVillager) target).getVillagerInventory();
            int sizeB = b.getSizeInventory();
            for(int i=0;i<sizeB;i++){
                if(b.getStackInSlot(i).isEmpty()) continue;
                ItemStack c = a.addItem(b.getStackInSlot(i).copy());
                b.setInventorySlotContents(i,c);
            }
            return true;
        } else if(isSuitable(target)){
            ItemStack c = target.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            c = a.addItem(c);
            target.setItemStackToSlot(EntityEquipmentSlot.HEAD,c);
            c = target.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            c = a.addItem(c);
            target.setItemStackToSlot(EntityEquipmentSlot.CHEST,c);
            c = target.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            c = a.addItem(c);
            target.setItemStackToSlot(EntityEquipmentSlot.LEGS,c);
            c = target.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            c = a.addItem(c);
            target.setItemStackToSlot(EntityEquipmentSlot.FEET,c);
            if(!(target instanceof EntityPlayer)) {
                c = target.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                c = a.addItem(c);
                target.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, c);
                c = target.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
                c = a.addItem(c);
                target.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, c);
            }
            return true;
        } else
            return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }




    static boolean isSuitable(EntityLivingBase e){
        return e instanceof EntityZombie || e instanceof AbstractIllager || e instanceof AbstractSkeleton || e instanceof EntityWitch || e instanceof EntityPlayer;
    }
}
