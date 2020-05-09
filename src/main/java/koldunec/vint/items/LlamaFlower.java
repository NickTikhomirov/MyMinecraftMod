package koldunec.vint.items;

import koldunec.vint.vint;
import koldunec.vint.tileentities.TileLlama;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

import javax.annotation.Nullable;

@Optional.Interface(modid = "thaumcraft", iface = "thaumcraft.api.crafting.IInfusionStabiliserExt")
public class LlamaFlower extends Block implements IInfusionStabiliserExt {

    public static final IProperty<Boolean> POWER = PropertyBool.create("variant");

    public LlamaFlower(){
        super(Material.PLANTS);
        setSoundType(SoundType.PLANT);
        setUnlocalizedName("llama_flower");
        setRegistryName("llama_flower");
        setDefaultState(this.blockState.getBaseState().withProperty(POWER,false));
        setCreativeTab(vint.magicTab);
        setLightOpacity(0);
        lightValue=15;
    }


    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        pos = pos.down();
        IBlockState blockstate = worldIn.getBlockState(pos);
        Material basemat = blockstate.getMaterial();
        return blockstate.isFullBlock() && (basemat.equals(Material.GROUND) || basemat.equals(Material.GRASS));
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        pos = pos.down();
        IBlockState bs = worldIn.getBlockState(pos);
        if(!(bs.getMaterial().equals(Material.GROUND) || bs.getMaterial().equals(Material.GRASS)) || !bs.isFullBlock()) {
            dropBlockAsItem(worldIn, pos, this.getDefaultState(), 0);
            worldIn.setBlockToAir(pos.up());
        }
    }


    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{POWER});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POWER)?1:0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(POWER,meta!=0);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.5F - 0.2F, 0.0F, 0.5F - 0.2F, 0.5F + 0.2F, 0.2F * 4.0F, 0.5F + 0.2F);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(this,1,0));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this,1,0));
        items.add(new ItemStack(this,1,1));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        if(placer instanceof EntityPlayer){
            if(placer.getHeldItemMainhand().getMetadata()==1)
                return this.blockState.getBaseState().withProperty(POWER,true);
        }
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(POWER);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileLlama();
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
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }



    @Override
    public float getStabilizationAmount(World world, BlockPos blockPos) {
        return 200;
    }

    @Override
    public boolean canStabaliseInfusion(World world, BlockPos blockPos) {
        return true;
    }
}
