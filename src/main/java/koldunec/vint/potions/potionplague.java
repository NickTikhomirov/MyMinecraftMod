package koldunec.vint.potions;


import koldunec.vint.vint;
import koldunec.vint.init.PotionRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

import static koldunec.vint.init.PotionRegister.HUMANITY;
import static koldunec.vint.init.PotionRegister.PLAGUE;

public class potionplague extends potion_base {

    private final ResourceLocation sprite = new ResourceLocation(vint.MODID, "textures/gui/effectacid.png");

    public potionplague(int liquidColorIn) {
        super("plague",false, liquidColorIn);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier) {
        if(entityLivingBase.isBurning())
            entityLivingBase.removeActivePotionEffect(this);
        if(!entityLivingBase.isPotionActive(PotionRegister.POISONPROTECTION))
            entityLivingBase.attackEntityFrom(DamageSource.GENERIC,1+amplifier);
        if(!entityLivingBase.isEntityAlive()) return;
        if(!entityLivingBase.getEntityWorld().isRemote && vint.random.nextBoolean()) {
            double x = entityLivingBase.posX;
            double y = entityLivingBase.posY;
            double z = entityLivingBase.posZ;
            PotionEffect hum = entityLivingBase.getActivePotionEffect(HUMANITY);
            int dur = entityLivingBase.getActivePotionEffect(PLAGUE).getDuration();

            List<Entity> l = entityLivingBase.getEntityWorld().getEntitiesWithinAABBExcludingEntity(
                    entityLivingBase, new AxisAlignedBB(x - 3, y - 2, z - 3, x + 3, y + 2, z + 2));
            if(l.size()<1) return;
            Entity a = l.get(vint.random.nextInt(l.size()));
            if(!(a instanceof EntityLivingBase)) return;
            //for(Entity a: l){
                //if(a instanceof EntityLivingBase){
                    if(!((EntityLivingBase) a).isPotionActive(PLAGUE) &&
                            (hum==null || !(a instanceof EntityPlayer))){
                        ((EntityLivingBase) a).addPotionEffect(new PotionEffect(PLAGUE,dur-1,amplifier));
                        if(hum!=null) ((EntityLivingBase) a).addPotionEffect(hum);
                    }
                //}
            //}
        }
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
