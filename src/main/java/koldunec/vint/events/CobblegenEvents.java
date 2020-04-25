package koldunec.vint.events;

import koldunec.vint.helpers.VanillaHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CobblegenEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLavaTouchBlock(BlockEvent.NeighborNotifyEvent e){
        Block b = e.getState().getBlock();
        World w = e.getWorld();
        if(!b.equals(Blocks.FLOWING_LAVA))
            return;
        if(w.getBlockState(e.getPos().up()).getBlock().equals(Blocks.SOUL_SAND)) {
            if(w.isRemote) {
                VanillaHelper.triggerMixEffects(e.getWorld(),e.getPos());
                return;
            }
            w.setBlockState(e.getPos(), ((BlockRedSandstone)Blocks.RED_SANDSTONE).getStateFromMeta(1), 1 + 2);
            e.setCanceled(true);
        }

    }

}
