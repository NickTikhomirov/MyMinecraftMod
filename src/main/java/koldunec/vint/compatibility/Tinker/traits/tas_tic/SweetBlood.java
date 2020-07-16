package koldunec.vint.compatibility.Tinker.traits.tas_tic;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.OtherIntegration.NailHelper;
import koldunec.vint.vint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ToolCore;

public class SweetBlood extends ModifierTrait {

    public SweetBlood() {
        super("blood_hydration", ColorConstants.BLOOD_COLOR);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if(!IntegrationHelper.isLoadedTough || player.world.isRemote)
            return;
        if(!(player instanceof EntityPlayer))
            return;
        if(target instanceof AbstractSkeleton)
            return;
        if(vint.random.nextInt(4)!=0)
            return;
        NailHelper.hydrateWithBloodWithChance((EntityPlayer) player);
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        if(!(stack.getItem() instanceof ToolCore))
            return false;
        return ((ToolCore) stack.getItem()).hasCategory(Category.WEAPON);
    }
}
