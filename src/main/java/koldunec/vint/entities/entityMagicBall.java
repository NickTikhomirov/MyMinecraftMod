package koldunec.vint.entities;

import koldunec.vint.init.ItemRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class entityMagicBall extends EntityThrowable {

    public int params;

    public entityMagicBall(World world) {
        super(world);
        params=0;
    }

    public entityMagicBall(World world, EntityLivingBase thrower) {
        super(world, thrower);
        params=0;
    }

    public entityMagicBall(World world, double x, double y, double z) {
        super(world, x, y, z);
        params=0;
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
            if(result.entityHit instanceof EntityChicken){
                if(world.isRemote){
                    for(int i=0;i<25;i++)
                        world.spawnParticle(
                                EnumParticleTypes.FLAME,
                                result.entityHit.posX+(Math.random()*((0.5-(-0.5))+1))-0.5,
                                result.entityHit.posY,
                                result.entityHit.posZ+(Math.random()*((0.5-(-0.5))+1))-0.5,
                                0.0D, 0.25D, 0.0D);
                    return;
                } else {
                    EntityItem o = new EntityItem(
                            world,
                            result.entityHit.posX,
                            result.entityHit.posY,
                            result.entityHit.posZ,
                            new ItemStack(Items.EGG, 200));
                    world.spawnEntity(o);
                    result.entityHit.setDead();
                }
            } else if(result.entityHit instanceof EntityGhast && ((EntityGhast) result.entityHit).getMaxHealth()<25) {
                if (world.isRemote) {
                    for (int i = 0; i < 25; i++)
                        world.spawnParticle(
                                EnumParticleTypes.EXPLOSION_LARGE,
                                result.entityHit.posX + (Math.random() * ((0.5 - (-0.5)) + 1)) - 0.5,
                                result.entityHit.posY,
                                result.entityHit.posZ + (Math.random() * ((0.5 - (-0.5)) + 1)) - 0.5,
                                0D, 0D, 0D);
                    return;
                } else {
                    result.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), 2000F);
                    for (EntityItem a : result.entityHit.capturedDrops)
                        a.setPosition(thrower.posX, thrower.posY, thrower.posZ);
                }
            } else if(result.entityHit instanceof EntityPigZombie){
                if(!world.isRemote)
                    result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), (float) 8);
            } else if(result.entityHit instanceof EntityCreeper){
                if(!world.isRemote)
                    result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, (EntitySkeleton) null), (float) 4);

            } else {
                if(world.isRemote) return;
                int i = 2;
                if(Math.abs(world.provider.getDimension())>1) i++;
                result.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), (float) i);
            }
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        } else if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
            if(thrower instanceof EntityPlayer)
              if(!((EntityPlayer)thrower).isCreative()){
                EntityItem o = new EntityItem(
                        world,
                        thrower.posX,
                        thrower.posY,
                        thrower.posZ,
                        new ItemStack(ItemRegister.MAGICBALL,1));
                world.spawnEntity(o);
            }
        }
    }
}
