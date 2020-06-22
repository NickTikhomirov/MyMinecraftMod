package koldunec.vint.world.structures;


import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.others.LootRegister;
import koldunec.vint.tileentities.EntityStore;
import koldunec.vint.vint;
import net.minecraft.block.BlockChest;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import twilightforest.world.WorldProviderTwilightForest;

import java.util.Random;

public class TwilightIsland implements ISkyIsle {

    public static boolean checkTwilight(WorldProvider w){
        return w instanceof WorldProviderTwilightForest;
    }

    @Override
    public int getX() { return 15; }

    @Override
    public int getY() { return 15; }

    @Override
    public int getZ() { return 300; }

    @Override
    public int getBound() { return 200; }

    @Override
    public ResourceLocation getLocation() {
        return new ResourceLocation(vint.MODID+":i_1_t");
    }

    @Override
    public void fill(BlockPos b, String s, World w, Random r) {
        LootContext.Builder lb = new LootContext.Builder((WorldServer) w);
        if(s.equals("chest")) {
            w.setBlockState(b, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.SOUTH));
            TileEntityChest es = (TileEntityChest) w.getTileEntity(b);
            w.getLootTableManager().getLootTableFromLocation(LootRegister.TWILIGHT_CHEST).fillInventory(es, vint.random, lb.build());
        } else {
            w.setBlockState(b, BlockRegister.STORE.getDefaultState());
            EntityStore es = (EntityStore) w.getTileEntity(b);
            w.getLootTableManager().getLootTableFromLocation(LootRegister.TWILIGHT_LOOT).fillInventory(es, vint.random, lb.build());
        }
    }
}
