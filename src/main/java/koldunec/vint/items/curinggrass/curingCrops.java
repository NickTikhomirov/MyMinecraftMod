package koldunec.vint.items.curinggrass;

import koldunec.vint.vint;
import koldunec.vint.init.ItemRegister;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.Random;

public class curingCrops extends BlockCrops {
    public static final PropertyInteger Age = PropertyInteger.create("age1", 0, 5);

    public curingCrops(String cropName) {
        this.setUnlocalizedName(cropName);
        this.setRegistryName(cropName);
    }

    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D),
    };

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        super.getDrops(drops, world, pos, state, fortune);
        if(getAge(state)>2)
            if(rand.nextBoolean())
                drops.add(new ItemStack(ItemRegister.CURINGSEEDS,1));
        if(getAge(state)==4 && rand.nextBoolean())
            drops.add(new ItemStack(ItemRegister.CURING_GRASS,1));
        if(getAge(state)==5)
            drops.add(new ItemStack(ItemRegister.CURING_GRASS,1+rand.nextInt(2)));
    }

    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess source, BlockPos position) {
        return CROPS_AABB[(blockState.getValue(this.getAgeProperty())).intValue()];
    }

    @Override
    protected Item getSeed() {
        return ItemRegister.CURINGSEEDS;
    }

    @Override
    protected Item getCrop() {
        return ItemRegister.CURING_GRASS;
    }

    protected int getBonemealAgeIncrease(World world) {
        return MathHelper.getInt(world.rand, 1, 1);
    }

    public EnumPlantType getPlantType(IBlockAccess world, BlockPos position) {
        return EnumPlantType.Crop;
    }

    @Override
    protected PropertyInteger getAgeProperty() {
        return Age;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return this.getAge(state);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {Age});
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(getAge(state)==5 && !worldIn.isRemote && !playerIn.isSneaking()){
            ItemStack a = new ItemStack(ItemRegister.CURING_GRASS,1+ vint.random.nextInt(2));
            if(!playerIn.inventory.addItemStackToInventory(a))
                playerIn.dropItem(a,false);
            if(vint.random.nextBoolean()){
                ItemStack b = new ItemStack(ItemRegister.CURINGSEEDS,1);
                if(!playerIn.inventory.addItemStackToInventory(b))
                    playerIn.dropItem(b,false);
            }
            worldIn.setBlockState(pos,this.withAge(1));
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
