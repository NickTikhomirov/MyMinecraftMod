package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.tileentities.EntityStore;
import koldunec.ammpdbm_mod.utils.Lootgen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.List;

public class scroll extends Item {
    public enum scrollTypes{
        DESERT,MINE,DUNGEON,JUNGLE;

        public static scrollTypes getByMeta(int meta){
            for (scrollTypes type : values()){
                if (type.ordinal() == meta)
                    return type;
            }
            return null;
        }

        public String getName(){
            return toString().toLowerCase();
        }
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + scrollTypes.getByMeta(i).getName();
    }

    public scroll(){
        this.setRegistryName("scroll");
        this.setUnlocalizedName("scroll");
        this.setCreativeTab(ammpdbm_mod.magicTab);
        this.setMaxStackSize(64);
        this.hasSubtypes=true;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == ammpdbm_mod.magicTab){
            for (scrollTypes type : scrollTypes.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add("Use this on the stORE block");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.getBlockState(pos).getBlock().equals(BlockRegister.STORE)
                && player.isSneaking()
                && !worldIn.isRemote){
            ItemStack s = player.getHeldItemMainhand();
            EntityStore p1 = (EntityStore) worldIn.getTileEntity(pos);
            if(!s.getItem().equals(this)) return EnumActionResult.FAIL;
            if(p1==null || !p1.isEmpty()) return EnumActionResult.FAIL;
            Lootgen.fill_store(player,p1,worldIn,Lootgen.get1(s.getMetadata()));
            s.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
