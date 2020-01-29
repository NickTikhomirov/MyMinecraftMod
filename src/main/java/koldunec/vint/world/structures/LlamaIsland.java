package koldunec.vint.world.structures;

import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.LootRegister;
import koldunec.vint.tileentities.EntityStore;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;

import java.util.Random;

public class LlamaIsland implements ISkyIsle {

    @Override
    public int getX() { return 15; }

    @Override
    public int getY() { return 15; }

    @Override
    public int getZ() { return 15; }

    @Override
    public int getBound() { return 850; }

    @Override
    public ResourceLocation getLocation() {
        return new ResourceLocation(vint.MODID+":best_island1");
    }

    @Override
    public void fill(BlockPos b, String s, World w, Random r) {
        w.setBlockState(b, BlockRegister.STORE.getDefaultState());
        EntityStore es = (EntityStore) w.getTileEntity(b);
        LootContext.Builder lb = new LootContext.Builder((WorldServer) w);
        w.getLootTableManager().getLootTableFromLocation(LootRegister.LLAMA_ISLAND).fillInventory(es, vint.random, lb.build());
    }

}
