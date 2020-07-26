package koldunec.vint.compatibility.Tinker.traits;


import koldunec.vint.utils.UsefulSets;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;


public class RedPower extends AbstractTrait {

    public RedPower() {
        super("redpower",TextFormatting.RED);
    }


    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(UsefulSets.ENDER_CREATURES.contains(target.getClass()))
            return 1F+target.getMaxHealth()/2F;
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }


}
