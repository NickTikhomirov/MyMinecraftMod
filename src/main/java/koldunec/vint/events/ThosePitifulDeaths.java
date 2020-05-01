package koldunec.vint.events;

import com.progwml6.natura.entities.entity.monster.EntityNitroCreeper;
import koldunec.vint.utils.RandomUtils;
import koldunec.vint.vint;
import koldunec.vint.items.saviour;
import koldunec.vint.items.scroll;
import koldunec.vint.items.tools.reliquarist_sword;
import koldunec.vint.items.xyAmulet;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.utils.MobLootUtils;
import net.daveyx0.primitivemobs.entity.monster.EntityBrainSlime;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityFireBat;
import twilightforest.entity.boss.EntityTFMinoshroom;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class ThosePitifulDeaths {

    @SubscribeEvent
    public void onDeath(LivingDropsEvent e){
        if(e.getEntity().getEntityWorld().isRemote) return;
        List<EntityItem> list = e.getDrops();
        EntityLivingBase victim = e.getEntityLiving();
        DamageSource way = e.getSource();
        Random rng = vint.random;
        EntityLivingBase killer = null;
        if(way.getTrueSource() instanceof EntityLivingBase)
            killer = (EntityLivingBase)way.getTrueSource();

        //everything about player killers
        if(killer instanceof EntityPlayer){
            EntityPlayer guyKiller = (EntityPlayer) killer;
            ItemStack weapon = guyKiller.getHeldItemMainhand();
            if(!weapon.isEmpty()){
                if(weapon.getItem() instanceof reliquarist_sword){
                    if(net.minecraftforge.fml.common.Loader.isModLoaded("xreliquary"))
                        MobLootUtils.dropXeno(victim,list,weapon);
                    if(vint.isLoadedThaumcraft){
                        if(victim instanceof EntityZombie && !(victim instanceof EntityPigZombie)){
                            if(victim instanceof EntityBrainyZombie || reliquarist_sword.countReliqueDrop(weapon,12)){
                                if(rng.nextBoolean()){
                                    list.add(MobLootUtils.formDrop(victim, new ItemStack(ItemsTC.brain,1)));
                                }
                            }
                        }
                    }
                    if(net.minecraftforge.fml.common.Loader.isModLoaded("randomthings")){
                        if(victim instanceof EntityGhast){
                            if(reliquarist_sword.countReliqueDrop(weapon,6)){
                                list.add(MobLootUtils.formDrop(victim, new ItemStack(Item.getByNameOrId("randomthings:ingredient"),1,2)));
                            }
                        }
                    }
                }
            }
        }

        if(vint.isLoadedThaumcraft){
            if(victim instanceof EntityFireBat){
                switch(rng.nextInt(5)){
                    case 0: list.add(MobLootUtils.formDrop(victim,new ItemStack(Items.REDSTONE,1)));
                    case 1:
                        list.add(MobLootUtils.formDrop(victim,new ItemStack(Items.REDSTONE,1)));
                        break;
                    case 2: list.add(MobLootUtils.formDrop(victim,new ItemStack(Items.GLOWSTONE_DUST,1)));
                    case 3:
                        list.add(MobLootUtils.formDrop(victim,new ItemStack(Items.GLOWSTONE_DUST,1)));
                        break;
                    case 4: list.add(MobLootUtils.formDrop(victim,new ItemStack(Items.BLAZE_POWDER,1)));
                }
            }
        }

        if(vint.integrationHelper.isLoadedTwilight && vint.integrationHelper.isLoadedPrimitive){
            if(victim instanceof EntityTFMinoshroom){
                list.add(new EntityItem(victim.getEntityWorld(),victim.posX,victim.posY,victim.posZ,new ItemStack(ItemRegister.SCROLL_ISLE,1,0)));
            }
        }

        if(vint.integrationHelper.isLoadedNatura){
            if(victim instanceof EntityNitroCreeper){
                list.add(MobLootUtils.formDrop(victim,new ItemStack(Items.GUNPOWDER,5+vint.random.nextInt(3))));
                if(vint.random.nextBoolean())
                    list.add(MobLootUtils.formDrop(victim,new ItemStack(Item.getByNameOrId("natura:materials"),1+vint.random.nextInt(2),4)));
                if(vint.random.nextInt(8)==0){
                    list.add(MobLootUtils.formDrop(victim,new ItemStack(Item.getByNameOrId("natura:nether_sapling"),1,1)));
                }
            }
        }

        if(vint.integrationHelper.isLoaded("railcraft")){
            if(victim.getEntityWorld().provider.doesWaterVaporize())
                if(vint.random.nextInt(5)==0)
                    list.add(MobLootUtils.formDrop(victim,new ItemStack(Item.getByNameOrId("railcraft:dust"),1,2)));

        }
    }

    @SubscribeEvent
    public void deathDrops(LivingDropsEvent e) {
        if(e.getEntity().getEntityWorld().isRemote) return;
        List<EntityItem> l = e.getDrops();
        if(vint.isLoadedPrimitive){
            if(e.getEntityLiving() instanceof EntityBrainSlime && ((EntitySlime)e.getEntityLiving()).getSlimeSize()<2){
                if(vint.isLoadedTinkers){
                    if(l.size()==1) l.clear();
                    else {
                        for(int i=0;i<l.size();i++)
                            if(l.get(i).getItem().getItem().equals(Items.SLIME_BALL)){
                                l.remove(i);
                                break;
                            }
                    }
                    l.add(MobLootUtils.formDrop(e.getEntity(),
                            new ItemStack(Item.getByNameOrId("tconstruct:edible"),
                                vint.random.nextInt(2)+2,
                                2)));
                }
                if(vint.isLoadedThaumcraft && vint.random.nextInt(5)==0){
                    e.getEntityLiving().entityDropItem(
                            new ItemStack(ItemsTC.brain,
                                    1)
                            ,0F);
                }
            }
        }

        if(!e.getEntity().isNonBoss()){
            int bound = 1 + vint.random.nextInt(2);
            for(int i=0; i<bound; ++i)
                e.getEntity().entityDropItem(
                        new ItemStack(
                                ItemRegister.SCROLL,
                                1,
                                vint.random.nextInt(scroll.scrollTypes.values().length)),
                        0.5F);
        }

        if(vint.isLoadedScalingH){
            if(e.getEntity() instanceof EntityPigZombie && RandomUtils.getTrueWithChance(7))
                e.getEntity().entityDropItem(
                        new ItemStack(
                                Item.getByNameOrId("scalinghealth:difficultychanger"),
                                1,
                                0
                        ),
                        0F);
        }
        
    }

    static void dropItem(Entity e, Item i, int amount, int meta){
        e.entityDropItem(new ItemStack(i,amount,meta),0);
    }

    static void dropItem(Entity e, String s, int amount, int meta){
        dropItem(e, Item.getByNameOrId(s),amount,meta);
    }

}
