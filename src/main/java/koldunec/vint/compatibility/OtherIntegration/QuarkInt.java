package koldunec.vint.compatibility.OtherIntegration;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vazkii.quark.world.block.BlockHugeGlowshroom;

public class QuarkInt {
    public static void ShroomBuilder(Chunk ch, int x, int y, int z){
        BlockHugeGlowshroom.setInPosition(
                ch.getWorld(),
                ch.getWorld().rand,
                new BlockPos((ch.x<<4)+x, y, (ch.z<<4)+z),
                true);
    }
}
