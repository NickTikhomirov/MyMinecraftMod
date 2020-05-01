package koldunec.vint.entities;

import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeJungle;

public class EntityHoneySlime extends EntitySlime {

    public EntityHoneySlime(World worldIn) {
        super(worldIn);
    }



    protected void initEntityAI() {
        super.initEntityAI();
        for(EntityAITasks.EntityAITaskEntry i: tasks.taskEntries){
            if(i.priority==2){
                tasks.taskEntries.remove(i);
                return;
            }
        }
    }


    @Override
    protected EntitySlime createInstance() {
        return new EntityHoneySlime(getEntityWorld());
    }

    @Override
    public boolean getCanSpawnHere() {
        World w = getEntityWorld();
        if(w.provider.getDimension()==0){
            if(w.getBiome(this.getPosition()) instanceof BiomeJungle){
                return true;
            }
        }
        return super.getCanSpawnHere();
    }
}
