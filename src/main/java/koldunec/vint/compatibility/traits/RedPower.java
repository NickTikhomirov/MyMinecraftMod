package koldunec.vint.compatibility.traits;

import koldunec.vint.init.IntegrationHelper;
import net.daveyx0.primitivemobs.entity.monster.EntityVoidEye;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.HashSet;

public class RedPower extends AbstractTrait {

    public static HashSet<Class<? extends EntityLivingBase>> VICTIMS = new HashSet<>();

    public RedPower() {
        super("redpower",TextFormatting.RED);
    }


    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(VICTIMS.contains(target.getClass()))
            return 1F+target.getMaxHealth()/2F;
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }


    public static void initPerks(){
        VICTIMS.add(EntityEnderman.class);
        VICTIMS.add(EntityShulker.class);
        if(IntegrationHelper.isLoadedPrimitive)
            VICTIMS.add(EntityVoidEye.class);
    }
}
