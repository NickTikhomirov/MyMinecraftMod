package koldunec.vint.events;

import koldunec.vint.helpers.NeighbourChecker;
import koldunec.vint.helpers.VanillaHelper;
import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CobblegenEvents {

    private static class IceChecker implements NeighbourChecker.INeighbourPredicate{
        @Override
        public boolean check(IBlockState i) {
            return i.getBlock().equals(Blocks.PACKED_ICE);
        }
    }

    static IceChecker checker = new IceChecker();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLavaTouchBlock(BlockEvent.NeighborNotifyEvent e){
        Block b = e.getState().getBlock();
        World w = e.getWorld();
        if(!b.equals(Blocks.FLOWING_LAVA))
            return;
        if(NeighbourChecker.checkHorizontal(e.getWorld(),e.getPos(),checker)>0) {
            if(w.isRemote) {
                VanillaHelper.triggerMixEffects(e.getWorld(),e.getPos());
                return;
            }
            w.setBlockState(e.getPos(), BlockRegister.BASALT_RAW.getDefaultState());
            e.setCanceled(true);
        }
    }

}
