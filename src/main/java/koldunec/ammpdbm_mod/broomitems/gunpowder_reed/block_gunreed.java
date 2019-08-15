package koldunec.ammpdbm_mod.broomitems.gunpowder_reed;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class block_gunreed extends Block implements net.minecraftforge.common.IPlantable {
    public enum reedTypes implements IStringSerializable {
        GUNPOWDER, REDSTONE, NICOLITE;

        public int typeToInt(){
            if(this.equals(REDSTONE)) return 1;
            if(this.equals(NICOLITE)) return 2;
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
    }
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 4);
    public static final IProperty<reedTypes> VARIANT = PropertyEnum.create("variant", reedTypes.class);
    protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);


    public block_gunreed(){
        super(Material.PLANTS);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE,0).withProperty(VARIANT,reedTypes.GUNPOWDER));
        this.setTickRandomly(true);
        this.setRegistryName("block_gunreed");
        this.setUnlocalizedName("block_gunreed");
        setLightOpacity(0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,new IProperty[]{AGE,VARIANT});
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return REED_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 5*state.getValue(VARIANT).typeToInt() + state.getValue(AGE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int age = meta%5;
        int type = meta/5;
        return this.getDefaultState().withProperty(AGE,age).withProperty(VARIANT,reedTypes.intToType(type));
    }






    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(isReed(worldIn.getBlockState(pos.down()).getBlock()) || this.checkForDrop(worldIn, pos, state)){
            int type = state.getValue(VARIANT).typeToInt();
            int size;
            for (size = 1; worldIn.getBlockState(pos.down(size)).getBlock() == this; ++size){}
            boolean blocked =
                    size>2 ||
                    pos.getY()==256 ||
                    !worldIn.isAirBlock(pos.up(1));
            if(type==0){
                if(!blocked){
                    if(ammpdbm_mod.random.nextBoolean()){
                        int j = state.getValue(AGE);
                        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)){
                            if(j>=4){
                                worldIn.setBlockState(pos.up(),this.getDefaultState());
                                worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 4);
                            } else worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                        }
                    }
                } else {
                    if(isReed(worldIn.getBlockState(pos.up(1)).getBlock())) return;
                    if(ammpdbm_mod.random.nextInt(3)==0){
                        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                            worldIn.setBlockState(pos.down(size - 1), this.getDefaultState().withProperty(VARIANT, reedTypes.REDSTONE).withProperty(AGE, 0));
                            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                        }
                    }
                }
            } else if(type==1 && ammpdbm_mod.isLoadedProjectRed_exploration){
                if(pos.getY()==256 || !isReed(worldIn.getBlockState(pos.up()).getBlock())){
                    if(ammpdbm_mod.random.nextBoolean()){
                        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                            worldIn.setBlockState(pos.down(size - 1), this.getDefaultState().withProperty(VARIANT, reedTypes.NICOLITE).withProperty(AGE, 0));
                            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                        }
                    }
                } else {
                    if(worldIn.getBlockState(pos.up()).getValue(VARIANT).typeToInt()==0 && ammpdbm_mod.random.nextInt(3)==0){
                        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                            worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(VARIANT, reedTypes.REDSTONE).withProperty(AGE, 0));
                            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                        }
                    }
                }

            // Николит или Редстоун при отсутствии ProjectRed-Exploration
            } else {
                if(pos.getY()==256 || !isReed(worldIn.getBlockState(pos.up()).getBlock())){
                    if(ammpdbm_mod.random.nextInt(4)==0){
                        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                            BlockPos bottom = pos.down(size-1);
                            for(;worldIn.getBlockState(bottom).getBlock().equals(BlockRegister.REED_GUNPOWDER);bottom=bottom.up()){
                                worldIn.setBlockState(bottom,Blocks.REEDS.getDefaultState());
                            }
                            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                        }
                    }
                } else {
                    if(worldIn.getBlockState(pos.up()).getValue(VARIANT).typeToInt()<=type && ammpdbm_mod.random.nextBoolean()){
                        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                            worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(VARIANT, reedTypes.intToType(getFinalVariant())).withProperty(AGE, 0));
                            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();
        if (block.canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this)) return true;
        if (block == this) {
            return true;
        } else if (block!=Blocks.SOUL_SAND) {
            return false;
        } else {
            BlockPos blockpos = pos.down();
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                IBlockState iblockstate = worldIn.getBlockState(blockpos.offset(enumfacing));
                if (iblockstate.getBlock() == Blocks.REDSTONE_BLOCK)
                    return true;
            }
            return false;
        }
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkForDrop(worldIn, pos, state);
    }

    protected final boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canBlockStay(worldIn, pos))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        if(worldIn.getBlockState(pos.down()).getBlock().equals(Blocks.REEDS)) return true;
        return this.canPlaceBlockAt(worldIn, pos);
    }

    public static boolean isReed(Block block){
        return block.equals(BlockRegister.REED_GUNPOWDER) || block.equals(Blocks.REEDS);
    }

    public static int getFinalVariant(){
        return ammpdbm_mod.isLoadedProjectRed_exploration?2:1;
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
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

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
