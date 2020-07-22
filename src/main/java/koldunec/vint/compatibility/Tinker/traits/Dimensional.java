package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.compatibility.TwilightForest.MainTFModule;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.List;

public class Dimensional extends ModifierTrait {
    public Dimensional() {
        super("dimensional", ColorConstants.SPECTRE_COLOR);
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        float speed = event.getNewSpeed();
        if(IntegrationHelper.isLoadedTwilight && ToolHelper.getTraits(tool).contains(MainTFModule.getTwilitTrait())){
            if(!MainTFModule.isTF(event.getEntity().world))
                speed+=2;
        }
        event.setNewSpeed(speed);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        List<ITrait> traits = ToolHelper.getTraits(tool);
        World targetworld = target.world;
        int data = targetworld.provider.getDimension();
        if(IntegrationHelper.isLoadedTwilight && traits.contains(MainTFModule.getTwilitTrait())){
            if(!MainTFModule.isTF(targetworld))
                newDamage+=2;
        }
        if(traits.contains(TinkerIntegration.CRUSADE)){
            if(data>-2 && data<2)
                newDamage+=4;
        }
        if(traits.contains(TinkerTraits.hellish)){
            if(target.isImmuneToFire())
                newDamage+=4;
        }
        if(IntegrationHelper.isLoaded("plustic")){
            for(ITrait t: traits)
                if(t.getIdentifier().equals("devilsstrength") && data==0)
                    newDamage+=2;
        }
        return newDamage;
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        return !((ToolCore) stack.getItem()).hasCategory(Category.NO_MELEE);
    }
}
