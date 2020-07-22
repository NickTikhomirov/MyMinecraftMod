package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.TwilightForest.MainTFModule;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;


public class SlimeCutter extends AbstractTrait {

    public static float amount = 3F;

    public SlimeCutter() {
        super("slimecutter", ColorConstants.BAMBOO_COLOR);
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
