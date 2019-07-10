package koldunec.ammpdbm_mod.TF_TowerAddition;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.block.BlockTFTowerTranslucent;
import twilightforest.block.TFBlocks;
import twilightforest.client.ModelRegisterCallback;
import twilightforest.client.ModelUtils;
import twilightforest.enums.TowerDeviceVariant;
import twilightforest.enums.TowerTranslucentVariant;
import twilightforest.item.TFItems;

import java.util.Random;

public class iron_reappearing extends Block implements ModelRegisterCallback
{
    public static final IProperty<TowerDeviceBroken_Variants> VARIANT = PropertyEnum.create("variant", TowerDeviceBroken_Variants.class);
    public iron_reappearing(String name)
    {
        super(Material.WOOD, MapColor.SAND);
        this.setHardness(10.0F);
        this.setResistance(35.0F);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(TFItems.creativeTab);
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, TowerDeviceBroken_Variants.REAPPEARINGb_INACTIVE));
    }
    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((TowerDeviceBroken_Variants)state.getValue(VARIANT)).ordinal();
    }

    @Override
    public int tickRate(World world) {
        return 15;
    }


    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TowerDeviceBroken_Variants variant = (TowerDeviceBroken_Variants)state.getValue(VARIANT);
           if(variant==TowerDeviceBroken_Variants.REAPPEARINGb_INACTIVE){
                world.playSound((EntityPlayer)null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 1.0F, 0.3F);
                changeToActiveVanishBlock(world, pos, TowerDeviceVariant.REAPPEARING_ACTIVE);
                return true;
           } else return false;
    }

    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        return super.getExplosionResistance(world, pos, exploder, explosion);
    }


    private static void changeToBlockState(World world, BlockPos pos, IBlockState state) {
        Block thereBlockID = world.getBlockState(pos).getBlock();
        if (thereBlockID == TFBlocks.tower_device || thereBlockID == TFBlocks.tower_translucent) {
            world.setBlockState(pos, state, 3);
            world.markBlockRangeForRenderUpdate(pos, pos);
            world.notifyNeighborsRespectDebug(pos, thereBlockID, false);
        }

    }

    public void func_176213_c(World world, BlockPos pos, IBlockState state) {
        if (!world.field_72995_K && state.func_177229_b(VARIANT) == TowerDeviceVariant.BUILDER_INACTIVE && world.func_175687_A(pos) > 0) {
            changeToBlockState(world, pos, state.func_177226_a(VARIANT, TowerDeviceVariant.BUILDER_ACTIVE));
            world.func_184148_a((EntityPlayer)null, (double)pos.func_177958_n() + 0.5D, (double)pos.func_177956_o() + 0.5D, (double)pos.func_177952_p() + 0.5D, SoundEvents.field_187885_gS, SoundCategory.BLOCKS, 0.3F, 0.6F);
        }

    }


    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random) {
        if (!world.field_72995_K) {
            TowerDeviceVariant variant = (TowerDeviceVariant)state.func_177229_b(VARIANT);
            EnumFacing[] var6;
            int var7;
            int var8;
            EnumFacing e;
            if (variant == TowerDeviceVariant.VANISH_ACTIVE || variant == TowerDeviceVariant.REAPPEARING_ACTIVE) {
                if (variant == TowerDeviceVariant.VANISH_ACTIVE) {
                    world.func_175698_g(pos);
                } else {
                    world.func_175656_a(pos, TFBlocks.tower_translucent.func_176223_P().func_177226_a(BlockTFTowerTranslucent.VARIANT, TowerTranslucentVariant.REAPPEARING_INACTIVE));
                    world.func_175684_a(pos, TFBlocks.tower_translucent, 80);
                }

                world.func_175722_b(pos, this, false);
                world.func_184148_a((EntityPlayer)null, (double)pos.func_177958_n() + 0.5D, (double)pos.func_177956_o() + 0.5D, (double)pos.func_177952_p() + 0.5D, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.3F, 0.5F);
                var6 = EnumFacing.field_82609_l;
                var7 = var6.length;

                for(var8 = 0; var8 < var7; ++var8) {
                    e = var6[var8];
                    checkAndActivateVanishBlock(world, pos.func_177972_a(e));
                }
            }

            if (variant == TowerDeviceVariant.BUILDER_ACTIVE && world.func_175687_A(pos) > 0) {
                this.letsBuild(world, pos);
            }

            if (variant == TowerDeviceVariant.BUILDER_INACTIVE || variant == TowerDeviceVariant.BUILDER_TIMEOUT) {
                var6 = EnumFacing.field_82609_l;
                var7 = var6.length;

                for(var8 = 0; var8 < var7; ++var8) {
                    e = var6[var8];
                    checkAndActivateVanishBlock(world, pos.func_177972_a(e));
                }
            }
        }

    }


    @SideOnly(Side.CLIENT)
    public void func_180655_c(IBlockState state, World world, BlockPos pos, Random random) {
        TowerDeviceVariant variant = (TowerDeviceVariant)state.func_177229_b(VARIANT);
        if (variant == TowerDeviceVariant.VANISH_ACTIVE || variant == TowerDeviceVariant.REAPPEARING_ACTIVE || variant == TowerDeviceVariant.BUILDER_ACTIVE) {
            this.sparkle(world, pos);
        }

    }

    public void sparkle(World worldIn, BlockPos pos) {
        Random random = worldIn.field_73012_v;
        double d0 = 0.0625D;

        for(int i = 0; i < 6; ++i) {
            double d1 = (double)((float)pos.func_177958_n() + random.nextFloat());
            double d2 = (double)((float)pos.func_177956_o() + random.nextFloat());
            double d3 = (double)((float)pos.func_177952_p() + random.nextFloat());
            if (i == 0 && !worldIn.func_180495_p(pos.func_177984_a()).func_185914_p()) {
                d2 = (double)pos.func_177956_o() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.func_180495_p(pos.func_177977_b()).func_185914_p()) {
                d2 = (double)pos.func_177956_o() - d0;
            }

            if (i == 2 && !worldIn.func_180495_p(pos.func_177968_d()).func_185914_p()) {
                d3 = (double)pos.func_177952_p() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.func_180495_p(pos.func_177978_c()).func_185914_p()) {
                d3 = (double)pos.func_177952_p() - d0;
            }

            if (i == 4 && !worldIn.func_180495_p(pos.func_177974_f()).func_185914_p()) {
                d1 = (double)pos.func_177958_n() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.func_180495_p(pos.func_177976_e()).func_185914_p()) {
                d1 = (double)pos.func_177958_n() - d0;
            }

            if (d1 < (double)pos.func_177958_n() || d1 > (double)(pos.func_177958_n() + 1) || d2 < 0.0D || d2 > (double)(pos.func_177956_o() + 1) || d3 < (double)pos.func_177952_p() || d3 > (double)(pos.func_177952_p() + 1)) {
                worldIn.func_175688_a(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

    }

    public static void checkAndActivateVanishBlock(World world, BlockPos pos) {
        IBlockState state = world.func_180495_p(pos);
        Block thereID = state.func_177230_c();
        if (thereID == TFBlocks.tower_device && (state.func_177229_b(VARIANT) == TowerDeviceVariant.VANISH_INACTIVE || state.func_177229_b(VARIANT) == TowerDeviceVariant.VANISH_UNLOCKED) && !areNearbyLockBlocks(world, pos)) {
            changeToActiveVanishBlock(world, pos, TowerDeviceVariant.VANISH_ACTIVE);
        } else if (thereID == TFBlocks.tower_device && state.func_177229_b(VARIANT) == TowerDeviceVariant.REAPPEARING_INACTIVE && !areNearbyLockBlocks(world, pos)) {
            changeToActiveVanishBlock(world, pos, TowerDeviceVariant.REAPPEARING_ACTIVE);
        } else if (thereID == TFBlocks.tower_translucent && state.func_177229_b(BlockTFTowerTranslucent.VARIANT) == TowerTranslucentVariant.BUILT_INACTIVE) {
            changeToActiveVanishBlock(world, pos, TowerTranslucentVariant.BUILT_ACTIVE);
        }

    }

    public static void changeToActiveVanishBlock(World world, BlockPos pos, TowerDeviceVariant variant) {
        changeToActiveVanishBlock(world, pos, TFBlocks.tower_device.func_176223_P().func_177226_a(VARIANT, variant));
    }

    public static void changeToActiveVanishBlock(World world, BlockPos pos, TowerTranslucentVariant variant) {
        changeToActiveVanishBlock(world, pos, TFBlocks.tower_translucent.func_176223_P().func_177226_a(BlockTFTowerTranslucent.VARIANT, variant));
    }

    private static void changeToActiveVanishBlock(World world, BlockPos pos, IBlockState state) {
        changeToBlockState(world, pos, state);
        world.func_184148_a((EntityPlayer)null, (double)pos.func_177958_n() + 0.5D, (double)pos.func_177956_o() + 0.5D, (double)pos.func_177952_p() + 0.5D, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.3F, 0.6F);
        world.func_175684_a(pos, state.func_177230_c(), getTickRateFor(state, world.field_73012_v));
    }

    private static int getTickRateFor(IBlockState state, Random rand) {
        if (state.func_177230_c() != TFBlocks.tower_device || state.func_177229_b(VARIANT) != TowerDeviceVariant.VANISH_ACTIVE && state.func_177229_b(VARIANT) != TowerDeviceVariant.REAPPEARING_ACTIVE) {
            return state.func_177230_c() == TFBlocks.tower_translucent && state.func_177229_b(BlockTFTowerTranslucent.VARIANT) == TowerTranslucentVariant.BUILT_ACTIVE ? 10 : 15;
        } else {
            return 2 + rand.nextInt(5);
        }
    }

    public int func_149750_m(IBlockState state) {
        return 5;
    }


    public Item func_180660_a(IBlockState state, Random random, int fortune) {
        switch((TowerDeviceVariant)state.func_177229_b(VARIANT)) {
            case ANTIBUILDER:
                return Items.field_190931_a;
            default:
                return Item.func_150898_a(this);
        }
    }

    protected boolean func_149700_E() {
        return false;
    }

    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }

    public int func_180651_a(IBlockState state) {
        state = state.func_177226_a(VARIANT, TowerDeviceVariant.REAPPEARING_INACTIVE);
        return this.func_176201_c(state);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel() {
        ModelUtils.registerToStateSingleVariant(this, VARIANT);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }
}