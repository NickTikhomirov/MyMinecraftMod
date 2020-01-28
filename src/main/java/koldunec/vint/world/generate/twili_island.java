package koldunec.vint.world.generate;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.LootRegister;
import koldunec.vint.items.scroll2;
import koldunec.vint.tileentities.EntityStore;
import koldunec.vint.vint;
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
import twilightforest.TwilightForestMod;
import twilightforest.world.WorldProviderTwilightForest;

import java.util.Map;
import java.util.Random;

public class twili_island implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!vint.isLoadedTwilight) return;
        if(!(world.provider instanceof WorldProviderTwilightForest)) return;
        if(vint.random.nextInt(150)!=0) return;

        int x = (chunkX << 4) + 8;
        int z = (chunkZ << 4) + 8;
        int y = vint.random.nextInt(10)+120;
        BlockPos p = new BlockPos(x,y,z);

        WorldServer w = (WorldServer) world;
        TemplateManager t = w.getStructureTemplateManager();
        Template template = t.get(w.getMinecraftServer(),new ResourceLocation(vint.MODID+":i_1_t"));
        PlacementSettings ps = new PlacementSettings();

        if(!scroll2.nice_zone(world,p,new BlockPos(15,15,15))){
            return;
        }

        template.addBlocksToWorld(world,p,ps);
        Map<BlockPos,String> dB = template.getDataBlocks(p,ps);
        for(Map.Entry<BlockPos,String> e: dB.entrySet()){
            String[] tokens = e.getValue().split(" ");
            if(tokens.length == 0) return;
            BlockPos dataPos = e.getKey();
            if(tokens[0].equals("chest")){
                world.setBlockState(dataPos, BlockRegister.STORE.getDefaultState());
                EntityStore es = (EntityStore) world.getTileEntity(dataPos);
                LootContext.Builder builder = new LootContext.Builder((WorldServer) world);
                world.getLootTableManager().getLootTableFromLocation(LootRegister.LLAMA_ISLAND).fillInventory(es, vint.random,builder.build());
            }
        }
    }
}
