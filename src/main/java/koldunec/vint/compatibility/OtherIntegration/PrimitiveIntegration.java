package koldunec.vint.compatibility.OtherIntegration;

import koldunec.vint.utils.UsefulSets;
import net.daveyx0.primitivemobs.entity.monster.EntityVoidEye;

public class PrimitiveIntegration {
    public static void initUsefulSets(){
        UsefulSets.ENDER_CREATURES.add(EntityVoidEye.class);
    }
}
