package koldunec.ammpdbm_mod.potions;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;


public class potionmagicprotection extends potion_base {

    private final ResourceLocation sprite = new ResourceLocation(ammpdbm_mod.MODID, "textures/gui/effectmagicprotection.png");

    public potionmagicprotection(int liquidColorIn) {
        super("magicprotection",false, liquidColorIn);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
    }

    @Override
    public boolean isReady(int duration, int amplifier) { return false;  }

    @Override
    public ResourceLocation getSprite() {
        return sprite;
    }
}
