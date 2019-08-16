package koldunec.vint.broomitems;

import koldunec.vint.vint;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.RandomValueRange;

import static net.minecraft.init.MobEffects.*;


public class nether_drink extends ItemFood {


    public nether_drink() {
        super(0,0,false);
        this.setRegistryName("netherdrink");
        this.setUnlocalizedName("netherdrink");
        this.setCreativeTab(CreativeTabs.BREWING);
        this.setMaxStackSize(1);
        this.setAlwaysEdible();
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    /*
    @Override
    public ItemStack onItemUseFinish(ItemStack i, World w, EntityLivingBase e){
        EntityPlayer entityplayer = e instanceof EntityPlayer ? (EntityPlayer)e : null;
        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode){
            i.shrink(1);
        }
        if (entityplayer instanceof EntityPlayerMP){
            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, i);
        }
        if (!w.isRemote){
            ItemStack a = new ItemStack(Items.GHAST_TEAR,6 + (new RandomValueRange(0,7)).generateInt(vint.random));
            e.addPotionEffect(new PotionEffect(NAUSEA, 1200));
            e.addPotionEffect(new PotionEffect(GLOWING, 9600));
            e.addPotionEffect(new PotionEffect(LEVITATION, 600));
            e.addPotionEffect(new PotionEffect(JUMP_BOOST,640,128));
            if(!((EntityPlayer)e).inventory.addItemStackToInventory(a)){
                EntityItem o = new EntityItem(
                        w,
                        e.posX,
                        e.posY,
                        e.posZ,
                        a);
                w.spawnEntity(o);
            }
        }

        if (entityplayer != null){
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode){
            if (i.isEmpty()){
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (entityplayer != null){
                entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
        return i;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 128;
    }
    */

    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer player){
        if(player.world.isRemote) return;
        ItemStack a = new ItemStack(Items.GHAST_TEAR,6 + (new RandomValueRange(0,7)).generateInt(vint.random));
        ItemStack b = new ItemStack(Items.GLASS_BOTTLE,1);
        player.addPotionEffect(new PotionEffect(NAUSEA, 1200));
        player.addPotionEffect(new PotionEffect(GLOWING, 9600));
        player.addPotionEffect(new PotionEffect(LEVITATION, 600));
        player.addPotionEffect(new PotionEffect(JUMP_BOOST,640,128));
        if(!player.inventory.addItemStackToInventory(a))
            player.dropItem(a,false);
        if(!player.inventory.addItemStackToInventory(b))
            player.dropItem(b,false);
    }
}
