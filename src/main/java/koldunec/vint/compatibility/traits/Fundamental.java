package koldunec.vint.compatibility.traits;

import koldunec.vint.helpers.TechHelper;
import koldunec.vint.helpers.VanillaHelper;
import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;


public class Fundamental extends ModifierTrait {
    public Fundamental() {
        super("fundamental", TechHelper.getColor(128,0,64));
    }

    @Override
    public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        if(!block.equals(Blocks.COBBLESTONE) || block.equals(Blocks.STONE) || block.equals(BlockRegister.BASALT_RAW))
            return;
        for(ItemStack i: event.getDrops())
            VanillaHelper.GivePlayer(event.getHarvester(),i);
        event.getDrops().clear();
    }
}
