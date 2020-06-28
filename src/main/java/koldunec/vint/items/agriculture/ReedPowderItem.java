package koldunec.vint.items.agriculture;

import koldunec.vint.items.gunpowder_reed.block_gunreed;
import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReedPowderItem extends ItemBlockSpecial implements IBehaviorDispenseItem {
    public ReedPowderItem(){
        super(BlockRegister.REED_GUNPOWDER);
        this.setRegistryName("gunreed");
        this.setUnlocalizedName("gunreed");
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(64);
        this.hasSubtypes=true;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + block_gunreed.reedTypes.intToType(stack.getMetadata()).getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == vint.magicTab){
            for (block_gunreed.reedTypes type : block_gunreed.reedTypes.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }


    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        int meta = stack.getMetadata();
        if(!(source.getBlockTileEntity() instanceof TileEntityDispenser)) {
            return stack;
        }
        stack.shrink(1);
        TileEntityDispenser dispenserTile = source.getBlockTileEntity();
        World w = dispenserTile.getWorld();
        BlockPos bp = dispenserTile.getPos();
        IBlockState dispenserState = w.getBlockState(dispenserTile.getPos());
        EnumFacing ef = dispenserState.getValue(BlockDispenser.FACING);
        bp = bp.offset(ef);
        if(meta!=0 || ef==EnumFacing.UP || ef==EnumFacing.DOWN){
            EntityItem ei = new EntityItem(w,bp.getX(),bp.getY(),bp.getZ(),new ItemStack(stack.getItem(),1,meta));
            BlockPos speed = new BlockPos(0,0,0);
            speed.offset(ef);
            ei.setVelocity(speed.getX(),speed.getY(),speed.getZ());
            w.spawnEntity(ei);
            return stack;
        }
        w.setBlockState(bp, BlockRegister.REED_GUNPOWDER.getStateFromMeta(meta*5));
        return stack;
    }
}
