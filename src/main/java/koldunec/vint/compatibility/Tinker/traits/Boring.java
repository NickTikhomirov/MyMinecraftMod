package koldunec.vint.compatibility.Tinker.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class Boring extends AbstractTrait {

    public Boring() {
        super("boring",TextFormatting.YELLOW);
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        if(ToolHelper.hasCategory(tool, Category.NO_MELEE)) {
            super.miningSpeed(tool, event);
            return;
        }
        IBlockState ib = event.getState();
        if(ib.getBlock().isToolEffective("shovel",ib) || ib.getBlock().isToolEffective("axe",ib)){
            event.setNewSpeed(ToolHelper.getActualMiningSpeed(tool));
        }
    }


    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        super.afterBlockBreak(tool, world, state, pos, player, wasEffective);
        if(!wasEffective)
            ToolHelper.healTool(tool,1,player);
    }
}
