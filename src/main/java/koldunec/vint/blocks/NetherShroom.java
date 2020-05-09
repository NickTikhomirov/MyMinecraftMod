package koldunec.vint.blocks;

import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class NetherShroom extends Block {
    public NetherShroom(String name) {
        super(Material.PLANTS);
        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.PLANT);
        setCreativeTab(vint.magicTab);
        setLightOpacity(0);
    }


    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        pos = pos.down();
        IBlockState blockstate = worldIn.getBlockState(pos);
        Material basemat = blockstate.getMaterial();
        if(!blockstate.isFullBlock())
            return false;
        if(!(basemat.equals(Material.GROUND) || basemat.equals(Material.GRASS)))
            return false;
        return super.canPlaceBlockAt(worldIn, pos.up());
    }


    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        pos = pos.down();
        IBlockState bs = worldIn.getBlockState(pos);
        Material basemat = bs.getMaterial();
        if(!(basemat.equals(Material.GROUND)|| basemat.equals(Material.GRASS)) || !bs.isFullBlock()) {
            dropBlockAsItem(worldIn, pos.up(), this.getDefaultState(), 0);
            worldIn.setBlockToAir(pos.up());
        }
    }


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.5F - 0.2F, 0.0F, 0.5F - 0.2F, 0.5F + 0.2F, 0.2F * 4.0F, 0.5F + 0.2F);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}
