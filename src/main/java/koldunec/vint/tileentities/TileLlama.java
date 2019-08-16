package koldunec.vint.tileentities;

import koldunec.vint.vint;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;

public class TileLlama extends TileEntity implements ITickable {
    private int timeSinceUpdate = 0;


    @Override
    public void update() {
        timeSinceUpdate++;
        if(timeSinceUpdate>=20){
            timeSinceUpdate = 0;
            if(world.isRemote) return;
            int LlamaCounter = 0;
            World w = getWorld();
            BlockPos p = getPos();
            LlamaCounter = w.getEntitiesWithinAABB(EntityLlama.class,new AxisAlignedBB(
                    p.getX()-6,
                    p.getY()-2,
                    p.getZ()-6,
                    p.getX()+6,
                    p.getY()+2,
                    p.getZ()+6)).size();
            if(LlamaCounter<4){
                EntityLlama newlama = new EntityLlama(w);
                newlama.setVariant(vint.random.nextInt(4));
                newlama.setPositionAndRotation(pos.getX(),pos.getY(),pos.getZ(),world.rand.nextFloat() * 360.0F,0F);
                AnvilChunkLoader.spawnEntity(newlama,w);
                world.playEvent(2004,p,0);
            }
        }
    }
}
