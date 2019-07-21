package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import koldunec.ammpdbm_mod.init.BlockRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemClock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class oreProspector extends base_item {
    public oreProspector(){
        super("red_powder",64);
        this.addPropertyOverride(new ResourceLocation("ore_searching"), new IItemPropertyGetter(){
            @SideOnly(Side.CLIENT)
            private float previousVal=0F;
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if(entityIn==null || entityIn.world==null) return setPrev(0F);
                if(worldIn==null) worldIn = entityIn.world;
                if(worldIn.getTotalWorldTime()%20!=0) return previousVal;
                if(stack.isOnItemFrame()) return setPrev(0F);
                if(!(entityIn instanceof EntityPlayer)|| Math.abs(worldIn.provider.getDimension())==1) return setPrev(0F);
                if(entityIn.posY<2) return setPrev(0F);
                EntityPlayer player = (EntityPlayer) entityIn;
                int mix = player.getPosition().getX()-1;
                int miy = Math.max(0,player.getPosition().getY()-4);
                int miz = player.getPosition().getZ()-1;

                int Mix = mix+3;
                int Miy = player.getPosition().getY();
                int Miz = miz+3;

                for(int iy=miy;iy<Miy;iy++){
                    for(int iz=miz;iz<Miz;iz++){
                        for(int ix=mix;ix<Mix;ix++){
                            if(worldIn.getBlockState(new BlockPos(ix,iy,iz)).getBlock().equals(BlockRegister.ORE_RAINBOW))
                                return setPrev(0.0078125F);
                        }
                    }
                }
                return setPrev(0F);
            }

            @SideOnly(Side.CLIENT)
            private float setPrev(float r){
                previousVal=r;
                return r;
            }
        });
    }
}
