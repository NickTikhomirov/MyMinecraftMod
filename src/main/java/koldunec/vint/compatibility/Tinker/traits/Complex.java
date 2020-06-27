package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.utils.TechHelper;
import koldunec.vint.IntegrationHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ToolCore;

public class Complex extends ModifierTrait {
    public Complex() {
        super("complex", TechHelper.getColor(255,128,255));
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(!IntegrationHelper.isLoadedPrimitive)
            return super.damage(tool, player, target, damage, newDamage, isCritical);
        String modid = TechHelper.getRegistryName(target).getResourceDomain();
        if(modid.equals("primitivemobs"))
            newDamage+=3;
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        return ((ToolCore) stack.getItem()).hasCategory(Category.WEAPON);
    }
}
