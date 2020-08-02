package koldunec.vint.utils;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.OtherIntegration.PrimitiveIntegration;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import scala.collection.generic.BitOperations;

import java.util.HashSet;

public class UsefulSets {
    public static HashSet<Class<? extends EntityLivingBase>> ENDER_CREATURES = new HashSet<>();
    public static HashSet<Item> MEAT = new HashSet<>();


    public static void init(){
        initEnder();
    }

    private static void initMeat(){
        MEAT.add(Items.PORKCHOP);
        MEAT.add(Items.COOKED_PORKCHOP);
        MEAT.add(Items.BEEF);
        MEAT.add(Items.COOKED_BEEF);
        MEAT.add(Items.CHICKEN);
        MEAT.add(Items.COOKED_CHICKEN);
        MEAT.add(Items.FISH);
        MEAT.add(Items.COOKED_FISH);
        MEAT.add(Items.MUTTON);
        MEAT.add(Items.COOKED_MUTTON);
        MEAT.add(Items.RABBIT);
        MEAT.add(Items.COOKED_RABBIT);
        MEAT.add(Items.ROTTEN_FLESH);
    }

    private static void initEnder(){
        ENDER_CREATURES.add(EntityEnderman.class);
        ENDER_CREATURES.add(EntityShulker.class);
        if(IntegrationHelper.isLoadedPrimitive)
            PrimitiveIntegration.initUsefulSets();
    }
}
