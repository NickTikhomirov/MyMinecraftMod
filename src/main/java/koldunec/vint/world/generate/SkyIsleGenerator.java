package koldunec.vint.world.generate;

import koldunec.vint.items.scroll2;
import koldunec.vint.vint;
import koldunec.vint.world.structures.ISkyIsle;
import koldunec.vint.world.structures.LlamaIsland;
import koldunec.vint.world.structures.TwilightIsland;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Map;
import java.util.Random;

public class SkyIsleGenerator implements IWorldGenerator {
    private ISkyIsle[] islesOverw = new ISkyIsle[]{
            new LlamaIsland()
    };

    private ISkyIsle[] islesTwi = null;

    private ISkyIsle[] getForWorld(WorldProvider wp){
        if(wp.getDimension()==0) return islesOverw;
        return islesTwi;
    }

    public SkyIsleGenerator(){
        if(vint.isLoadedTwilight)
            islesTwi = new ISkyIsle[]{
                new TwilightIsland()
            };
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(worldNGen(world.provider)) return;

        int x = (chunkX << 4) + 8;
        int z = (chunkZ << 4) + 8;
        int y = vint.random.nextInt(10)+120;
        BlockPos p = new BlockPos(x,y,z);
        if(!emptyPos15(world,p)) return;

        WorldServer w = (WorldServer) world;
        TemplateManager t = w.getStructureTemplateManager();
        PlacementSettings ps = new PlacementSettings();

        ISkyIsle[] generates = getForWorld(world.provider);
        for(ISkyIsle item : generates){
            if(randomNGen(random, item.getBound())) continue;
            Template template = t.get(w.getMinecraftServer(), item.getLocation());
            template.addBlocksToWorld(world,p,ps);
            Map<BlockPos,String> dB = template.getDataBlocks(p,ps);
            for(Map.Entry<BlockPos,String> e: dB.entrySet())
                item.fill(e.getKey(),e.getValue(),world,random);
            return;
        }
    }

    public boolean emptyPos15(World world, BlockPos p){
        return scroll2.nice_zone(world,p,new BlockPos(15,15,15));
    }

    boolean randomNGen(Random r, int bound){
        return r.nextInt(bound)!=0;
    }

    boolean worldNGen(WorldProvider wp){
        if(wp.getDimension()==0) return false;
        //if(wp.getDimension()==-1) return false;
        if(vint.isLoadedTwilight) return !TwilightIsland.checkTwilight(wp);
        return true;
    }
}
