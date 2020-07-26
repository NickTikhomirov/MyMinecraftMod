package koldunec.vint.compatibility.Tinker.traits_armour;

import c4.conarm.common.armor.utils.ArmorHelper;
import c4.conarm.lib.armor.ArmorCore;
import c4.conarm.lib.traits.AbstractArmorTrait;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.Tinker.ConarmIntegration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.HashSet;

@Mod.EventBusSubscriber
public class Experimental extends AbstractArmorTrait {
    static HashSet<Item> foods = new HashSet<Item>(){{
        add(Items.PORKCHOP);
        add(Items.COOKED_PORKCHOP);
        add(Items.BEEF);
        add(Items.COOKED_BEEF);
        add(Items.CHICKEN);
        add(Items.COOKED_CHICKEN);
        add(Items.FISH);
        add(Items.COOKED_FISH);
        add(Items.MUTTON);
        add(Items.COOKED_MUTTON);
        add(Items.RABBIT);
        add(Items.COOKED_RABBIT);
    }};

    public Experimental() {
        super("experimental", ColorConstants.CARMINITE_COLOR);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish e){
        if(!(e.getEntityLiving() instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) e.getEntityLiving();
        if(ArmorHelper.getArmorAbilityLevel(player, ConarmIntegration.EXPERIMENTAL.identifier)<=0)
            return;
        if(foods.contains(e.getItem().getItem())){
            ItemFood food = (ItemFood)e.getItem().getItem();
            ItemStack stack = e.getItem();
            player.getFoodStats().addStats(food.getHealAmount(stack), food.getSaturationModifier(stack));
        }
    }
}
