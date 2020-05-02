package koldunec.vint.blocks;

import koldunec.vint.vint;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTriDirectional extends BlockRotatedPillar {

    static Material initMat(String s){
        if(s.equals("basalt_raw") || s.equals("basalt_pillar") || s.equals("debris")) {
            return Material.ROCK;
        }
        return Material.WOOD;
    }

    public BlockTriDirectional(String name) {
        super(initMat(name));
        setRegistryName(name);
        setUnlocalizedName(name);
        setDefaultState(blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
        setCreativeTab(vint.magicTab);
        if(name.equals("basalt_raw") || name.equals("basalt_pillar") || name.equals("debris")) {
            setHarvestLevel("pickaxe",0);
            setSoundType(SoundType.STONE);
            setHardness(1.5F);
            setResistance(6F);
            return;
        }
        setHarvestLevel("axe",0);
        setHardness(2F);
        setResistance(2F);
        setSoundType(SoundType.WOOD);
    }


    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        switch (facing.getAxis()){
            case X: return getDefaultState().withProperty(AXIS,EnumFacing.Axis.X);
            case Y: return getDefaultState().withProperty(AXIS,EnumFacing.Axis.Y);
            case Z: return getDefaultState().withProperty(AXIS,EnumFacing.Axis.Z);
        }
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        switch(rot){
            case CLOCKWISE_90:
            case COUNTERCLOCKWISE_90:
                switch (state.getValue(AXIS)){
                    case X:
                        return getDefaultState().withProperty(AXIS,EnumFacing.Axis.Z);
                    case Z:
                        return getDefaultState().withProperty(AXIS,EnumFacing.Axis.X);
                }
        }
        return getDefaultState().withProperty(AXIS,EnumFacing.Axis.Y);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS);
    }

    @Override
    public int damageDropped(IBlockState state) { return 0; }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing.Axis ea = state.getValue(AXIS);
        switch(ea){
            case Y: return 0;
            case X: return 1;
            case Z: return 2;
        }
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        switch (meta){
            case 0: return getDefaultState().withProperty(AXIS,EnumFacing.Axis.Y);
            case 1: return getDefaultState().withProperty(AXIS,EnumFacing.Axis.X);
            case 2: return getDefaultState().withProperty(AXIS,EnumFacing.Axis.Z);
        }
        return getStateFromMeta(0);
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        IBlockState i = world.getBlockState(pos);
        if(!i.getBlock().getClass().equals(BlockTriDirectional.class))
            return false;
        switch (getMetaFromState(i)){
            case 0: {  //y
                world.setBlockState(pos,i.withProperty(AXIS, EnumFacing.Axis.Z));
                return true;
            }
            case 1: {  //x
                world.setBlockState(pos,i.withProperty(AXIS, EnumFacing.Axis.Y));
                return true;
            }
            case 2: {  //z
                world.setBlockState(pos,i.withProperty(AXIS, EnumFacing.Axis.X));
                return true;
            }
        }
        return false;
    }
}
