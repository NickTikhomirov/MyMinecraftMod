package koldunec.ammpdbm_mod.utils;

import koldunec.ammpdbm_mod.tileentities.EntityStore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;

public class Lootgen {
    public static void fill_store(EntityPlayer player, EntityStore p1, World worldIn, ResourceLocation l){
        if(worldIn.isRemote) return;
        LootContext.Builder builder = new LootContext.Builder((WorldServer) worldIn).withPlayer(player);
        worldIn.getLootTableManager().getLootTableFromLocation(l).fillInventory(p1, worldIn.rand, builder.build());
    }

    public static ResourceLocation get1(int a){
        if(a==0) return LootTableList.CHESTS_DESERT_PYRAMID;
        if(a==1) return LootTableList.CHESTS_ABANDONED_MINESHAFT;
        if(a==2) return LootTableList.CHESTS_SIMPLE_DUNGEON;
        if(a==3) return LootTableList.CHESTS_JUNGLE_TEMPLE;
        return LootTableList.CHESTS_SPAWN_BONUS_CHEST;
    }

    public static ResourceLocation get2(int a){
        if(a==1) return LootTableList.CHESTS_NETHER_BRIDGE;
        return LootTableList.CHESTS_SPAWN_BONUS_CHEST;
    }

    public static boolean is(int a){
        if(a==1) return true;
        return false;
    }
}
