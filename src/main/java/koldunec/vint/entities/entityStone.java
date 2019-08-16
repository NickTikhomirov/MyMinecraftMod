package koldunec.vint.entities;

import koldunec.vint.init.ItemRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class entityStone extends EntityThrowable {


    public entityStone(World world) {
        super(world);
    }

    public entityStone(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public entityStone(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    //Вызов различных событий (частицы, звуки), при попадени снаряда в цель (живое существо, блок)
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        //if (id == 3) {
        //    for (int i = 0; i < 8; ++i) {
                //Появляются частицы лавы. В обычном майнкрафте они образуются на потолке, если сверху лава.
        //        this.world.spawnParticle(EnumParticleTypes.DRIP_LAVA, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        //   }
        //}
    }

    //Вызывается, когда предмет попадает в цель (живое существо, блок)
    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            if(world.isRemote) return;
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 2);
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        } else if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
            EntityItem o = new EntityItem(
                    world,
                    this.posX,
                    this.posY,
                    this.posZ,
                    new ItemStack(ItemRegister.ROUND_STONE,1));
            world.spawnEntity(o);
        }
    }
}
