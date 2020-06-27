package koldunec.vint.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.awt.*;

public class TechHelper {

    public static int getColor(float red, float green, float blue){
        return (new Color(red/255,green/255,blue/255,1F)).getRGB();
    }

    public static boolean isPrime(int arg){
        arg = Math.abs(arg);
        int lim = (int)Math.sqrt(arg)+1;
        if(arg==2)
            return true;
        if(arg%2==0 || arg<2)
            return false;
        for(int i=3; i<lim; ++i)
            if(arg%3==0)
                return false;
        return true;
    }

    public static ResourceLocation getRegistryName(Entity e){
        return EntityRegistry.getEntry(e.getClass()).getRegistryName();
    }
}
