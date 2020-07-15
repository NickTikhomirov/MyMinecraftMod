package com.progwml6.natura.world.worldgen.trees.nether;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.nether.block.leaves.BlockNetherLeaves;
import com.progwml6.natura.nether.block.logs.BlockNetherLog;
import net.minecraft.block.state.IBlockState;

public class Corrector {
    public static GhostwoodTreeGenerator act(){
        IBlockState netherLog = NaturaNether.netherLog.getDefaultState();
        IBlockState netherLeaves = NaturaNether.netherLeaves.getDefaultState();
        GhostwoodTreeGenerator result = new GhostwoodTreeGenerator(
                netherLog.withProperty(BlockNetherLog.TYPE, BlockNetherLog.LogType.GHOSTWOOD),
                netherLeaves.withProperty(BlockNetherLeaves.TYPE, BlockNetherLeaves.LeavesType.GHOSTWOOD),
                true);
        result.heightLimit = 10;
        return result;
    }
}
