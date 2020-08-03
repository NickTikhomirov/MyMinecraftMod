package koldunec.vint.blocks;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.objectbuilders.ObjectBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class FertileBlock extends ObjectBuilder.SimpleBlock {
    public static Block BuildDirt(){ return new FertileBlock("fertile_dirt", Material.GROUND, SoundType.GROUND); }
    public static Block BuildSand(){ return new FertileBlock("fertile_sand", Material.SAND, SoundType.SAND); }
    private FertileBlock(String name, Material blockMaterialIn, SoundType soundType) { super(name, blockMaterialIn, soundType); }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        if(plantable.equals(Items.PUMPKIN_SEEDS) || plantable.equals(Items.MELON_SEEDS) || plantable.equals(ItemRegister.VITASARIA_SEEDS))
            return this.equals(BlockRegister.FERTILE_DIRT);
        if(!(plantable instanceof Block))
            return false;
        Block plant = (Block) plantable;
        if(plant.getRegistryName().getResourcePath().equals("bamboo"))
            return true;
        if(plant.equals(Blocks.REEDS))
            return true;
        if(plant.equals(Blocks.CACTUS))
            return this.equals(BlockRegister.FERTILE_SAND);
        if(plant.equals(Blocks.COCOA))
            return this.equals(BlockRegister.FERTILE_DIRT);
        if(plant.equals(Blocks.PUMPKIN_STEM) || plant.equals(Blocks.MELON_BLOCK))
            return this.equals(BlockRegister.FERTILE_DIRT);
        if(plant.equals(BlockRegister.CURING_CROPS))
            return this.equals(BlockRegister.FERTILE_DIRT);
        return false;
    }

    @Override
    public boolean isFertile(World world, BlockPos pos) { return true; }
}
