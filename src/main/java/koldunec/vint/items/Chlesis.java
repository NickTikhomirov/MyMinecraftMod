package koldunec.vint.items;

import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.vint;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import team.chisel.api.IChiselGuiType;
import team.chisel.api.IChiselItem;
import team.chisel.api.carving.ICarvingVariation;
import team.chisel.api.carving.IChiselMode;

@Optional.Interface(modid = "chisel", iface = "team.chisel.api.IChiselItem")
public class Chlesis extends SimpleItems.SimpleItem implements IChiselItem {

    public Chlesis(){
        super("chlesis",1);
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
