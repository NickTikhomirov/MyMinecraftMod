package koldunec.vint.items;

import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.utils.ParticleSpawner;
import koldunec.vint.vint;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.chisel.api.IChiselGuiType;
import team.chisel.api.IChiselItem;
import team.chisel.api.carving.ICarvingVariation;
import team.chisel.api.carving.IChiselMode;

public class Stealer extends base_item implements IChiselItem {

    public Stealer(){
        super("stealer",1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(vint.instance,-1,worldIn, handIn.ordinal(),0,0);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean canOpenGui(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
        return true;
    }

    @Override
    public IChiselGuiType getGuiType(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
        return IChiselGuiType.ChiselGuiType.NORMAL;
    }

    @Override
    public boolean onChisel(World world, EntityPlayer entityPlayer, ItemStack itemStack, ICarvingVariation iCarvingVariation) {
        return false;
    }

    @Override
    public boolean canChisel(World world, EntityPlayer entityPlayer, ItemStack itemStack, ICarvingVariation iCarvingVariation) {
        return !itemStack.isEmpty();
    }

    @Override
    public boolean canChiselBlock(World world, EntityPlayer entityPlayer, EnumHand enumHand, BlockPos blockPos, IBlockState iBlockState) {
        return false;
    }

    @Override
    public boolean supportsMode(EntityPlayer entityPlayer, ItemStack itemStack, IChiselMode iChiselMode) {
        return false;
    }
}
