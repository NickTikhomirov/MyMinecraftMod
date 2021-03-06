package koldunec.vint.events;


import koldunec.vint.blocks.plants.TorchBerry;
import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.compatibility.natura.NaturaModuleClass;
import koldunec.vint.utils.SpawnCorrector;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.items.tools.Broom;
import koldunec.vint.vint;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.potions.PotionRegister;
import koldunec.vint.world.nether.NaturaDecoratorBerries;
import lumien.randomthings.potion.ModPotions;
import net.daveyx0.primitivemobs.entity.monster.EntityVoidEye;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityEnderCrystal;
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
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.utils.ToolHelper;

import twilightforest.block.BlockTFLeaves;
import twilightforest.block.BlockTFMagicLeaves;
import twilightforest.block.BlockTFMagicLogSpecial;
import twilightforest.item.ItemTFTransformPowder;
import twilightforest.item.TFItems;

import static koldunec.vint.potions.PotionRegister.ENDERPROTECTION;
import static koldunec.vint.potions.PotionRegister.WITHERPROTECTION;


@Mod.EventBusSubscriber
public class VintMainEventHandler{

    @SubscribeEvent
    public static void onClickEntity(PlayerInteractEvent.EntityInteract e){
        if(IntegrationHelper.isLoadedTinkers && !e.isCanceled()){
            EntityPlayer player = e.getEntityPlayer();
            ItemStack weapon = player.getHeldItemMainhand();
            if(ToolHelper.hasCategory(weapon, Category.TOOL)){
                if(ToolHelper.getTraits(weapon).contains(TinkerIntegration.ACTIVATING)){
                    if(e.getTarget() instanceof EntityCreeper) {
                        player.swingArm(EnumHand.MAIN_HAND);
                        ((EntityCreeper) e.getTarget()).ignite();
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock e){
        World world = e.getWorld();
        IBlockState targetstate = world.getBlockState(e.getPos());
        Block target = targetstate.getBlock();
        ItemStack stack = e.getItemStack();
        Item tool = stack.getItem();
        if(IntegrationHelper.isLoadedTwilight){
            //lantern transformation
            if(IntegrationHelper.isLoadedQuark)
                if(!world.isRemote && tool.equals(TFItems.transformation_powder)) {
                    Block lantern = Blocks.SEA_LANTERN;
                    Block old = Block.getBlockFromName("quark:elder_sea_lantern");
                    if (target.equals(lantern)){
                        world.setBlockState(e.getPos(), old.getDefaultState(),1|2);
                        stack.shrink(1);
                    } else if(target.equals(old)){
                        e.getWorld().setBlockState(e.getPos(), lantern.getDefaultState(),1|2);
                        stack.shrink(1);
                    }
                }
            //torchberry plant
            if(tool.equals(TFItems.torchberries))
                if(TorchBerry.checkSoil(targetstate)>0)
                    if(e.getFace().equals(EnumFacing.DOWN) && world.getBlockState(e.getPos().down()).getBlock().equals(Blocks.AIR)){
                        if(!world.isRemote) {
                            stack.shrink(1);
                            world.setBlockState(e.getPos().down(), BlockRegister.TORCH_CROPS.getDefaultState(), 1 | 2);
                        } else {
                            world.playSound(e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS,  1F,1F,false);
                        }
                    }
        }
        if(tool.equals(Items.DYE) && stack.getMetadata()==15){
            if(IntegrationHelper.isLoadedNatura){
                if(target.getRegistryName().toString().equals("natura:nether_glowshroom")){
                    NaturaModuleClass.BuildShroom(target.getMetaFromState(targetstate), world, e.getPos());
                    stack.shrink(1);
                }
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMonsterSpawn(EntityJoinWorldEvent e){
        if(!(e.getEntity() instanceof EntityLiving))
            return;
        EntityLiving one = (EntityLiving)e.getEntity();
        if(one.getClass().equals(EntityZombie.class) && vint.random.nextInt(5)==0){
            one.tasks.addTask(3,new EntityAIOpenDoor(one, false));
            return;
        }
        if(e.getWorld().provider instanceof WorldProviderHell) {
            if(SpawnCorrector.HellCorrector(one))
                e.setCanceled(true);
            return;
        }
        if(one.getClass().equals(EntitySkeleton.class)){
            if(vint.random.nextInt(20)==0){
                if(IntegrationHelper.isLoadedRandomThings){
                    ItemStack a = new ItemStack(Items.TIPPED_ARROW,1);
                    PotionUtils.addPotionToItemStack(a, ModPotions.collapseTypeStrong);
                    one.setItemStackToSlot(EntityEquipmentSlot.OFFHAND,a);
                    if(IntegrationHelper.isLoadedHype) {
                        one.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Item.getByNameOrId("hypewear:baritone_white_chestplate"), 1, 0));
                        one.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Item.getByNameOrId("hypewear:baritone_white_leggings"), 1, 0));
                    }
                }
            }
        }

    }


    @SubscribeEvent
    public static void blockBr(BlockEvent.HarvestDropsEvent e) {
        if(e.getWorld().isRemote) return;
        EntityPlayer player = e.getHarvester();

        if(player!=null){
            ItemStack toolMainHand = player.getHeldItemMainhand();
            if(!toolMainHand.isEmpty())
                if(toolMainHand.getItem() instanceof Broom && Broom.affected_blocks.contains(e.getState().getBlock())){
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(e.getState().getBlock()));
                } else if(IntegrationHelper.isLoadedTwilight &&
                        (toolMainHand.getItem().equals(ItemRegister.CARMINITE_AXE))){
                    if(e.getState().getBlock() instanceof BlockTFMagicLogSpecial) {
                        e.getDrops().clear();
                        e.getDrops().add(
                                new ItemStack(
                                        e.getState().getBlock(),
                                        1,
                                        e.getState().getBlock().getMetaFromState(e.getState())));
                    }
                }

        }

        if(IntegrationHelper.isLoadedTwilight){
            if(e.getState().getBlock() instanceof BlockTFMagicLeaves)
                if((((BlockTFMagicLeaves)e.getState().getBlock()).func_176201_c(e.getState())&3)==1){
                    if(!e.getDrops().contains(new ItemStack(Item.getItemFromBlock(e.getState().getBlock())))) {
                        e.getDrops().clear();
                        if(vint.random.nextInt(4)==0)
                            e.getDrops().add(new ItemStack(ItemRegister.TRANSFORMATION_DUST));
                    }
                }
        }
    }



    @SubscribeEvent
    public static void damageDeconstructor(LivingHurtEvent e){
        EntityLivingBase victim = e.getEntityLiving();
        Entity enemy = e.getSource().getTrueSource();
        DamageSource damage_type = e.getSource();

        if(IntegrationHelper.isLoadedPrimitive){
            if(IntegrationHelper.isLoadedProjectRed_exploration && e.getEntityLiving() instanceof EntityVoidEye){
                if(enemy instanceof EntityPlayer && !((EntityPlayer)enemy).getHeldItemMainhand().isEmpty()){
                    if(isAthame(((EntityPlayer) e.getSource().getTrueSource()).getHeldItemMainhand()))
                        e.setAmount(25F);
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
            } else if(IntegrationHelper.isLoadedTwilight & victim instanceof EntityPlayer){
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