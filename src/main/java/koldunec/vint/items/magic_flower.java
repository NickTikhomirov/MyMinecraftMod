package koldunec.vint.items;

import koldunec.vint.vint;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class magic_flower extends BlockBush {
    public magic_flower(String name){
        super();
        this.setCreativeTab(vint.magicTab);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT;
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos) {
        return net.minecraftforge.common.EnumPlantType.Plains;
    }
}
