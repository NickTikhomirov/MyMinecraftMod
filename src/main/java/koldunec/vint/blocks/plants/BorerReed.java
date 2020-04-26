package koldunec.vint.blocks.plants;

import koldunec.vint.helpers.NeighbourChecker;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.items.gunpowder_reed.block_gunreed;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.Random;

public class BorerReed extends Block implements IPlantable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

    public BorerReed(){
        super(Material.PLANTS);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE,0));
        this.setTickRandomly(true);
        this.setRegistryName("borer_reed_block");
        this.setUnlocalizedName("borer_reed_block");
        setLightOpacity(0);
        setSoundType(SoundType.PLANT);
    }


    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(!vint.integrationHelper.isLoadedTwilight)
            return;
        if(!checkAndDrop(worldIn, pos))
            return;
        if(!isSuitableForGrowOnce(worldIn,pos))
            return;
        if(ForgeHooks.onCropsGrowPre(worldIn,pos,state,true)){
            int age = getMetaFromState(state);
            if(age==3){
                worldIn.playEvent(2001, pos, Block.getStateId(worldIn.getBlockState(pos.down())));
                worldIn.setBlockState(pos.down(),getDefaultState().withProperty(AGE,0));
            } else {
                worldIn.setBlockState(pos, getDefaultState().withProperty(AGE,age+1));
            }
            ForgeHooks.onCropsGrowPost(worldIn,pos,state,worldIn.getBlockState(pos));
        }
    }



    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();
        if (block == this) {
            return true;
        }
        return isFullWood(worldIn,pos.down());
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        checkAndDrop(worldIn, pos);
    }

    public boolean checkAndDrop(World worldIn, BlockPos pos){
        pos = pos.down();

        if(isFullWood(worldIn, pos) || worldIn.getBlockState(pos).getBlock().equals(this))
            return true;
        dropBlockAsItem(worldIn, pos, this.getDefaultState(), 0);
        worldIn.setBlockToAir(pos.up());
        return false;
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemRegister.BORER_REED; }

    @Override
    public int quantityDropped(Random random) { return 1; }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState();
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
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Cave;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,new IProperty[]{AGE});
    }

    public boolean isSuitableForGrowOnce(World w, BlockPos bp){
        BlockPos base = bp.down();
        if(bp.getY()<3)
            return false;
        return isSafeWood(w, base) && isFullWood(w,base.down());
    }

    public boolean isFullWood(World w, BlockPos bp){
        IBlockState s = w.getBlockState(bp);
        //minecraftforum says
        // isFullBlock is more about placement
        // isFullCuve is more about visual (shadows etc)
        // however it is not a strict law
        //this says a lot about our society
        if(!s.isFullBlock())
            return false;
        return s.getMaterial().equals(Material.WOOD);
    }

    public boolean isSafeWood(World w, BlockPos bp){
        if(!isFullWood(w,bp))
            return false;
        if(w.getTileEntity(bp)!=null)
            return false;
        return NeighbourChecker.checkHorizontal(w,bp,liquidChecker)==0;
    }

    public static LiquidChecker liquidChecker = new LiquidChecker();





    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return block_gunreed.REED_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }


    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AGE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) { return this.getDefaultState().withProperty(AGE,meta); }


    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ItemRegister.BORER_REED);
    }

    public static class LiquidChecker implements NeighbourChecker.INeighbourPredicate{
        @Override
        public boolean check(IBlockState i) {
            return i.getMaterial().isLiquid();
        }
    }
}
