package koldunec.vint.events;



import com.progwml6.natura.entities.entity.monster.EntityNitroCreeper;
import koldunec.vint.items.magic_shovel;
import koldunec.vint.vint;
import koldunec.vint.items.tools.reliquarist_sword;
import koldunec.vint.items.xyAmulet;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.init.PotionRegister;
import net.daveyx0.primitivemobs.entity.monster.EntityVoidEye;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import teamroots.embers.entity.EntityAncientGolem;

import twilightforest.block.BlockTFLeaves;
import twilightforest.block.BlockTFMagicLeaves;
import twilightforest.block.BlockTFMagicLogSpecial;
import twilightforest.item.ItemTFMinotaurAxe;


import static java.lang.Math.round;
import static koldunec.vint.init.PotionRegister.ENDERPROTECTION;
import static koldunec.vint.init.PotionRegister.WITHERPROTECTION;


@Mod.EventBusSubscriber
public class EventHandler{

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onEntityHello(EntityJoinWorldEvent e){
        if(e.getEntity()!=null && e.getEntity() instanceof EntityLiving){
            EntityLiving one = (EntityLiving)e.getEntity();
            if(one.getClass().equals(EntityZombie.class) && vint.random.nextInt(10)==0){
                one.tasks.addTask(1,new EntityAILeapAtTarget(one, 0.5F));
                return;
            }
            if(e.getWorld().provider instanceof WorldProviderHell){
                if(one.getClass().equals(EntitySkeleton.class)){
                    e.setCanceled(true);
                    return;
                }
                if(vint.isLoadedNatura){
                    if(one.getClass().equals(EntityNitroCreeper.class)){
                        e.setCanceled(true);
                        return;
                    }
                }
                if(vint.isLoadedHype){
                    if(one.getClass().equals(EntityPigZombie.class) && vint.random.nextInt(10)==0){
                        one.setItemStackToSlot(EntityEquipmentSlot.CHEST,new ItemStack(Item.getByNameOrId("hypewear:aggc_chestplate"),1,0));
                        one.setItemStackToSlot(EntityEquipmentSlot.LEGS,new ItemStack(Item.getByNameOrId("hypewear:castle_leggings"),1,0));
                        return;
                    }
                }
            }
            if(one.getClass().equals(EntitySkeleton.class)){
                if(vint.random.nextInt(10)!=0){
                    one.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BONE,1));
                    one.tasks.addTask(1,new EntityAILeapAtTarget(one, 0.4F));
                    for(EntityAITasks.EntityAITaskEntry a: one.targetTasks.taskEntries){
                        if(a.action instanceof EntityAINearestAttackableTarget){
                            one.targetTasks.taskEntries.remove(a);
                            break;
                        }
                    }
                    one.targetTasks.addTask(2,new EntityAINearestAttackableTarget((EntityCreature) one,EntityZombie.class,true));
                    //one.targetTasks.addTask(2,new EntityAINearestAttackableTarget((EntityCreature) one,EntityCreeper.class,true));
                    one.tasks.addTask(1,new EntityAILeapAtTarget(one, 0.5F));
                    return;
                }
                if(vint.random.nextInt(10)==0){
                    return;
                }
            }
        }
    }

    //Note forge has two different events called playerevent (why?!)
    //So it was kinda difficult to figure out why it doesnt work
    @SubscribeEvent
    public static void onDecide(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed e){
        if(vint.isLoadedBaubles){
            //in theory entityliving is always a player so lets try not to check excessively
            if(e.getEntityLiving()!=null){
                EntityPlayer p = (EntityPlayer) e.getEntityLiving();
                if(magic_shovel.isEquiped(p)){
                    if(magic_shovel.isHarvestable(e.getState().getBlock())){
                        e.setNewSpeed(e.getNewSpeed()+10F);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent e){
        Item result = e.crafting.getItem();

        if(result.equals(ItemRegister.RUNIC_STICK) ||
           result.equals(ItemRegister.ROUND_STONE) ||
           result.equals(ItemRegister.CURINGSEEDS) ||
           result.equals(ItemRegister.WOODEN_RUNE) ||
           result.equals(Items.GHAST_TEAR) ||
           result.equals(Item.getItemFromBlock(Blocks.LOG)) ||
           result.equals(Item.getItemFromBlock(Blocks.PLANKS)))
            for(int i=0;i<9;i++){
                Item ii = e.craftMatrix.getStackInSlot(i).getItem();
                if(ii.equals(ItemRegister.MAGIC_FLINTS))
                    e.craftMatrix.getStackInSlot(i).grow(1);
            }
    }

    //blockBr is kinda unreadable for now so ill substract this
    @SubscribeEvent
    public static void magic_shovel_code(BlockEvent.BreakEvent e) {
        if(vint.isLoadedBaubles){
            if(e.getPlayer()!=null){
                if(magic_shovel.isEquiped(e.getPlayer())){
                    if(magic_shovel.isHarvestable(e.getState().getBlock())){
                        magic_shovel.damageShovel(e.getPlayer(),1);
                        magic_shovel.attemptToFix(e.getPlayer());
                    }
                }
            }
        }
    }

    //0 - black
    //2 - green
    //3 - brown
    //4 - blue
    //15 - white


    @SubscribeEvent
    public static void blockBr(BlockEvent.HarvestDropsEvent e) {
        if(e.getWorld().isRemote) return;
        EntityPlayer player = (EntityPlayer) e.getHarvester();
        if(player!=null){
            ItemStack itemstack = player.getHeldItemMainhand();
            ItemStack itemleft = player.getHeldItemOffhand();
            if(!itemstack.isEmpty())
                if(itemstack.getItem().equals(ItemRegister.BROOM)&(e.getState().getBlock().equals(Blocks.BOOKSHELF)|e.getState().getBlock().equals(Blocks.ENDER_CHEST))){
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(e.getState().getBlock()));
                } else if(itemstack.getItem().equals(ItemRegister.MAGNETPICK)){
                    if(e.getState().getBlock().equals(Blocks.COBBLESTONE) ||
                       e.getState().getBlock().equals(Blocks.STONEBRICK)){
                        for(ItemStack a: e.getDrops()){
                            if(a.getItem().equals(Item.getItemFromBlock(Blocks.COBBLESTONE)) ||
                                a.getItem().equals(Item.getItemFromBlock(Blocks.STONEBRICK))){
                                ItemStack b = a.copy();
                                if(!player.inventory.addItemStackToInventory(a))
                                    player.dropItem(a,false);
                                e.getDrops().remove(a);
                            }
                        }
                    } else if(e.getState().getBlock().equals(Blocks.LOG)){
                        e.getDrops().clear();
                        ItemStack pick = e.getHarvester().getHeldItemMainhand();
                        pick.setItemDamage(Math.max(0,pick.getItemDamage()-20));
                    }
                } else if(vint.isLoadedTwilight &&
                        (itemstack.getItem().equals(ItemRegister.CARMINITE_AXE)) ||
                        itemstack.getItem() instanceof ItemTFMinotaurAxe){
                    if(e.getState().getBlock() instanceof BlockTFMagicLogSpecial) {
                        e.getDrops().clear();
                        e.getDrops().add(
                                new ItemStack(
                                        e.getState().getBlock(),
                                        1,
                                        e.getState().getBlock().getMetaFromState(e.getState())));
                    }
                }
                if(xyAmulet.isEquiped(player) && !e.isSilkTouching()){
                    if(e.getState().getBlock().equals(Blocks.REDSTONE_ORE)){
                        if(vint.random.nextInt(20)==0){
                            e.getDrops().add(new ItemStack(Item.getByNameOrId("projectx:xycronium_crystal"),1,2));
                        }
                    } else if(e.getState().getBlock().equals(Blocks.COAL_ORE)){
                        if(vint.random.nextInt(100)==0){
                            e.getDrops().add(new ItemStack(Item.getByNameOrId("projectx:xycronium_crystal"),1,3));
                        }
                    } else if(e.getState().getBlock().equals(Blocks.QUARTZ_ORE)){
                        if(vint.random.nextInt(10)==0){
                            e.getDrops().add(new ItemStack(Item.getByNameOrId("projectx:xycronium_crystal"),1,4));
                        }
                    } else if(e.getState().getBlock().equals(BlockRegister.ORE_RAINBOW)){
                        for(int i=0; i<5;i++){
                            e.getDrops().add(
                                    new ItemStack(Item.getByNameOrId("projectx:xycronium_crystal"),
                                    vint.random.nextInt(2)+1,
                                    i));
                        }
                    } else if(e.getState().getBlock().equals(Blocks.DIAMOND_ORE)){
                        e.getDrops().add(new ItemStack(Item.getByNameOrId("projectx:xycronium_crystal"),1,4));
                    } else if(e.getState().getBlock().equals(Blocks.EMERALD_ORE)){
                        e.getDrops().add(new ItemStack(Item.getByNameOrId("projectx:xycronium_crystal"),2,1));
                    }
                }
        }

        if(e.getState().getBlock().equals(BlockRegister.ORE_RAINBOW)){
            e.getDrops().add(new ItemStack(Items.DYE,1,1));                 //red
            e.getDrops().add(new ItemStack(Items.DYE,1,5));                 //purple
            e.getDrops().add(new ItemStack(Items.DYE,1,11));                //yellow
            e.getDrops().add(new ItemStack(Items.DYE,1,12));                //light_blue
            e.getDrops().add(new ItemStack(Items.DYE,1,14));                //orange
            e.getDrops().add(new ItemStack(ItemRegister.ANOTHER_DYE,1,1));  //green
            e.getDrops().add(new ItemStack(ItemRegister.ANOTHER_DYE,1,3));  //blue
        }

        if(vint.isLoadedTwilight){
            if(e.getState().getBlock() instanceof BlockTFMagicLeaves)
                if((((BlockTFMagicLeaves)e.getState().getBlock()).func_176201_c(e.getState())&3)==1){
                    if(!e.getDrops().contains(new ItemStack(Item.getItemFromBlock(e.getState().getBlock())))) {
                        e.getDrops().clear();
                        if(vint.random.nextInt(4)==0)
                            e.getDrops().add(new ItemStack(ItemRegister.TRANSFORMATION_DUST));
                    }
                }
            if(e.getState().getBlock() instanceof BlockTFLeaves && ((BlockTFLeaves) e.getState().getBlock()).func_180651_a(e.getState())==9){
                if(!e.getDrops().contains(new ItemStack(Item.getItemFromBlock(e.getState().getBlock())))) {
                    if(vint.random.nextInt(3)==0)
                        e.getDrops().add(new ItemStack(ItemRegister.ESSENCE_RAINBOW));
                }
            }
        }
    }



    @SubscribeEvent
    public static void damageDeconnstructor(LivingHurtEvent e){

        EntityLivingBase victim = e.getEntityLiving();
        Entity enemy = e.getSource().getTrueSource();
        DamageSource damage_type = e.getSource();

        if(vint.isLoadedPrimitive){
            if(vint.isLoadedProjectRed_exploration && e.getEntityLiving() instanceof EntityVoidEye){
                if(enemy instanceof EntityPlayer && !((EntityPlayer)enemy).getHeldItemMainhand().isEmpty()){
                    if(isAthame(((EntityPlayer) e.getSource().getTrueSource()).getHeldItemMainhand()))
                        e.setAmount(25F);
                }
            }
        }

        if(net.minecraftforge.fml.common.Loader.isModLoaded("embers")){
            if(victim instanceof EntityAncientGolem){
                if(enemy instanceof EntityPlayer){
                    ItemStack hand = ((EntityPlayer) enemy).getHeldItemMainhand();
                    if(!hand.isEmpty()){
                        if(hand.getItem() instanceof reliquarist_sword){
                            e.setAmount(25F);
                        }
                    }
                }
            }
        }

        if(victim.isPotionActive(PotionRegister.ENDERPROTECTION) &&
                (enemy instanceof EntityEnderman ||
                 enemy instanceof EntityEndermite ||
                 enemy instanceof EntityEnderCrystal ||
                 enemy instanceof EntityDragon ||
                 enemy instanceof EntityShulker)){
            if(enemy instanceof EntityEndermite) {
                e.setAmount(0);
                enemy.attackEntityFrom(DamageSource.causeIndirectMagicDamage(victim, victim),100F);
            } else {
                int ampl = victim.getActivePotionEffect(PotionRegister.ENDERPROTECTION).getAmplifier();
                if(enemy instanceof EntityDragon){
                    e.setAmount(Math.max(e.getAmount()-(ampl+1)*2,0));
                }
                if(enemy instanceof EntityEnderman){
                    e.setAmount(Math.max(e.getAmount()-Math.round(Math.pow(2,ampl)),0));
                }
                if(enemy instanceof EntityEnderCrystal){
                    e.setAmount(0);
                }
                if(enemy instanceof EntityShulker){
                    int newDamage = (int)(e.getAmount() - 2*ampl);
                    e.setAmount(Math.max(0,newDamage));
                    e.getSource().getTrueSource().attackEntityFrom(DamageSource.causeIndirectMagicDamage(e.getEntity(),null),newDamage);
                }
            }
        }
        if(victim.isPotionActive(PotionRegister.ENDERPROTECTION) &&
            damage_type.equals(DamageSource.ANVIL)){
            int ampl = victim.getActivePotionEffect(PotionRegister.ENDERPROTECTION).getAmplifier();
            e.getEntityLiving().addPotionEffect(
                    new PotionEffect(
                            ENDERPROTECTION,
                            Math.min(12000,e.getEntityLiving().getActivePotionEffect(ENDERPROTECTION).getDuration()*(ampl+1)),ampl));
        }
        if(victim.isPotionActive(WITHERPROTECTION) && e.getSource().equals(DamageSource.WITHER)){
            int ampl = victim.getActivePotionEffect(WITHERPROTECTION).getAmplifier();
            if(ampl==0) e.setAmount(e.getAmount()*1.0F/2);
            else e.setAmount(0);
        }
        if(victim.isPotionActive(WITHERPROTECTION) && e.getSource().isExplosion()){
            if(victim.getActivePotionEffect(WITHERPROTECTION).getAmplifier()>0)
                victim.addPotionEffect(new PotionEffect(
                        WITHERPROTECTION,
                        victim.getActivePotionEffect(WITHERPROTECTION).getDuration()+600,
                        victim.getActivePotionEffect(WITHERPROTECTION).getAmplifier()));
        }
        if(victim.isPotionActive(PotionRegister.HUMANITY)){
            if(e.getSource().getTrueSource() instanceof EntityZombie){
                e.setAmount(
                        e.getAmount() +
                                victim.getEntityWorld().provider.getDimension()==-1?-1:-2 -
                                victim.getActivePotionEffect(PotionRegister.HUMANITY).getAmplifier()*2);
            }
        }
        if(damage_type.isMagicDamage()){
            if(victim.isPotionActive(PotionRegister.MAGICPROTECTION)){
                int a = victim.getActivePotionEffect(PotionRegister.MAGICPROTECTION).getAmplifier();
                if(a==0) {
                    DamageSource nn = DamageSource.IN_FIRE;
                    nn.setFireDamage();
                    e.getEntityLiving().hurtResistantTime = 0;
                    e.getEntityLiving().attackEntityFrom(nn, e.getAmount());
                } else if(a==1) {
                    DamageSource nn = DamageSource.IN_FIRE;
                    nn.setFireDamage();
                    e.getEntityLiving().hurtResistantTime = 0;
                    e.getEntityLiving().attackEntityFrom(nn, e.getAmount()-2);
                }
                e.setAmount(0);
            } else if(vint.isLoadedTwilight & victim instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) e.getEntity();
                Item curer = ItemRegister.MAGIC_PROTECTOR;
                if(player.getHeldItem(EnumHand.OFF_HAND).getItem().equals(curer)) {
                    e.setAmount(0);
                    ItemStack c = player.getHeldItem(EnumHand.OFF_HAND);
                    c.shrink(1);
                    if(c.isEmpty()) player.inventory.deleteStack(c);
                } else {
                    int i = 0;
                    for(; i<9; i++){
                        if(player.inventory.getStackInSlot(i).getItem().equals(curer)){
                            if(i==8) i = 10;
                            break;
                        }
                    }
                    if(i==10) i=8;
                    else if(i<9);
                    else if(player.inventory.getStackInSlot(17).getItem().equals(curer)) i = 17;
                    else if(player.inventory.getStackInSlot(26).getItem().equals(curer)) i = 26;
                    else if(player.inventory.getStackInSlot(35).getItem().equals(curer)) i = 35;
                    if(i!=9){
                        ItemStack c = player.inventory.getStackInSlot(i);
                        e.setAmount(0);
                        c.shrink(1);
                        if(c.isEmpty()) player.inventory.deleteStack(c);
                    }

                }
            }
        }
        if(e.getSource().getTrueSource() instanceof EntityPlayer){
            if(((EntityPlayer)e.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ItemRegister.BROOM)){
                if(victim.getClass().equals(EntityIronGolem.class)){
                    if(!victim.getEntityWorld().isRemote){
                        int px = e.getEntityLiving().getPosition().getX();
                        int py = e.getEntityLiving().getPosition().getY();
                        int pz = e.getEntityLiving().getPosition().getZ();
                        World pw = e.getEntityLiving().world;

                        EntityItem aa = new EntityItem(pw,px,py,pz,
                                (new ItemStack(Blocks.IRON_BLOCK,
                                        round( 4 * (e.getEntityLiving().getHealth()+1) / e.getEntityLiving().getMaxHealth()))));
                        EntityItem bb = new EntityItem(pw,px,py,pz,
                                (new ItemStack(Blocks.PUMPKIN,1)));

                        e.getEntityLiving().world.spawnEntity(aa);
                        e.getEntityLiving().world.spawnEntity(bb);
                        e.getEntity().world.playSound(px,py,pz, SoundEvents.ENTITY_ENDERMEN_SCREAM, SoundCategory.HOSTILE,1.0F,1.0F,true);
                        e.getEntityLiving().setDead();
                    }
                }
                if(victim.getClass().equals(EntitySnowman.class)){
                    if(!victim.getEntityWorld().isRemote){
                        EntityItem aa = new EntityItem(
                                e.getEntity().world,
                                e.getEntityLiving().getPosition().getX(),
                                e.getEntityLiving().getPosition().getY(),
                                e.getEntityLiving().getPosition().getZ(),
                                (new ItemStack(Blocks.SNOW,2)));

                        EntityItem bb = new EntityItem(
                                e.getEntity().world,
                                e.getEntityLiving().getPosition().getX(),
                                e.getEntityLiving().getPosition().getY(),
                                e.getEntityLiving().getPosition().getZ(),
                                (new ItemStack(Blocks.PUMPKIN,1)));
                        e.getEntityLiving().world.spawnEntity(aa);
                        e.getEntityLiving().world.spawnEntity(bb);
                        e.getEntityLiving().setDead();
                    }
                }
                if(victim instanceof EntitySpider | victim instanceof EntitySilverfish){
                    e.getEntityLiving().hurtResistantTime = 0;
                    e.getEntityLiving().attackEntityFrom(DamageSource.GENERIC,10);
                }
            }
            if(((EntityPlayer)e.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ItemRegister.DIAMONDGOLDEN_GOLDEN_DIAMOND_SWORD)){
                e.getSource().setMagicDamage();
            }
        }
    }



    @SubscribeEvent
    public void onTeleport(EnderTeleportEvent e) {
        if(!e.getEntityLiving().isPotionActive(PotionRegister.ENDERPROTECTION)) return;
        if(e.getAttackDamage()>0F){
            e.setAttackDamage(0F);
            e.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY,600));
        }
    }

    public static boolean isAthame(ItemStack i){
        ResourceLocation r = i.getItem().getRegistryName();
        if(r==null) return false;
        if(!r.getResourceDomain().toLowerCase().equals("projectred-exploration")) return false;
        return r.getResourcePath().toLowerCase().equals("athame");
    }
}