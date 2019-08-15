package koldunec.ammpdbm_mod.world.generate;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.scroll2;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.LootRegister;
import koldunec.ammpdbm_mod.tileentities.EntityStore;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Map;
import java.util.Random;

public class llama_island implements IWorldGenerator {
    int keeptrying = 0;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(world.provider.getDimension()!=0) return;
        if(keeptrying==50) return;
        if(ammpdbm_mod.random.nextInt(850)!=0 && keeptrying==0) return;

        int x = (chunkX << 4) + 8;
        int z = (chunkZ << 4) + 8;
        int y = ammpdbm_mod.random.nextInt(10)+120;
        BlockPos p = new BlockPos(x,y,z);

        WorldServer w = (WorldServer) world;
        TemplateManager t = w.getStructureTemplateManager();
        Template template = t.get(w.getMinecraftServer(),new ResourceLocation(ammpdbm_mod.MODID+":best_island1"));
        PlacementSettings ps = new PlacementSettings();

        if(!scroll2.nice_zone(world,p,new BlockPos(15,15,15))){
            keeptrying++;
            return;
        }
        keeptrying = 0;

        template.addBlocksToWorld(world,p,ps);
        Map<BlockPos,String> dB = template.getDataBlocks(p,ps);
        for(Map.Entry<BlockPos,String> e: dB.entrySet()){
            String[] tokens = e.getValue().split(" ");
            if(tokens.length == 0) return;
            BlockPos dataPos = e.getKey();
            if(tokens[0].equals("store1")){
                world.setBlockState(dataPos, BlockRegister.STORE.getDefaultState());
                EntityStore es = (EntityStore) world.getTileEntity(dataPos);
                LootContext.Builder builder = new LootContext.Builder((WorldServer) world);
                world.getLootTableManager().getLootTableFromLocation(LootRegister.LLAMA_ISLAND).fillInventory(es, ammpdbm_mod.random,builder.build());
            }
        }
    }
}
