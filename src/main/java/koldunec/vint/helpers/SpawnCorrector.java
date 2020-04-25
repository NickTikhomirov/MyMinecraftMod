package koldunec.vint.helpers;

import com.progwml6.natura.entities.entity.monster.EntityNitroCreeper;
import koldunec.vint.vint;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpawnCorrector {

    public static boolean HellCorrector(Entity one){
        if(one.getClass().equals(EntitySkeleton.class))
            if(vint.random.nextBoolean())
                return true;

        if(vint.integrationHelper.isLoadedNatura)
            if(one.getClass().equals(EntityNitroCreeper.class))
                return true;

        if(vint.integrationHelper.isLoadedHype){
            if(one.getClass().equals(EntityPigZombie.class) && vint.random.nextInt(8)==0){
                one.setItemStackToSlot(EntityEquipmentSlot.CHEST,new ItemStack(Item.getByNameOrId("hypewear:aggc_chestplate"),1,0));
                one.setItemStackToSlot(EntityEquipmentSlot.LEGS,new ItemStack(Item.getByNameOrId("hypewear:castle_leggings"),1,0));
            }
        }
        return false;
    }
}
