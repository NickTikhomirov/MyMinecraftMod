package koldunec.ammpdbm_mod.world.generate;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.BlockRegister;
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

public class GenerateOre implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        switch(world.provider.getDimension()) { //Получение ID измерения

            case -1: //Нэзер
                break;
            case 0: //Обычний мир
                runGenerator(BlockRegister.ORE_BIT.getDefaultState(), 4, 8, 30, 100, Blocks.STONE, world, random, chunkX, chunkZ);
                runGenerator(BlockRegister.ORE_BIT.getDefaultState(), 4, 4, 10, 29, Blocks.STONE, world, random, chunkX, chunkZ);
                //runGenerator(BlockRegister.ORE_SPARKLE.getDefaultState(), 7, 8, 30, 64, Blocks.STONE, world, random, chunkX, chunkZ);
                runGenerator(BlockRegister.ORE_EGG.getDefaultState(), 3, 5, 10, 20, Blocks.STONE, world, random, chunkX, chunkZ);
                runGenerator(BlockRegister.ORE_RAINBOW.getDefaultState(), 4, 15, 49, 110, Blocks.DIRT, world, random, chunkX, chunkZ);
                runGenerator(Blocks.EMERALD_ORE.getDefaultState(),3,5,4,32, Blocks.STONE, world, random, chunkX, chunkZ);
                if(ammpdbm_mod.isLoadedProjectX)
                    runGenerator(BlockRegister.ORE_ALUMINUM.getDefaultState(), 3, 12, 25, 50, Blocks.STONE, world, random, chunkX, chunkZ);
                break;
            case 1: //Край
                break;
            default: //Мир из другого мода (если нужно)
                runGenerator(BlockRegister.ORE_RAINBOW.getDefaultState(), 4, 16, 10, 70, Blocks.DIRT, world, random, chunkX, chunkZ);
                runGenerator(Blocks.EMERALD_ORE.getDefaultState(),3,5,4,32, Blocks.STONE, world, random, chunkX, chunkZ);
                break;

        /*
            BlocksInit.TEST_ORE.getDefaultState() - генерируемый блок
            1 - максимальное количество блоков в месторождении, можно задать вариацию записью 1 + random.nextInt(4) для генерации от 1-го до 5-ти блоков в месторождении
            8 - количество месторождений на чанк
            0 - минимальная высота генерации
            16 - максимальная высота генерации
            BlockMatcher.forBlock(Blocks.STONE) - блок, который можно заменить на руду
        */

        }

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