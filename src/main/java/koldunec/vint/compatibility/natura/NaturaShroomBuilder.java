package koldunec.vint.compatibility.natura;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves2;
import com.progwml6.natura.nether.block.logs.BlockNetherLog;
import com.progwml6.natura.world.worldgen.glowshroom.BaseGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.glowshroom.nether.BlueGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.glowshroom.nether.GreenGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.glowshroom.nether.PurpleGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.trees.BaseTreeGenerator;
import com.progwml6.natura.world.worldgen.trees.nether.Corrector;   // well actually this one is mine
import com.progwml6.natura.world.worldgen.trees.nether.FusewoodTreeGenerator;
import com.progwml6.natura.world.worldgen.trees.nether.GhostwoodTreeGenerator;
import koldunec.vint.vint;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NaturaShroomBuilder {

    public static PurpleGlowshroomGenerator GEN_PURP = new PurpleGlowshroomGenerator(NaturaNether.netherLargePurpleGlowshroom.getDefaultState());
    public static GreenGlowshroomGenerator GEN_GREEN = new GreenGlowshroomGenerator(NaturaNether.netherLargeGreenGlowshroom.getDefaultState());
    public static BlueGlowshroomGenerator GEN_BLUE = new BlueGlowshroomGenerator(NaturaNether.netherLargeBlueGlowshroom.getDefaultState());
    public static ProperDarkwoodGen darkTreeGen;
    public static FusewoodTreeGenerator fuseTreeGen;
    public static GhostwoodTreeGenerator ghostTreeGen;

    public static void init(){
        IBlockState netherLog = NaturaNether.netherLog.getDefaultState();
        IBlockState netherLeaves = NaturaNether.netherLeaves.getDefaultState();
        IBlockState netherLeaves2 = NaturaNether.netherLeaves2.getDefaultState();
        NaturaShroomBuilder.darkTreeGen = new ProperDarkwoodGen(
                        3,
                        netherLog.withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.DARKWOOD),
                        netherLeaves2.withProperty(BlockNetherLeaves2.TYPE, BlockNetherLeaves2.LeavesType.DARKWOOD),
                        netherLeaves2.withProperty(BlockNetherLeaves2.TYPE, BlockNetherLeaves2.LeavesType.DARKWOOD_FLOWERING),
                        netherLeaves2.withProperty(BlockNetherLeaves2.TYPE, BlockNetherLeaves2.LeavesType.DARKWOOD_FRUIT));
        NaturaShroomBuilder.fuseTreeGen = new FusewoodTreeGenerator(
                3,
                netherLog.withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.FUSEWOOD),
                netherLeaves.withProperty(BlockNetherLeaves.TYPE, BlockNetherLeaves.LeavesType.FUSEWOOD));
        NaturaShroomBuilder.ghostTreeGen = Corrector.act();
    }



    public static IBlockState LieToNatura(World w, BlockPos where){
        IBlockState ground = w.getBlockState(where);
        w.setBlockState(where, NaturaNether.netherTaintedSoil.getDefaultState());
        return ground;
    }

    // we do need to replace ground, because Natura checks for certain blocks
    // and then we are allowed to switch ground back
    public static void BuildShroom(World w, BlockPos firstBlock, BaseGlowshroomGenerator generator){
        IBlockState ground = LieToNatura(w, firstBlock.down());
        generator.generateShroom(w.rand,w,firstBlock);
        w.setBlockState(firstBlock.down(),ground);
    }


    public static void BuildTree(World w, BlockPos first, BaseTreeGenerator gen){
        if(gen instanceof GhostwoodTreeGenerator) {
            int x = ((first.getX()>>4)<<4) + 5 + w.rand.nextInt(6);
            int z = ((first.getZ()>>4)<<4) + 5 + w.rand.nextInt(6);
            first = new BlockPos(x, w.getHeight(x, z), z);
        }
        IBlockState ground = LieToNatura(w, first.down());
        gen.generateTree(vint.random, w, first);
        w.setBlockState(first.down(), ground);
    }
}
