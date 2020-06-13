package koldunec.vint.compatibility;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;


public class Tinkerfluid extends Fluid {
    public static ResourceLocation still = new ResourceLocation("tconstruct:blocks/fluids/molten_metal");
    public static ResourceLocation flowing = new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow");


    public Tinkerfluid(String fluidName, int color) {
        super(fluidName, still, flowing, color);
    }
}
