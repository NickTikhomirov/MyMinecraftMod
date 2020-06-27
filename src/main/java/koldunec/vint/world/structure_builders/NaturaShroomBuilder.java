package koldunec.vint.world.structure_builders;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.world.worldgen.glowshroom.BaseGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.glowshroom.nether.BlueGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.glowshroom.nether.GreenGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.glowshroom.nether.PurpleGlowshroomGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NaturaShroomBuilder {

    public static PurpleGlowshroomGenerator GEN_PURP = new PurpleGlowshroomGenerator(NaturaNether.netherLargePurpleGlowshroom.getDefaultState());
    public static GreenGlowshroomGenerator GEN_GREEN = new GreenGlowshroomGenerator(NaturaNether.netherLargeGreenGlowshroom.getDefaultState());
    public static BlueGlowshroomGenerator GEN_BLUE = new BlueGlowshroomGenerator(NaturaNether.netherLargeBlueGlowshroom.getDefaultState());


    // we do need to replace ground, because Natura checks for certain blocks
    // and then we are allowed to switch ground back
    public static void BuildShroom(World w, BlockPos firstBlock, BaseGlowshroomGenerator generator){
        BlockPos ground_pos = firstBlock.down();
        IBlockState ground = w.getBlockState(ground_pos);
        w.setBlockState(ground_pos,NaturaNether.netherTaintedSoil.getDefaultState());
        generator.generateShroom(w.rand,w,firstBlock);
        w.setBlockState(ground_pos,ground);
    }



    // parts of world shroom generator code

    //int y = ch.getHeightValue(x,z)-1;
    //Block base = ch.getBlockState(x,y,z).getBlock();
    //if(base.equals(BlockRegister.RED_NYLIUM)) {
    //    ch.setBlockState(new BlockPos(x,y,z),NaturaNether.netherTaintedSoil.getDefaultState());
    //    x+=chunkX<<4;
    //    z+=chunkZ<<4;
    //    purp.generateShroom(random, world, new BlockPos(x, y + 1, z));
    //} else if(base.equals(BlockRegister.BLUE_NYLIUM) && y<134){
    //}
}
