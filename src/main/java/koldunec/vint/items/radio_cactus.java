package koldunec.vint.items;

import koldunec.vint.vint;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class radio_cactus extends BlockCactus {
    public radio_cactus(){
        super();
        setCreativeTab(vint.magicTab);
        lightValue=15;
        setRegistryName("radio_cactus");
        setUnlocalizedName("radio_cactus");
        setHardness(0.2F);
        setSoundType(SoundType.CLOTH);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Nether;
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        if(plantable.equals(this)) return true;
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if(entityIn instanceof EntityItem) return;
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
    }

    public static boolean canStay(World w, BlockPos b){
        if(!w.isAirBlock(b)) return false;
        if(!w.isAirBlock(b.north())) return false;
        if(!w.isAirBlock(b.south())) return false;
        if(!w.isAirBlock(b.east())) return false;
        if(!w.isAirBlock(b.west())) return false;
        return true;
    }
}
