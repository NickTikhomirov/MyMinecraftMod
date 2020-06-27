package koldunec.vint.compatibility.Tinker.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import twilightforest.entity.EntityTFMazeSlime;

public class Mazey extends AbstractTrait {

    public static float chance = 0.0099F;

    public Mazey() {
        super("mazey", TextFormatting.AQUA);
    }


    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if(wasEffective && !world.isRemote && random.nextFloat() < chance) {
            spawnSlime(player, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world);
        }
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(!target.isEntityAlive() && !target.getEntityWorld().isRemote && random.nextFloat() < chance) {
            spawnSlime(player, target.posX, target.posY, target.posZ, target.getEntityWorld());
        }
    }

    protected void spawnSlime(EntityLivingBase player, double x, double y, double z, World world) {
        EntityTFMazeSlime entity = new EntityTFMazeSlime(world);
        entity.func_70799_a(1, true);
        entity.setPosition(x, y, z);
        world.spawnEntity(entity);
        entity.setLastAttackedEntity(player);
        entity.playLivingSound();
    }
}
