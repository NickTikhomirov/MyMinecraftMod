package koldunec.vint.broomitems;

import koldunec.vint.broomitems.baseItems.base_item;
import koldunec.vint.utils.ParticleSpawner;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
        if(target instanceof EntityVillager){
            boolean success=true;
            InventoryEnderChest a = playerIn.getInventoryEnderChest();
            InventoryBasic b = ((EntityVillager) target).getVillagerInventory();
            int sizeB = b.getSizeInventory();
            if(sizeB==0) {
                playerIn.getEntityWorld().spawnParticle(
                        EnumParticleTypes.VILLAGER_ANGRY,
                        target.posX,
                        target.posY+target.height,
                        target.posZ,
                        0,
                        0.25,
                        0
                );
                success=false;
            }
            for(int i=0;i<sizeB;i++){
                if(b.getStackInSlot(i).isEmpty()) continue;
                ItemStack c = a.addItem(b.getStackInSlot(i).copy());
                b.setInventorySlotContents(i,c);
                if(!b.getStackInSlot(i).getItem().equals(Items.AIR)) {
                    ParticleSpawner.cloud(
                            EnumParticleTypes.SMOKE_NORMAL,
                            playerIn.getEntityWorld(),
                            40,
                            playerIn.posX,
                            playerIn.posY+playerIn.getEyeHeight(),
                            playerIn.posZ,
                            0.5,0.25,0.5,
                            playerIn.posX-target.posX,
                            playerIn.posY-target.posY + 0.7*playerIn.getEyeHeight()-0.4*((EntityVillager)target).height,
                            playerIn.posZ-target.posZ,
                            -2
                    );
                    success=false;
                }
            }
            if(success){
                ParticleSpawner.cloud(
                        EnumParticleTypes.PORTAL,
                        playerIn.getEntityWorld(),
                        40,
                        playerIn.posX,
                        playerIn.posY+playerIn.getEyeHeight(),
                        playerIn.posZ,
                        0.5,0.25,0.5,
                        playerIn.posX-target.posX,
                        playerIn.posY-target.posY + 0.7*playerIn.getEyeHeight()-0.4*((EntityVillager)target).height,
                        playerIn.posZ-target.posZ,
                        -2
                );
            }
            return true;
        } else return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}
