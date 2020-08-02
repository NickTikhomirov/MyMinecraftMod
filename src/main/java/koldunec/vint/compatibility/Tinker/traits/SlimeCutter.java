package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.TwilightForest.MainTFModule;
import koldunec.vint.vint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.List;


public class SlimeCutter extends AbstractTrait {

    public static float amount = 3F;

    public SlimeCutter() {
        super("slimecutter", ColorConstants.BAMBOO_COLOR);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if(!(target instanceof EntitySlime))
            return;
        if(vint.random.nextBoolean())
            return;
        List<EntitySlime> othersToDamage = target.world.getEntitiesWithinAABB(EntitySlime.class,
                new AxisAlignedBB(target.posX-1, target.posY-0.5F, target.posZ-1,
                        target.posX+1, target.posY+0.5F, target.posZ+1));
        othersToDamage.remove(target);
        for(EntitySlime e: othersToDamage){
            if(e.isSmallSlime())
                e.attackEntityFrom(new EntityDamageSource(DamageSource.GENERIC.damageType, player), amount);
        }
    }



    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(target instanceof EntitySlime)
            newDamage+=amount;
        else if(IntegrationHelper.isLoadedTwilight)
            if(MainTFModule.checkBeetle(target))
                newDamage+=amount;
        return newDamage;
    }

}
