package koldunec.vint.utils;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.OtherIntegration.PrimitiveIntegration;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityShulker;

import java.util.HashSet;

public class UsefulSets {
    public static HashSet<Class<? extends EntityLivingBase>> ENDER_CREATURES = new HashSet<>();


    public static void init(){
        ENDER_CREATURES.add(EntityEnderman.class);
        ENDER_CREATURES.add(EntityShulker.class);
        if(IntegrationHelper.isLoadedPrimitive)
            PrimitiveIntegration.initUsefulSets();
    }
}
