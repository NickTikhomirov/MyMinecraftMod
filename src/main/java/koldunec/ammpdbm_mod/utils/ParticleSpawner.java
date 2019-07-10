package koldunec.ammpdbm_mod.utils;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.RandomValueRange;

import java.util.Random;

public class ParticleSpawner {

    static public void cloud(
            EnumParticleTypes kind,
            World w,
            int amount,
            double x,
            double y,
            double z,
            double amplX,
            double amplY,
            double amplZ,
            double speedX,
            double speedY,
            double speedZ,
            double multiplier){
        if(w==null) return;
        if(amount<1) return;
        if(!w.isRemote) return;
        for(int i=0; i<amount;i++){
            w.spawnParticle(
                    kind,
                    delta(x,amplX,w.rand),
                    delta(y,amplY,w.rand),
                    delta(z,amplZ,w.rand),
                    speedX*multiplier,
                    speedY*multiplier,
                    speedZ*multiplier);
        }
    }

    static public double delta(double d, double a,Random r){
        return d+a*r.nextDouble();
    }

}
