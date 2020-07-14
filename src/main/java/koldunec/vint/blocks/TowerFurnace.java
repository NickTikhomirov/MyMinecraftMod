package koldunec.vint.blocks;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.tileentities.TileTower;
import koldunec.vint.vint;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class TowerFurnace extends BlockContainer {

    public static PropertyDirection FACING = BlockHorizontal.FACING;
    private static boolean keepInventory;
    private boolean lit;                 // will be used for particle spawning

    public TowerFurnace(boolean varlit) {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        lit = varlit;
        setHardness(5.0F).setResistance(10.0F);
        if(lit) {
            lightValue = 15;
            setRegistryName("twilight_reactor_lit");
            setUnlocalizedName("twilight_reactor_lit");
            return;
        }
        setRegistryName("twilight_reactor");
        setUnlocalizedName("twilight_reactor");
        setCreativeTab(vint.magicTab);
    }


    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileTower)
            playerIn.openGui(vint.instance, 1,worldIn,pos.getX(),pos.getY(),pos.getZ());
        return true;
    }




    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;
        if (active) {
            worldIn.setBlockState(pos, BlockRegister.TOWER_FURNACE_LIT.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlockRegister.TOWER_FURNACE_LIT.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, BlockRegister.TOWER_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlockRegister.TOWER_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        keepInventory = false;
        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }


    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileTower)
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileTower)tileentity);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.HORIZONTALS[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing ef = state.getValue(FACING);
        for(int i=0; i<4;++i){
            if(EnumFacing.HORIZONTALS[i].equals(ef))
                return i;
        }
        return super.getMetaFromState(state);
    }

    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTower();
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockRegister.TOWER_FURNACE);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }


    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
        if (!lit) return;
        EnumFacing enumfacing = stateIn.getValue(FACING);
        EnumParticleTypes particleMain = EnumParticleTypes.CRIT_MAGIC;
        EnumParticleTypes particleSecond = EnumParticleTypes.REDSTONE;
        double d0 = pos.getX() + 0.5D;
        double d1 = pos.getY() + rand.nextDouble() * 12.0D / 16.0D;
        double d1_1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = pos.getZ() + 0.5D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        double mainSpeed = 0;
        if (rand.nextDouble() < 0.1D)
            worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);

        switch (enumfacing) {
            case WEST:
                worldIn.spawnParticle(particleMain, d0 - 0.52D, d1, d2 + d4, 0.0D, mainSpeed, 0.0D);
                worldIn.spawnParticle(particleSecond, d0 - 0.52D, d1_1, d2 + d4, 0.0D, mainSpeed, 0.0D);
                break;
            case EAST:
                worldIn.spawnParticle(particleMain, d0 + 0.52D, d1, d2 + d4, 0.0D, mainSpeed, 0.0D);
                worldIn.spawnParticle(particleSecond, d0 + 0.52D, d1_1, d2 + d4, 0.0D, mainSpeed, 0.0D);
                break;
            case NORTH:
                worldIn.spawnParticle(particleMain, d0 + d4, d1, d2 - 0.52D, 0.0D, mainSpeed, 0.0D);
                worldIn.spawnParticle(particleSecond, d0 + d4, d1_1, d2 - 0.52D, 0.0D, mainSpeed, 0.0D);
                break;
            case SOUTH:
                worldIn.spawnParticle(particleMain, d0 + d4, d1, d2 + 0.52D, 0.0D, mainSpeed, 0.0D);
                worldIn.spawnParticle(particleSecond, d0 + d4, d1_1, d2 + 0.52D, 0.0D, mainSpeed, 0.0D);
        }

    }
}
