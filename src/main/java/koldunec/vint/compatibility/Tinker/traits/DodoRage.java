package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.vint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class DodoRage extends ModifierTrait {

    public static int PROBABILITY_BOUND = 100;

    public DodoRage() {
        super("dodo_rage", ColorConstants.CARMINITE_COLOR);
        for(int i=0; i<aspects.size(); ++i)
            if(aspects.get(i) instanceof ModifierAspect.FreeModifierAspect){
                aspects.set(i, new ModifierAspect.FreeModifierAspect(0));
                break;
            }
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(vint.random.nextInt(PROBABILITY_BOUND)==0)
            return target.getMaxHealth()*5;
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }
}
