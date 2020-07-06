package koldunec.vint.world.generate;

import koldunec.vint.init.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;


// old stuff
//runGenerator(BlockRegister.ORE_BIT.getDefaultState(), 4, 8, 20, 64, Blocks.STONE, world, random, chunkX, chunkZ);
//runGenerator(BlockRegister.ORE_SPARKLE.getDefaultState(), 7, 8, 30, 64, Blocks.STONE, world, random, chunkX, chunkZ);
//runGenerator(BlockRegister.ORE_EGG.getDefaultState(), 3, 5, 10, 20, Blocks.STONE, world, random, chunkX, chunkZ);
//runGenerator(Blocks.EMERALD_ORE.getDefaultState(),3,5,4,32, Blocks.STONE, world, random, chunkX, chunkZ);
//runGenerator(BlockRegister.ORE_ALUMINUM.getDefaultState(), 3, 12, 25, 50, Blocks.STONE, world, random, chunkX, chunkZ);



public class GenerateOre implements IWorldGenerator {

    // struct for keeping data at the same place
    public static class Generatable{
        IBlockState b;
        int amount;
        int chances;
        int minY;
        int maxY;
        Block toReplace;
        public Generatable(IBlockState a1, int a2, int a3, int a4, int a5, Block a6){
            b = a1;
            amount = a2;
            chances = a3;
            minY = a4;
            maxY = a5;
            toReplace = a6;
        }
    }

    // array for generating in the overworld
    public static final Generatable[] generatesOverw = new Generatable[]{
            new Generatable(BlockRegister.STORE.getDefaultState(), 3, 12, 15, 64, Blocks.STONE),
            new Generatable(BlockRegister.ORE_EGG.getDefaultState(), 3, 5, 10, 20, Blocks.STONE),
            new Generatable(Blocks.EMERALD_ORE.getDefaultState(),3,5,4,32,Blocks.STONE),
            new Generatable(BlockRegister.ORE_RAINBOW.getDefaultState(), 3, 18, 60, 120, Blocks.DIRT)
    };




    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        // generator for end, nether, overworld and common world ores
        switch(world.provider.getDimension()) {
            case -1:
                break;
            case 1:
                break;
            case 0:
            default:
                for(Generatable item: generatesOverw)
                    runGenerator(item,world,random,chunkX,chunkZ);
                break;
        }
    }

    private void runGenerator(Generatable gen, World world, Random rand, int chunk_X, int chunk_Z){
        runGenerator(
                gen.b,
                gen.amount,
                gen.chances,
                gen.minY,
                gen.maxY,
                gen.toReplace,
                world,
                rand,
                chunk_X,
                chunk_Z
        );
    }

    private void runGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight, Block blockToReplace, World world, Random rand, int chunk_X, int chunk_Z){ //Объявление генератора
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) //Проверка на правильность координаты Y
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator"); //Если неправильно ошибка в консоли
        WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, BlockMatcher.forBlock(blockToReplace)); //Новый экземпляр генератора
        int heightdiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++){ //Запуск генератора в каждом чанке с заданным параметрами

            int x = chunk_X * 16 +rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightdiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

}