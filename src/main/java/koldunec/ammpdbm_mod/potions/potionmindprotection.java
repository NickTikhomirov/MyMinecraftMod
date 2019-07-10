package koldunec.ammpdbm_mod.potions;


import koldunec.ammpdbm_mod.ammpdbm_mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Iterator;

public class potionmindprotection extends Potion {

    private final ResourceLocation sprite = new ResourceLocation(ammpdbm_mod.MODID, "textures/gui/effectmindprotection.png");

    public potionmindprotection(String name, boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        this.setName(name);
    }

    public void setName(String potionName) {

        this.setRegistryName(ammpdbm_mod.MODID, potionName);
        this.setPotionName("effect." + this.getRegistryName().toString());
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        Iterator<PotionEffect> ite = entityLivingBase.getActivePotionEffects().iterator();
        while(ite.hasNext()){
            PotionEffect g = ite.next();
            Boolean k = false;
            if(g.getPotion() == Potion.getPotionById(9)) k = true;  //nausea
            if(g.getPotion() == Potion.getPotionById(15)) k = true; //blindness
            if(amplifier>0){
                if(g.getPotion() == Potion.getPotionById(2)) k = true;  //slowness
                if(g.getPotion() == Potion.getPotionById(4)) k = true;  //fatigue
                if(g.getPotion() == Potion.getPotionById(18)) k = true; //weakness
            }
            if(k) entityLivingBase.removePotionEffect(g.getPotion());
        }
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
