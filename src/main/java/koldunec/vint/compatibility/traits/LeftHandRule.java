package koldunec.vint.compatibility.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class LeftHandRule extends AbstractTrait {
    public LeftHandRule() {
        super("lefthandrule",TextFormatting.GOLD);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        if(entity.getHeldItemOffhand().equals(tool)){
            ItemStack main = entity.getHeldItemMainhand();
            if(main.getItem() instanceof TinkerToolCore)
                if(!ToolHelper.isBroken(main)) {
                    ToolHelper.damageTool(main, damage, entity);
                    return Math.max(0, newDamage-damage);
                }
        }
        return super.onToolDamage(tool, damage, newDamage, entity);
    }
}
