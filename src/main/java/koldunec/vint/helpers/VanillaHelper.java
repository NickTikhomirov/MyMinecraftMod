package koldunec.vint.helpers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VanillaHelper {

    public static ItemStack getEggFor(ResourceLocation location){
        ItemStack result  = new ItemStack(Items.SPAWN_EGG);
        ItemMonsterPlacer.applyEntityIdToItemStack(result,location);
        return result;
    }
}
