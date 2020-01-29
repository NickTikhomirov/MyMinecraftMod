package koldunec.vint.world.structures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public interface ISkyIsle {
    int getX();
    int getY();
    int getZ();
    int getBound();
    ResourceLocation getLocation();

    void fill(BlockPos b, String s, World w, Random r);
}
