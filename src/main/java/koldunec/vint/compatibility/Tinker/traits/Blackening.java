package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Blackening extends AbstractTrait {
    public Blackening() {
        super("blackening", TextFormatting.BLACK);
    }

    @Override
    public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        if(block.equals(Blocks.COBBLESTONE) || block.equals(Blocks.STONE)){
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(BlockRegister.BLACKSTONE));
        }
    }
}
