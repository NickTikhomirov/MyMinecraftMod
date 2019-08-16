package koldunec.vint.tileentities;


import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import koldunec.vint.ammpdbm_mod;

import javax.annotation.Nullable;
import java.util.Random;

public class TowerFurnace extends Block implements ITileEntityProvider {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool Active = PropertyBool.create("active");

    public TowerFurnace(float hardness, float hardness_expl){
        super(Material.WOOD);
        this.setRegistryName("towerfurnace");
        this.setUnlocalizedName("towerfurnace");
        this.setHardness(hardness);
        this.setResistance(hardness_expl);
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(Active, false));
    }

    @Override
    public boolean onBlockActivated(World w, BlockPos b, IBlockState s, EntityPlayer p, EnumHand h, EnumFacing f, float hitx, float hity, float hitz){
        if(!w.isRemote){
            p.openGui(ammpdbm_mod.MODID, 0, w, b.getX(),b.getY(), b.getZ());
        }
        return true;
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockRegister.TOWER_FURNACE);
    }

    @Override
    public void onBlockAdded(World w, BlockPos p, IBlockState s){
        if(!w.isRemote){
            IBlockState north = w.getBlockState(p.north());
            IBlockState west = w.getBlockState(p.west());
            IBlockState east = w.getBlockState(p.east());
            IBlockState south = w.getBlockState(p.south());
            EnumFacing face = (EnumFacing) s.getValue(FACING);

            if(face==EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if(face==EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if(face==EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            else if(face==EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            w.setBlockState(p, s.withProperty(FACING, face),2);

        }
    }

    public static void setState(boolean active, World w, BlockPos p){
        IBlockState state = w.getBlockState(p);
        TileEntity tileEntity = w.getTileEntity(p);

        if(active) w.setBlockState(p, BlockRegister.TOWER_FURNACE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(Active,true));
        else w.setBlockState(p, BlockRegister.TOWER_FURNACE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(Active,false));

        if(tileEntity!=null){
            tileEntity.validate();
            w.setTileEntity(p,tileEntity);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTowerFurnace();
    }
}
