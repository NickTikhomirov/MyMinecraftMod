package koldunec.ammpdbm_mod.potions;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.HashMap;


public class potionstorage extends Potion {
    private final ResourceLocation sprite = new ResourceLocation(ammpdbm_mod.MODID, "textures/gui/effectstorage.png");
    static public final HashMap<EntityLivingBase, EntityWolf> Contents = new HashMap<EntityLivingBase, EntityWolf>();

    public potionstorage(String name, boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        this.setName(name);
    }

    public void setName(String potionName) {
        this.setRegistryName(ammpdbm_mod.MODID, potionName);
        this.setPotionName("effect." + this.getRegistryName().toString());
    }

    @Override
    public void performEffect(EntityLivingBase e, int amplifier) {
        //effectstorage f = (effectstorage)e.getActivePotionEffect(effectstorage.EFFECT_S);
        //if(f!=null) f.performEffect(e);
        //e.addPotionEffect(new PotionEffect(MobEffects.SPEED,12,10));
        //Ахахаххаах, нет
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect potionEffect, Minecraft mc) {
        if (mc.currentScreen != null) {
            mc.getTextureManager().bindTexture(this.sprite);
            Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect potionEffect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(this.sprite);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }
}
