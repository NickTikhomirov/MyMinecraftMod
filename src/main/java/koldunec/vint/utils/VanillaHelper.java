package koldunec.vint.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VanillaHelper {

    public static ItemStack getEggFor(ResourceLocation location){
        ItemStack result  = new ItemStack(Items.SPAWN_EGG);
        ItemMonsterPlacer.applyEntityIdToItemStack(result,location);
        return result;
    }

    //Is it necessary to make these cool methods protected? (copy-pasted from BlockLiquid class)
    public static void triggerMixEffects(World worldIn, BlockPos pos)
    {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i)
        {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }

    public static void GivePlayer(EntityPlayer ep, ItemStack i){
        World w = ep.getEntityWorld();
        BlockPos bp = ep.getPosition();
        w.spawnEntity(new EntityItem(w,ep.posX,ep.posY,ep.posZ, i.copy()));
    }

    public static void VisualBreak(BlockPos pos, World world){
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock().equals(Blocks.AIR))
            return;
        world.playEvent(2001, pos, Block.getStateId(state));
    }

    public static EntityItem formDrop(Entity e, ItemStack i){
        return new EntityItem(e.world,e.posX,e.posY,e.posZ,i);
    }
}
