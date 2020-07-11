package koldunec.vint.compatibility.Tinker.traits;


import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.vint;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.ranged.item.CrossBow;

public class IceQueen extends AbstractProjectileTrait {
    public IceQueen() {
        super("ice_queen_has_a_crossbow", ColorConstants.FROZEN_COLOR);
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if(!(tool.getItem() instanceof CrossBow))
            return;
        if(!(entity instanceof EntityPlayer))
            return;
        if(vint.random.nextInt(200)!=0)
            return;
        CrossBow crossBow = (CrossBow) tool.getItem();
        if(!ToolHelper.isBroken(tool) && !crossBow.isLoaded(tool)) {
            Sounds.PlaySoundForPlayer(entity, Sounds.crossbow_reload, 1.5f, 0.9f + vint.random.nextFloat() * 0.1f);
            crossBow.setLoaded(tool, true);
        }
    }
}
