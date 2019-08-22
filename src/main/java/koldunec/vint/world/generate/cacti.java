package koldunec.vint.world.generate;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.LootRegister;
import koldunec.vint.items.radio_cactus;
import koldunec.vint.items.scroll2;
import koldunec.vint.tileentities.EntityStore;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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

public class cacti implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!world.provider.doesWaterVaporize()) return;
        int ii = vint.random.nextInt(3);
        for(int i=0;i<3;i++){
            if(i==ii) continue;
            int y_min = (i+1)*30;
            int y_max = getmax(i);
            for(int j=0;j<8;j++){
                BlockPos b = randompos((chunkX<<4),y_min,(chunkZ<<4));
                int c_height=0;
                for(int y=0;y<=y_max-y_min;y++){
                    IBlockState bstate = world.getBlockState(b.up(y));
                    Block bl = bstate.getBlock();
                    if(bl.equals(Blocks.NETHER_BRICK)) return;
                    if(c_height>2 || bstate.getMaterial().isLiquid() || bl.equals(Blocks.GRAVEL) || !(bstate.isFullBlock() || bl.equals(BlockRegister.NETHER_CACTUS))){
                        y+=100;
                        continue;
                    }
                    if(bl.equals(Blocks.SOUL_SAND) || bl.equals(BlockRegister.NETHER_CACTUS)){
                        if(world.getBlockState(b.up(y+1)).getBlock().equals(Blocks.AIR))
                            if(radio_cactus.canStay(world,b.up(y+1))){
                                world.setBlockState(b.up(y+1),BlockRegister.NETHER_CACTUS.getDefaultState());
                                c_height++;
                        }
                    }
                }
            }
        }
    }

    static int getmax(int i){
        switch(i) {
            case 0: return 35;
            case 1: return 69;
            default: return 95;
        }
    }

    static BlockPos randompos(int x, int y, int z){
        x += 1 + vint.random.nextInt(14);
        z += 1 + vint.random.nextInt(14);
        return new BlockPos(x,y,z);
    }
}
