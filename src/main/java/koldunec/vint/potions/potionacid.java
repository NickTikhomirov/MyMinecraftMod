package koldunec.vint.potions;

import koldunec.vint.vint;
import koldunec.vint.init.PotionRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class potionacid extends potion_base {
    private final ResourceLocation sprite = new ResourceLocation(vint.MODID, "textures/gui/effecacid.png");

    public potionacid(int liquidColorIn) {
        super("acid",true, liquidColorIn);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean isBadEffect() {
        return false;
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        if(entityLivingBaseIn.isPotionActive(PotionRegister.POISONPROTECTION)) return;
        int j = (int)(health * (double)(6 << amplifier) + 0.5D);
        if (source == null){
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float)j);
        } else {
            entityLivingBaseIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(source, indirectSource), (float)j);
        }
        //super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if(entityLivingBase.isPotionActive(PotionRegister.HUMANITY)) return;
        entityLivingBase.attackEntityFrom(DamageSource.MAGIC, (float)(6 << amplifier));
    }

    @Override
    public boolean isReady(int duration, int amplifier) { return duration >= 1;}

    @Override
    public ResourceLocation getSprite() {
        return sprite;
    }
}
