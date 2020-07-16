package koldunec.vint.compatibility.Tinker.traits;

import com.google.common.collect.ImmutableList;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.shared.TinkerCommons;

import java.util.HashSet;
import java.util.List;

public class Deal extends AbstractTrait {
    public static HashSet<Item> FORBIDS = new HashSet<Item>(){{
       add(Items.ROTTEN_FLESH);
       add(Items.BONE);
       add(Items.STRING);
       add(Items.GUNPOWDER);
       add(Items.SLIME_BALL);
       add(Items.BEEF);
       add(Item.getItemFromBlock(Blocks.WOOL));
       add(Items.PORKCHOP);
       add(Items.MUTTON);
       add(Items.CHICKEN);
       add(Items.STONE_SWORD);
       add(Items.IRON_SWORD);
       add(Items.GOLDEN_SWORD);
       add(Items.IRON_SHOVEL);
       add(Items.COAL);
       add(Items.FEATHER);
    }};

    public Deal() {
        super("dmg_deal", ColorConstants.CACTUS_COLOR);
    }

    public static EntityItem forbid(EntityItem e){
        ItemStack stack = e.getItem();
        Item item = stack.getItem();
        if(item.equals(Items.DYE) && stack.getMetadata()==0)
            return null;
        if(item.equals(TinkerCommons.edibles) && stack.getMetadata()==1)
            return null;
        if(item.getRegistryName().toString().equals("actuallyadditions:item_solidified_experience"))
            return null;
        if(FORBIDS.contains(item))
            return null;
        return e;
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(damage!=0)
            return newDamage+7;
        return 0;
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if(target instanceof EntityPlayer)
            return;
        target.getEntityData().setBoolean("vint_deal_sacrificed", true);
    }

    @Override
    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
        String loc = String.format(LOC_Extra, getModifierIdentifier());
        return ImmutableList.of(I18n.format(loc));
    }
}
