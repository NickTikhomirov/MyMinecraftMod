package koldunec.vint.compatibility.Tinker.traits;

import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.utils.VanillaHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.CompatibilityRegister;
import koldunec.vint.IntegrationHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ToolCore;

import java.util.HashSet;

public class Capitator extends ModifierTrait {
    public HashSet<Block> TARGETS = new HashSet<Block>(){{
        add(BlockRegister.NETHER_CACTUS);
    }};
    public HashSet<Block> FORBIDDEN = new HashSet<Block>(){{
       add(Blocks.PUMPKIN_STEM);
       add(Blocks.MELON_STEM);
    }};

    public void appendCactus(){
        TARGETS.add(Block.getBlockFromName("minecraft:cactus"));
        TARGETS.add(Block.getBlockFromName("minecraft:reeds"));
        if(IntegrationHelper.isLoadedFuture)
            TARGETS.add(Block.getBlockFromItem(CompatibilityRegister.FUTURE_BAMBOO));
    }

    public Capitator() {
        super("capitator", ColorConstants.GREEN_LOG_COLOR);
    }

    private static BlockPos findTop(Block b, World w, BlockPos pos){
        do {
            pos = pos.up();
            if(pos.getY()>254)
                return pos;
        } while(w.getBlockState(pos).getBlock().equals(b));
        return pos.down();
    }

    private static boolean isLone(World w, BlockPos pos){
        Block b = w.getBlockState(pos).getBlock();
        return !b.equals(w.getBlockState(pos.down()).getBlock()) && !b.equals(w.getBlockState(pos.up()).getBlock());
    }


    @Override
    public void beforeBlockBreak(ItemStack tool, BlockEvent.BreakEvent event) {
        World w = event.getWorld();
        if(w==null)
            return;
        BlockPos bp = event.getPos();
        if(bp==null)
            return;
        EntityPlayer ep = event.getPlayer();
        if(ep==null)
            return;
        Block block = w.getBlockState(bp).getBlock();
        if(FORBIDDEN.contains(block)){
            event.setCanceled(true);
            return;
        }
        if(!TARGETS.contains(block))
            return;
        if(isLone(w,bp))
            return;
        BlockPos top = findTop(block,w,bp);
        int count = 0;
        for(BlockPos i = top; w.getBlockState(i.down()).getBlock().equals(block); i=i.down()){
            ++count;
            VanillaHelper.VisualBreak(i, w);
            w.setBlockState(i,Blocks.AIR.getDefaultState());
            if(w.getBlockState(i.down()).getBlock().equals(Blocks.AIR))    //in case of illegal cacti
                break;
        }
        ItemStack loot = new ItemStack(block.getItemDropped(null,null,0),count);  // pls don't support complex things
        VanillaHelper.GivePlayer(ep,loot);
        event.setCanceled(true);
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        if(!(stack.getItem() instanceof ToolCore))
            return false;
        return ((ToolCore) stack.getItem()).hasCategory(Category.HARVEST);
    }
}
