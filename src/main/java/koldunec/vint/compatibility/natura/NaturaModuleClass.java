package koldunec.vint.compatibility.natura;

import com.progwml6.natura.world.worldgen.glowshroom.BaseGlowshroomGenerator;
import com.progwml6.natura.world.worldgen.trees.BaseTreeGenerator;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.world.structure_builders.TreeRouter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NaturaModuleClass {
    // 0  - green
    // 1  - blue
    // 2+ - purple
    public static void BuildShroom(int meta, World w, BlockPos pos){
        BaseGlowshroomGenerator gen = null;
        switch(meta){
            case 0:
                gen = NaturaShroomBuilder.GEN_GREEN;
                break;
            case 1:
                gen = NaturaShroomBuilder.GEN_BLUE;
                break;
            default:
                gen = NaturaShroomBuilder.GEN_PURP;
        }
        NaturaShroomBuilder.BuildShroom(w, pos, gen);
    }

    // 0  - ghost
    // 1  - dark
    // 2+ - fuse
    public static void BuildTree(int meta, World w, BlockPos pos){
        BaseTreeGenerator foliage = null;
        switch (meta){
            case 0:
                foliage = NaturaShroomBuilder.ghostTreeGen;
                break;
            case 1:
                foliage = NaturaShroomBuilder.darkTreeGen;
                break;
            default:
                foliage = NaturaShroomBuilder.fuseTreeGen;
        }
        NaturaShroomBuilder.BuildTree(w, pos, foliage);
    }


    public static TreeRouter.SingleTreeGen getGenerator(){
        return (World w, BlockPos b, Random random)-> {
            if(w.getBlockState(b.down()).getBlock().equals(BlockRegister.RED_NYLIUM))
                BuildTree(0, w, b);
            else
                BuildTree(1 + random.nextInt(2), w, b);
        };
    }
}
