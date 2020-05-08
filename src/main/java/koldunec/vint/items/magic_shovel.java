package koldunec.vint.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;


@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class magic_shovel extends base_item implements IBauble{
    public magic_shovel(){
        super("shovel_plus_plus",1);
        this.setMaxDamage(256);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    //called in events section
    public static boolean isEquiped(EntityPlayer e){
        if(e.getEntityWorld().isRemote) return false;
        if (IntegrationHelper.isLoadedBaubles) {
            if(e.getHeldItemMainhand().getItem() instanceof ItemSpade) return false;
            if(BaublesApi.isBaubleEquipped(e, ItemRegister.SHOVEL_AMULET) != -1) return true;
        }
        return false;
    }

    //called in events section
    public static void damageShovel(EntityPlayer e, int value){
        if(!isEquiped(e)) return;
        //they say not to modify itemstack we ge from getStackInSlot so lets use more complicated way
        IBaublesItemHandler i = BaublesApi.getBaublesHandler(e);
        int j = BaublesApi.isBaubleEquipped(e, ItemRegister.SHOVEL_AMULET);
        ItemStack s = i.getStackInSlot(j).copy();
        s.setItemDamage(s.getItemDamage()+value);
        i.setStackInSlot(j,s);
    }

    //called in events section
    public static void attemptToFix(EntityPlayer e){
        if(e.getHeldItemMainhand().isItemStackDamageable()){
            if(e.getHeldItemMainhand().getItem() instanceof ItemTool){
                e.getHeldItemMainhand().setItemDamage(e.getHeldItemMainhand().getItemDamage()-1);
            }
        }
    }

    //called in events section
    public static boolean isHarvestable(Block b){
        return b.equals(Blocks.DIRT) || b.equals(Blocks.GRAVEL) || b.equals(Blocks.SOUL_SAND) || b.equals(BlockRegister.ORE_RAINBOW);
    }


    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }
}
