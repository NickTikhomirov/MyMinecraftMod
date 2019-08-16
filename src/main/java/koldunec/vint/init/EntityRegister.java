package koldunec.vint.init;

import koldunec.vint.vint;
import koldunec.vint.entities.entityBitcoin;
import koldunec.vint.entities.entityMagicBall;
import koldunec.vint.entities.entityStone;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegister {
    public static void register(){
        EntityRegistry.registerModEntity(new ResourceLocation("vint", "magic_ball"), entityMagicBall.class, "vint:magic_ball", 0, vint.instance, 64, 20, true);
        EntityRegistry.registerModEntity(new ResourceLocation("vint", "round_stone"), entityStone.class, "vint:round_stone", 1, vint.instance, 64, 20, true);
        EntityRegistry.registerModEntity(new ResourceLocation("vint", "bitcoin5000"), entityBitcoin.class, "vint:bitcoin5000", 2, vint.instance, 64, 20, true);
    }
}
