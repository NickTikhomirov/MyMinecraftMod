package koldunec.vint.potions;


import koldunec.vint.vint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;


public class potionwitherprotection extends potion_base {

    private final ResourceLocation sprite = new ResourceLocation(vint.MODID, "textures/gui/effectwitherprotection.png");

    public potionwitherprotection(int liquidColorIn) {
        super("witherprotection",false, liquidColorIn);
    }


    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        entityLivingBase.removeActivePotionEffect(MobEffects.WITHER);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 20 == 0;
    }


    @Override
    public ResourceLocation getSprite() {
        return sprite;
    }
}
