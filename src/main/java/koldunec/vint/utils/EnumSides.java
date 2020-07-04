package koldunec.vint.utils;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;

public enum EnumSides {
    FRONT,
    LEFT,
    RIGHT,
    BACK;

    public static EnumSides getSideFor(EnumFacing front, EnumFacing another){
        if(front.equals(another))
            return FRONT;
        Vec3i main = front.getDirectionVec();
        Vec3i side = front.getDirectionVec();
        Vec3i product = main.crossProduct(side);
        if(product.equals(Vec3i.NULL_VECTOR))
            return BACK;
        if(product.equals(new Vec3i(0,1,0)))
            return LEFT;
        return RIGHT;
    }
}
