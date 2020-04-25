package koldunec.vint.items;


import koldunec.vint.init.ItemRegister;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;

import static java.lang.Math.round;


public class Broom extends Item {

    public Broom(String name) {
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(vint.magicTab);
        this.maxStackSize = 1;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 600;
    }


    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Block b = state.getBlock();
        if(stack.getItem().equals(ItemRegister.BROOM))
            if(b.equals(Blocks.ENDER_CHEST) || b.equals(Blocks.WEB))
                return 150.0F;
            else if(b.equals(Blocks.BOOKSHELF) || b.equals(Blocks.ENCHANTING_TABLE))
                return 75.0F;
            return 1.0F;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state)
    {
        if(
                state.getBlock().equals(Blocks.ENCHANTING_TABLE)
              ||state.getBlock().equals(Blocks.WEB)
              ||state.getBlock().equals(Blocks.BOOKSHELF)
              ||state.getBlock().equals(Blocks.ENDER_CHEST)
              ||state.getBlock().equals(Blocks.SEA_LANTERN)
        )
            return true;
        return super.canHarvestBlock(state);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
            return EnumActionResult.FAIL;
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR && block == Blocks.GRASS) {
            IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
            worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!worldIn.isRemote)
                worldIn.setBlockState(pos, iblockstate1, 11);
            return EnumActionResult.SUCCESS;
        } else if (block == Blocks.MONSTER_EGG) {
            IBlockState iblockstate1 = iblockstate;
            if(block.getMetaFromState(iblockstate)==0)iblockstate1 = Blocks.STONE.getDefaultState();
            if(block.getMetaFromState(iblockstate)==1)iblockstate1 = Blocks.COBBLESTONE.getDefaultState();
            if(block.getMetaFromState(iblockstate)==2)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(0);
            if(block.getMetaFromState(iblockstate)==3)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(1);
            if(block.getMetaFromState(iblockstate)==4)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(2);
            if(block.getMetaFromState(iblockstate)==5)iblockstate1 = Blocks.STONEBRICK.getBlockState().getValidStates().get(3);
            worldIn.playSound(player, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!worldIn.isRemote)
                worldIn.setBlockState(pos, iblockstate1, 11);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if(!(entity instanceof EntityLiving))
            return super.onLeftClickEntity(stack,player,entity);
        EntityLiving e = (EntityLiving) entity;
        BlockPos bp = e.getPosition();
        if(e.getClass().equals(EntityIronGolem.class)){
            if(!e.getEntityWorld().isRemote){
                int px = bp.getX();
                int py = bp.getY();
                int pz = bp.getZ();
                World pw = e.world;

                EntityItem aa = new EntityItem(pw,px,py,pz,
                        (new ItemStack(Blocks.IRON_BLOCK,
                                round( 4 * e.getHealth() / e.getMaxHealth()))));

                e.world.spawnEntity(aa);
                e.world.playSound(px,py,pz, SoundEvents.ENTITY_ENDERMEN_SCREAM, SoundCategory.HOSTILE,1.0F,1.0F,true);
                e.setDead();
            }
            return true;
        }
        if(e.getClass().equals(EntitySnowman.class)){
            if(!e.getEntityWorld().isRemote){
                EntityItem aa = new EntityItem(
                        e.world,
                        bp.getX(),
                        bp.getY(),
                        bp.getZ(),
                        new ItemStack(Blocks.SNOW,2));
                e.world.spawnEntity(aa);
                e.setDead();
            }
        }
        if(e instanceof EntitySpider || e instanceof EntitySilverfish){
            e.hurtResistantTime = 0;
            e.attackEntityFrom(DamageSource.GENERIC,10);
        }
        return false;
    }

    public static HashSet<Block> affected_blocks = new HashSet<Block>(){{
        add(Blocks.WEB);
        add(Blocks.ENCHANTING_TABLE);
        add(Blocks.BOOKSHELF);
        add(Blocks.ENDER_CHEST);
        add(Blocks.SEA_LANTERN);
    }};



}
