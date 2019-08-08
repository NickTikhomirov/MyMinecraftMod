package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class soul_shears extends ItemShears {
    public soul_shears(){
        this.setRegistryName("soul_shears");
        this.setUnlocalizedName("soul_shears");
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setContainerItem(this);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if(player!=null && entity instanceof EntityLivingBase){
            if(player.inventory.armorInventory.get(3).getItem().equals(Item.getItemFromBlock(Blocks.PUMPKIN))){
                EntityLivingBase e = ((EntityLivingBase) entity);
                if(e.getHealth()<10 && e.isNonBoss()
                    &&(e instanceof EntitySkeleton ||
                       e instanceof EntityVillager ||
                       e instanceof EntityPigZombie ||
                       e instanceof EntityGhast ||
                       e instanceof EntityWitch ||
                       e instanceof AbstractIllager)){
                    entity.setDropItemsWhenDead(false);
                    entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(player,player),10F);
                    ItemStack b = new ItemStack(ItemRegister.SOUL);
                    if(!player.inventory.addItemStackToInventory(b))
                        player.dropItem(b,false);
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    /*
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        boolean res = super.itemInteractionForEntity(itemstack, player, entity, hand);
        if(res){
            int i = player.getFoodStats().getFoodLevel();
            if(i<4){
                player.getFoodStats().setFoodLevel(0);
                player.attackEntityFrom(DamageSource.MAGIC,10F);
            } else player.getFoodStats().setFoodLevel(i-4);
        }
        return res;
    }
    */


    /*
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {

        boolean res = super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
        if(res && entityLiving instanceof EntityPlayer){
            int i = ((EntityPlayer)entityLiving).getFoodStats().getFoodLevel();
            if(i<4){
                ((EntityPlayer)entityLiving).getFoodStats().setFoodLevel(0);
                entityLiving.attackEntityFrom(DamageSource.MAGIC,10F);
            } else ((EntityPlayer)entityLiving).getFoodStats().setFoodLevel(i-4);
        }
        return res;
    }
    */
}
