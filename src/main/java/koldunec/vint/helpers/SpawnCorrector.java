package koldunec.vint.helpers;

import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpawnCorrector {

    public static boolean HellCorrector(Entity one){
        if(one.getPosition().getY()>127){
            if(one instanceof EntityCreeper)
                return true;
            if(one instanceof EntityEnderman)
                return false;
            if(one instanceof EntityMagmaCube)
                return false;
            if(one instanceof EntityBlaze)
                return false;
            String domain = TechHelper.getRegistryName(one).getResourceDomain();
            if(domain.equals("primitivemobs"))
                return false;
            if(domain.equals("quark"))
                return false;
            return true;
        } else {
            if(one instanceof EntityEnderman)
                return true;
        }

        if(one.getClass().equals(EntitySkeleton.class))
            if(vint.random.nextBoolean())
                return true;

        if(IntegrationHelper.isLoadedHype){
            if(one.getClass().equals(EntityPigZombie.class) && vint.random.nextInt(8)==0){
                one.setItemStackToSlot(EntityEquipmentSlot.CHEST,new ItemStack(Item.getByNameOrId("hypewear:aggc_chestplate"),1,0));
                one.setItemStackToSlot(EntityEquipmentSlot.LEGS,new ItemStack(Item.getByNameOrId("hypewear:castle_leggings"),1,0));
            }
        }
        return false;
    }
}
