package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.compatibility.Tinker.ToolRebuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.harvest.TinkerHarvestTools;

@Mod.EventBusSubscriber
public class Shifting extends AbstractTrait {
    public Shifting() {
        super("shifting", ColorConstants.FROZEN_COLOR);
    }


    @SubscribeEvent
    public static void onBlockStart(PlayerInteractEvent.LeftClickBlock e){
        ItemStack tool = e.getItemStack();
        Item core = tool.getItem();
        if(!(core instanceof ToolCore))
            return;
        EntityPlayer player = e.getEntityPlayer();
        IBlockState state = e.getWorld().getBlockState(e.getPos());
        if(ToolHelper.isToolEffective2(tool, state))
            return;
        if(!ToolHelper.getTraits(tool).contains(TinkerIntegration.SHIFTING))
            return;
        boolean isHuge = core.equals(TinkerHarvestTools.excavator) || core.equals(TinkerHarvestTools.lumberAxe) || core.equals(TinkerHarvestTools.hammer);
        if(!(isHuge || core.equals(TinkerHarvestTools.pickaxe) || core.equals(TinkerHarvestTools.hatchet) || core.equals(TinkerHarvestTools.shovel)))
            return;
        ToolRebuilder.TYPE type = ToolRebuilder.TYPE.getByState(state);
        if(type==null)
            return;
        EnumHand eh = null;
        if(player.getHeldItem(EnumHand.MAIN_HAND).equals(tool))
            eh = EnumHand.MAIN_HAND;
        else if(player.getHeldItem(EnumHand.OFF_HAND).equals(tool))
            eh = EnumHand.OFF_HAND;
        if(eh==null)
            return;
        player.setItemStackToSlot(
                eh.equals(EnumHand.MAIN_HAND)?
                        EntityEquipmentSlot.MAINHAND:EntityEquipmentSlot.OFFHAND,
                isHuge?
                        ToolRebuilder.rebuildComplex(tool, type): ToolRebuilder.rebuildSimple(tool, type));
        e.setCanceled(true);
    }

}
