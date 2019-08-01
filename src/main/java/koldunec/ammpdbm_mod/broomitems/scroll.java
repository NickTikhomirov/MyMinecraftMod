package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.tileentities.EntityStore;
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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.getBlockState(pos).getBlock().equals(BlockRegister.STORE)
                && player.isSneaking()
                && !worldIn.isRemote){
            ItemStack s = player.getHeldItemMainhand();
            EntityStore p1 = (EntityStore) worldIn.getTileEntity(pos);
            if(p1==null || !p1.isEmpty()) return EnumActionResult.FAIL;
            LootContext.Builder builder = new LootContext.Builder((WorldServer) worldIn).withPlayer(player);
            ResourceLocation l = null;
            if(s.getMetadata()==0){
                l = LootTableList.CHESTS_DESERT_PYRAMID;
            } else if(s.getMetadata()==1) {
                l = LootTableList.CHESTS_ABANDONED_MINESHAFT;
            } else if(s.getMetadata()==2) {
                l = LootTableList.CHESTS_SIMPLE_DUNGEON;
            } else if(s.getMetadata()==3){
                l = LootTableList.CHESTS_JUNGLE_TEMPLE;
            } else l = LootTableList.CHESTS_SPAWN_BONUS_CHEST;
            worldIn.getLootTableManager().getLootTableFromLocation(l).fillInventory(p1, worldIn.rand, builder.build());
            s.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
