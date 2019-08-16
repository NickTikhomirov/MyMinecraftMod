package koldunec.vint.potions;


import koldunec.vint.vint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class potion_base extends Potion {

    //private final ResourceLocation sprite = new ResourceLocation(vint.MODID, "textures/gui/effectstorage.png");

    public potion_base(String name, boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        this.setName(name);
    }

    public void setName(String potionName) {

        this.setRegistryName(vint.MODID, potionName);
        this.setPotionName("effect." + this.getRegistryName().toString());
    }

    public abstract ResourceLocation getSprite();

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return false;
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect potionEffect, Minecraft mc) {
        if (mc.currentScreen != null) {
            mc.getTextureManager().bindTexture(getSprite());
            Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect potionEffect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(getSprite());
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }
}
