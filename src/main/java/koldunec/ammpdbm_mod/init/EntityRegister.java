package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.entities.entityBitcoin;
import koldunec.ammpdbm_mod.entities.entityMagicBall;
import koldunec.ammpdbm_mod.entities.entityStone;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegister {
    public static void register(){
        EntityRegistry.registerModEntity(new ResourceLocation("ammpdbm_mod", "magic_ball"), entityMagicBall.class, "ammpdbm:magic_ball", 0, ammpdbm_mod.instance, 64, 20, true);
        EntityRegistry.registerModEntity(new ResourceLocation("ammpdbm_mod", "round_stone"), entityStone.class, "ammpdbm:round_stone", 1, ammpdbm_mod.instance, 64, 20, true);
        EntityRegistry.registerModEntity(new ResourceLocation("ammpdbm_mod", "bitcoin5000"), entityBitcoin.class, "ammpdbm:bitcoin5000", 2, ammpdbm_mod.instance, 64, 20, true);
    }
}
