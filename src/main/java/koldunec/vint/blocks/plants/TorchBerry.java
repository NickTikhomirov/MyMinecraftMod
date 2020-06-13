package koldunec.vint.blocks.plants;

import koldunec.vint.init.IntegrationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

import java.util.Random;

public class TorchBerry extends BlockCrops {

    public static final PropertyInteger Age = PropertyInteger.create("age1", 0, 3);

    public TorchBerry(){
        super();
        setUnlocalizedName("torch_crop");
        setRegistryName("torch_crop");
        lightValue=8;
    }

    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {
            new AxisAlignedBB(0.375D, 0.6875D, 0.375D, 0.625D, 1.0D, 0.625D),    //state 1
            new AxisAlignedBB(0.25D, 0.5625D, 0.25D, 0.625D, 1.0D, 0.625D),      //state 2
            new AxisAlignedBB(0.25D, 0.5625D, 0.25D, 0.625D, 1.0D, 0.625D),      //state 3
            new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.625D, 1.0D, 0.625D)       //state 4
    };

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        int age = this.getAge(state);
        if (age >= this.getMaxAge())
        return;
        IBlockState soilstate = worldIn.getBlockState(pos.up());
        Block soil = soilstate.getBlock();
        int chance = 6;
        if(soil.equals(Blocks.DIRT))
            if(soilstate.getValue(BlockDirt.VARIANT).equals(BlockDirt.DirtType.PODZOL))
                chance = 3;
        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(chance) == 0)) {
            worldIn.setBlockState(pos, this.withAge(age + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
        }
    }

    public static int checkSoil(IBlockState state){
        Block soil = state.getBlock();
        if(soil.equals(Blocks.DIRT))
            return (state.getValue(BlockDirt.VARIANT).equals(BlockDirt.DirtType.PODZOL))?3:1;
        if(soil.equals(Blocks.GRASS))       return 1;
        if(soil.equals(Blocks.MYCELIUM))    return 1;
        if(soil.equals(Blocks.GRASS_PATH))  return 2;
        if(soil.equals(Blocks.SOUL_SAND))   return 2;
        if(soil.equals(Blocks.FARMLAND))    return 1;
        if(IntegrationHelper.isLoadedNatura)
            if(soil.getRegistryName().getResourcePath().equals("nether_tainted_soil"))
                                            return 1;
                                            return 0;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        drops.add(new ItemStack(TFItems.torchberries,1));
        if(getAge(state)>1)
            if(rand.nextBoolean())
                drops.add(new ItemStack(TFItems.torchberries,1));
        if(getAge(state)==3)
            drops.add(new ItemStack(TFItems.torchberries,1));
    }

    @Override
    public boolean canBlockStay(World w, BlockPos pos, IBlockState me) {
        IBlockState state = w.getBlockState(pos.up());
        return checkSoil(state)>0 || state.getBlock().canSustainPlant(state, w, pos.down(), EnumFacing.DOWN, this);
    }

    @Override
    protected Item getSeed() {
        return TFItems.torchberries;
    }

    @Override
    protected Item getCrop() {
        return TFItems.torchberries;
    }

    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess source, BlockPos position) {
        return CROPS_AABB[(blockState.getValue(this.getAgeProperty())).intValue()];
    }

    protected int getBonemealAgeIncrease(World world) {
        return MathHelper.getInt(world.rand, 1, 1);
    }

    @Override
    protected PropertyInteger getAgeProperty() {
        return Age;
    }

    @Override
    public int getMaxAge() {
        return 3;
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
}
