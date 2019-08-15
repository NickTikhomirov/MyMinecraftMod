package koldunec.ammpdbm_mod.events;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.tools.reliquarist_sword;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.items.consumables.ItemZombieBrain;
import twilightforest.entity.EntityTFSlimeBeetle;

import java.util.Random;

@Mod.EventBusSubscriber
public class ThosePitifulDeaths {

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e){
        if(e.getEntity().getEntityWorld().isRemote) return;
        EntityLivingBase victim = e.getEntityLiving();
        DamageSource way = e.getSource();
        Random rng = ammpdbm_mod.random;
        EntityLivingBase killer = null;
        if(way.getTrueSource() instanceof EntityLivingBase)
            killer = (EntityLivingBase)way.getTrueSource();

        //everything about player killers
        if(killer instanceof EntityPlayer){
            EntityPlayer guyKiller = (EntityPlayer) killer;
            ItemStack weapon = guyKiller.getHeldItemMainhand();
            if(!weapon.isEmpty()){
                if(weapon.getItem() instanceof reliquarist_sword){
                    if(net.minecraftforge.fml.common.Loader.isModLoaded("xreliquary")){
                        if(victim instanceof EntityZombie){
                            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,6),0F);
                            }
                        } else if(victim instanceof AbstractSkeleton && !(victim instanceof EntityWitherSkeleton)){
                            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,0),0F);
                            }
                        } else if(victim instanceof EntityCreeper){
                            if(((EntityCreeper)victim).getPowered()){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,8),0F);
                            }
                            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,3),0F);
                            }
                        } else if(victim instanceof EntitySpider){
                            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,2),0F);
                            }
                        } else if(victim instanceof EntityBat){
                            int a = 1;
                            if(reliquarist_sword.countReliqueDrop(weapon,4)) a++;
                            victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),a,5),0F);
                        } else if(victim instanceof EntitySquid){
                            if(reliquarist_sword.countReliqueDrop(weapon,6)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,12),0F);
                            }
                        } else if(victim.getClass().equals(EntitySlime.class)){
                            if(((EntitySlime)victim).isSmallSlime()){
                                if(reliquarist_sword.countReliqueDrop(weapon,10)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,4),0F);
                                }
                            }
                        } else if(ammpdbm_mod.isLoadedTwilight && victim instanceof EntityTFSlimeBeetle){
                            if(reliquarist_sword.countReliqueDrop(weapon,5)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,4),0F);
                            }
                        }
                        if(((reliquarist_sword)weapon.getItem()).getLevel()>0){
                            if(victim instanceof EntityWitherSkeleton){
                                if(reliquarist_sword.countReliqueDrop(weapon,12)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,1),0F);
                                }
                            } else if(victim instanceof EntityWitch){
                                if(reliquarist_sword.countReliqueDrop(weapon,10)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:witch_hat"),1),0F);
                                }
                            } else if(victim instanceof EntityGuardian){
                                if(reliquarist_sword.countReliqueDrop(weapon,8)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,16),0F);
                                }
                            } else if(victim instanceof EntitySnowman){
                                if(reliquarist_sword.countReliqueDrop(weapon,8)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,10),0F);
                                }
                            }
                        }
                        if(((reliquarist_sword)weapon.getItem()).getLevel()>1){
                            if(victim instanceof EntityBlaze){
                                if(reliquarist_sword.countReliqueDrop(weapon,12)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,7),0F);
                                }
                            } else if(victim instanceof EntityEnderman){
                                if(reliquarist_sword.countReliqueDrop(weapon,12)){
                                    victim.entityDropItem(new ItemStack(Item.getByNameOrId("xreliquary:mob_ingredient"),1,11),0F);
                                }
                            }
                        }
                    }
                    if(ammpdbm_mod.isLoadedThaumcraft){
                        if(victim instanceof EntityZombie && !(victim instanceof EntityPigZombie)){
                            if(victim instanceof EntityBrainyZombie || reliquarist_sword.countReliqueDrop(weapon,12)){
                                if(rng.nextBoolean()){
                                    victim.entityDropItem(new ItemStack(ItemsTC.brain,1),0F);
                                }
                            }
                        }
                    }
                    if(net.minecraftforge.fml.common.Loader.isModLoaded("randomthings")){
                        if(victim instanceof EntityGhast){
                            if(reliquarist_sword.countReliqueDrop(weapon,6)){
                                victim.entityDropItem(new ItemStack(Item.getByNameOrId("randomthings:ingredient"),1,2),0F);
                            }
                        }
                    }
                }
            }
        }

        if(net.minecraftforge.fml.common.Loader.isModLoaded("thaumcraft")){
            if(victim instanceof EntityFireBat){
                switch(rng.nextInt(5)){
                    case 0: victim.entityDropItem(new ItemStack(Items.REDSTONE,1),0F);
                    case 1:
                        victim.entityDropItem(new ItemStack(Items.REDSTONE,1),0F);
                        break;
                    case 2: victim.entityDropItem(new ItemStack(Items.GLOWSTONE_DUST,1),0F);
                    case 3:
                        victim.entityDropItem(new ItemStack(Items.GLOWSTONE_DUST,1),0F);
                        break;
                    case 4: victim.entityDropItem(new ItemStack(Items.BLAZE_POWDER,1),0F);
                }
            }
        }
    }
}
