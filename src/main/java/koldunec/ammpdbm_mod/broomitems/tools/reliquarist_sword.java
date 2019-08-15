package koldunec.ammpdbm_mod.broomitems.tools;

import net.minecraft.client.resources.I18n;
import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import teamroots.embers.entity.EntityAncientGolem;
import thaumcraft.common.items.consumables.ItemAlumentum;

import javax.annotation.Nullable;
import java.util.List;


public class reliquarist_sword extends ItemSword {
    public reliquarist_sword(Item.ToolMaterial r){
        super(r);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setMaxStackSize(1);
        this.setRegistryName("ambersword");
        this.setUnlocalizedName("ambersword");
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if(ammpdbm_mod.isLoadedThaumcraft)
            if(repair.getItem().getClass().equals(ItemAlumentum.class)) return true;
        return false;
    }

    public static boolean countReliqueDrop(ItemStack me, int baseValue){
        int ll = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING,me);
        ll++;
        if(me.getItem() instanceof reliquarist_sword){
            ll+=((reliquarist_sword)me.getItem()).getLevel();
        }
        return ammpdbm_mod.random.nextInt(baseValue)<=ll;
    }

    public int getLevel(){
        return 0;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem().equals(ItemRegister.RELIQUARISTS_SWORD) &&
                net.minecraftforge.fml.common.Loader.isModLoaded("embers"))
            tooltip.add(TextFormatting.GOLD+I18n.format("ammpdbm_mod:flavor2"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
