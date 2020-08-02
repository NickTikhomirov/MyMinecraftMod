package koldunec.vint.blocks;

import koldunec.vint.tileentities.TileEntityFlopper;
import koldunec.vint.utils.BehaviourDispenseFood;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFlopper extends BlockDispenser {
    private final IBehaviorDispenseItem dropBehavior = new BehaviourDispenseFood();

    protected IBehaviorDispenseItem getBehavior(ItemStack stack)
    {
        return dropBehavior;
    }

    public BlockFlopper(){
        super();
        setCreativeTab(vint.magicTab);
        setRegistryName("flopper");
        setUnlocalizedName("flopper");
        setHardness(5F);  // random number lol
        setSoundType(SoundType.STONE);
    }
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityFlopper();
    }

    // I _DO_NOT_ KNOW WHY MINECRAFT IS ABLE TO WORK WITH THAT CODE,
    // BUT MY DROPPER _DO_NOT_ WORK WITHOUT THIS OVERRIDING
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
        boolean flag1 = state.getValue(TRIGGERED);
        if (flag && !flag1)
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

}