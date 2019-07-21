package koldunec.ammpdbm_mod.entities;

import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static koldunec.ammpdbm_mod.init.PotionRegister.ENDERPROTECTION;

public class entityBitcoin extends EntityThrowable {


    public entityBitcoin(World world) {
        super(world);
    }

    public entityBitcoin(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public entityBitcoin(World world, double x, double y, double z) {
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
            if(result.entityHit instanceof MultiPartEntityPart &&
               ((MultiPartEntityPart)result.entityHit).parent instanceof EntityDragon){
                if(this.getThrower()!=null &&
                        this.getThrower() instanceof EntityPlayer &&
                        this.getThrower().isPotionActive(ENDERPROTECTION)){
                    short o=0;
                    Iterable<ItemStack> p = ((EntityPlayer) this.getThrower()).getArmorInventoryList();
                    for(ItemStack a: p){
                        if(a.getItem() instanceof ItemArmor){
                            if(((ItemArmor)a.getItem()).getArmorMaterial().equals(ItemArmor.ArmorMaterial.CHAIN)) o++;
                        }
                    }
                    if(o==4 && thrower.getActivePotionEffect(ENDERPROTECTION).getDuration()>9600) result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,this.getThrower()),1000);
                }
            }
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 2);
            world.setEntityState(this, (byte)3);
            setDead();
        } else if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
            EntityItem o = new EntityItem(
                    world,
                    thrower.posX,
                    thrower.posY,
                    thrower.posZ,
                    new ItemStack(ItemRegister.BITCOIN5000,1));
            world.spawnEntity(o);
        }
    }
}
