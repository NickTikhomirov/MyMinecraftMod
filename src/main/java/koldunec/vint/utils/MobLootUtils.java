package koldunec.vint.utils;

import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import koldunec.vint.items.tools.reliquarist_sword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.entity.EntityTFSlimeBeetle;

import java.util.List;

public class MobLootUtils {
    private final static Item xe = Item.getByNameOrId("xreliquary:mob_ingredient");


    public static void dropXeno(EntityLivingBase victim, List<EntityItem> list, ItemStack weapon){
        if(victim instanceof EntityZombie){
            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                list.add(formDrop(victim, new ItemStack(xe,1,6)));
            }
        } else if(victim instanceof AbstractSkeleton && !(victim instanceof EntityWitherSkeleton)){
            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                list.add(formDrop(victim, new ItemStack(xe,1,0)));
            }
        } else if(victim instanceof EntityCreeper){
            if(((EntityCreeper)victim).getPowered()){
                list.add(formDrop(victim, new ItemStack(xe,1,8)));
            }
            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                list.add(formDrop(victim, new ItemStack(xe,1,3)));
            }
        } else if(victim instanceof EntitySpider){
            if(reliquarist_sword.countReliqueDrop(weapon,8)){
                list.add(formDrop(victim, new ItemStack(xe,1,2)));
            }
        } else if(victim instanceof EntityBat){
            int a = 1;
            if(reliquarist_sword.countReliqueDrop(weapon,4)) a++;
            list.add(formDrop(victim, new ItemStack(xe,a,5)));
        } else if(victim instanceof EntitySquid){
            if(reliquarist_sword.countReliqueDrop(weapon,6)){
                list.add(formDrop(victim, new ItemStack(xe,1,12)));
            }
        } else if(victim.getClass().equals(EntitySlime.class)){
            if(((EntitySlime)victim).isSmallSlime()){
                if(reliquarist_sword.countReliqueDrop(weapon,10)){
                    list.add(formDrop(victim, new ItemStack(xe,1,4)));
                }
            }
        } else if(IntegrationHelper.isLoadedTwilight && victim instanceof EntityTFSlimeBeetle){
            if(reliquarist_sword.countReliqueDrop(weapon,5)){
                list.add(formDrop(victim, new ItemStack(xe,1,4)));
            }
        }
        if(((reliquarist_sword)weapon.getItem()).getLevel()>0){
            if(victim instanceof EntityWitherSkeleton){
                if(reliquarist_sword.countReliqueDrop(weapon,12)){
                    list.add(formDrop(victim, new ItemStack(xe,1,1)));
                }
            } else if(victim instanceof EntityWitch){
                if(reliquarist_sword.countReliqueDrop(weapon,10)){
                    list.add(formDrop(victim, new ItemStack(Item.getByNameOrId("xreliquary:witch_hat"),1)));
                }
            } else if(victim instanceof EntityGuardian){
                if(reliquarist_sword.countReliqueDrop(weapon,8)){
                    list.add(formDrop(victim, new ItemStack(xe,1,16)));
                }
            } else if(victim instanceof EntitySnowman){
                if(reliquarist_sword.countReliqueDrop(weapon,8)){
                    list.add(formDrop(victim, new ItemStack(xe,1,10)));
                }
            }
        }
        if(((reliquarist_sword)weapon.getItem()).getLevel()>1){
            if(victim instanceof EntityBlaze){
                if(reliquarist_sword.countReliqueDrop(weapon,12)){
                    list.add(formDrop(victim, new ItemStack(xe,1,7)));
                }
            } else if(victim instanceof EntityEnderman){
                if(reliquarist_sword.countReliqueDrop(weapon,12)){
                    list.add(formDrop(victim, new ItemStack(xe,1,11)));
                }
            }
        }
    }


    public static EntityItem formDrop(Entity e, ItemStack i){
        return new EntityItem(e.world,e.posX,e.posY,e.posZ,i);
    }
}
