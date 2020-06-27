package koldunec.vint.items.tools;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.vint;
import net.minecraft.client.resources.I18n;
import koldunec.vint.init.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import thaumcraft.common.items.consumables.ItemAlumentum;

import javax.annotation.Nullable;
import java.util.List;


public class reliquarist_sword extends ItemSword {
    public reliquarist_sword(Item.ToolMaterial r, String name){
        super(r);
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(1);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if(IntegrationHelper.isLoadedThaumcraft)
            if(repair.getItem().getClass().equals(ItemAlumentum.class)) return true;
        return false;
    }

    public static boolean countReliqueDrop(ItemStack me, int baseValue){
        int ll = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING,me);
        ll+=2;
        if(me.getItem() instanceof reliquarist_sword){
            ll+=((reliquarist_sword)me.getItem()).getLevel();
        }
        return vint.random.nextInt(baseValue)<=ll;
    }

    public int getLevel(){
        if(this.equals(ItemRegister.DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD))
            return 2;
        return 0;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem().equals(ItemRegister.DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD)){
            tooltip.add(TextFormatting.YELLOW+I18n.format("vint:flavor3"));
            tooltip.add(TextFormatting.AQUA+I18n.format("vint:flavor4"));
            return;
        }
        if(stack.getItem().equals(ItemRegister.RELIQUARISTS_SWORD) &&
                IntegrationHelper.isLoaded("embers"))
            tooltip.add(TextFormatting.GOLD+I18n.format("vint:flavor2"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
