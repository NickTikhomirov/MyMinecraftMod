package koldunec.vint.items;

import koldunec.vint.vint;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import koldunec.vint.init.others.MaterialRegister;


public class magnetpick extends ItemPickaxe {
    public magnetpick(){
        //super(1.0F,-2.8F,magicFlint, Sets.newHashSet(Blocks.COBBLESTONE));
        super(MaterialRegister.magicFlint);
        setCreativeTab(vint.magicTab);
        setRegistryName("magnetpick");
        setUnlocalizedName("magnetpick");
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        if(state.getBlock().equals(Blocks.COBBLESTONE) || state.getBlock().equals(Blocks.STONEBRICK))
            return 6F;
        if(state.getBlock().equals(Blocks.LOG))
            return 0.25F;
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return super.getMaxItemUseDuration(stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
}
