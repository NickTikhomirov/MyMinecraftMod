package koldunec.vint.items;

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
