package koldunec.vint.blocks.plants;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class PowderReedBlock extends Block implements net.minecraftforge.common.IPlantable {
    public static int maxSize = 5;

    public enum reedTypes implements IStringSerializable {
        GUNPOWDER, REDSTONE, NICOLITE;

        public int typeToInt(){
            if(equals(REDSTONE)) return 1;
            if(equals(NICOLITE)) return 2;
            return 0;
        }

        public static reedTypes intToType(int i){
            if(i==2) return reedTypes.NICOLITE;
            if(i==1) return reedTypes.REDSTONE;
            return reedTypes.GUNPOWDER;
        }

        public String getName(){
            return toString().toLowerCase();
        }

        public static reedTypes getFromState(IBlockState state){
            if(!state.getBlock().equals(BlockRegister.REED_GUNPOWDER))
                return null;
            return state.getValue(VARIANT);
        }

        public boolean canGrow(IBlockState state){
            if(state.getBlock().equals(Blocks.AIR))
                return equals(GUNPOWDER);
            reedTypes temp = getFromState(state);
            if(temp==null)
                return false;
            return temp.typeToInt()+1==typeToInt();
        }
    }
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 4);
    public static final IProperty<reedTypes> VARIANT = PropertyEnum.create("variant", reedTypes.class);
    public static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);


    public PowderReedBlock(){
        super(Material.PLANTS);
        setSoundType(SoundType.PLANT);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE,0).withProperty(VARIANT,reedTypes.GUNPOWDER));
        setTickRandomly(true);
        setRegistryName("block_gunreed");
        setUnlocalizedName("block_gunreed");
        setLightOpacity(0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AGE,VARIANT});
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return REED_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) { return NULL_AABB; }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 5*state.getValue(VARIANT).typeToInt() + state.getValue(AGE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int age = meta%5;
        int type = meta/5;
        return getDefaultState().withProperty(AGE,age).withProperty(VARIANT,reedTypes.intToType(type));
    }






    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        boolean isHigh = pos.getY()>254;
        if(!checkForDrop(worldIn, pos, state))
            return;
        reedTypes variant = state.getValue(VARIANT);
        if(!isHigh)
            if(!variant.canGrow(worldIn.getBlockState(pos.up())))
                return;
        int type = variant.typeToInt();
        int size;
        for (size = 1; worldIn.getBlockState(pos.down(size)).getBlock() == this; ++size){}
        if(type==0){
            if(size>=maxSize || isHigh){
                if(isReed(worldIn.getBlockState(pos.up(1)).getBlock()))
                    return;
                if(ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    worldIn.setBlockState(pos.down(size - 1), getDefaultState().withProperty(VARIANT, reedTypes.REDSTONE).withProperty(AGE, 0));
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            } else {
                int age = state.getValue(AGE);
                if(ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)){
                    if(age>3){
                        worldIn.setBlockState(pos.up(), getDefaultState());
                        worldIn.setBlockState(pos, state.withProperty(AGE, 0), 4);
                    } else
                        worldIn.setBlockState(pos, state.withProperty(AGE, age + 1), 4);
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        } else if(type==1 && IntegrationHelper.isLoadedProjectRed_exploration){
            if(isHigh || !isReed(worldIn.getBlockState(pos.up()).getBlock())){
                if(ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    worldIn.setBlockState(pos.down(size - 1), getDefaultState().withProperty(VARIANT, reedTypes.NICOLITE).withProperty(AGE, 0));
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            } else {
                if(worldIn.getBlockState(pos.up()).getValue(VARIANT).typeToInt()==0 && vint.random.nextInt(3)==0){
                    if(ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                        worldIn.setBlockState(pos.up(), getDefaultState().withProperty(VARIANT, reedTypes.REDSTONE).withProperty(AGE, 0));
                        ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                    }
                }
            }
        } else {
            if(pos.getY()>254 || !isReed(worldIn.getBlockState(pos.up()).getBlock()))
                return;
            if(worldIn.getBlockState(pos.up()).getValue(VARIANT).typeToInt()<=type && vint.random.nextBoolean()){
                if(ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(VARIANT, getFinalType()).withProperty(AGE, 0));
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();
        if (block == this)
            return true;
        return block.equals(BlockRegister.FERTILE_SAND);

    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        checkForDrop(worldIn, pos, state);
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
        if(canPlaceBlockAt(worldIn, pos))
            return true;
        dropBlockAsItem(worldIn, pos, state, 0);
        worldIn.setBlockToAir(pos);
        return false;
    }

    public static boolean isReed(Block block){
        return block.equals(BlockRegister.REED_GUNPOWDER);
    }

    public static reedTypes getFinalType(){
        return IntegrationHelper.isLoadedProjectRed_exploration? reedTypes.NICOLITE:reedTypes.REDSTONE;
    }






    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, fortune);
        drops.add(new ItemStack(ItemRegister.POWDER_REED,1,state.getValue(VARIANT).typeToInt()));
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ItemRegister.POWDER_REED,1,state.getValue(VARIANT).typeToInt());
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        int m = meta;
        if(placer instanceof EntityPlayer){
            EntityPlayer e = (EntityPlayer) placer;
            if(e.getHeldItemMainhand().getItem().equals(ItemRegister.POWDER_REED))
                m = e.getHeldItemMainhand().getMetadata()*5;
        }
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, m, placer, hand);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }






    @Override
    public boolean canProvidePower(IBlockState state) {
        return state.getValue(VARIANT).typeToInt()==1;
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if(blockState.getValue(VARIANT).typeToInt()==1) return 15;
        return super.getStrongPower(blockState, blockAccess, pos, side);
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if(blockState.getValue(VARIANT).typeToInt()==1) return 15;
        return super.getWeakPower(blockState, blockAccess, pos, side);
    }
}
