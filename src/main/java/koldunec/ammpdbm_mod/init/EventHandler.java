package koldunec.ammpdbm_mod.init;



import com.sun.media.jfxmedia.logging.Logger;
import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.another_dye_please_dont_blame_me;
import koldunec.ammpdbm_mod.broomitems.flints;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.init.PotionRegister;
import koldunec.ammpdbm_mod.utils.dragonBreathFix;
import koldunec.ammpdbm_mod.utils.tipped_crafting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeTippedArrow;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.*;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.OreDictionary;
import twilightforest.block.BlockTFLeaves;
import twilightforest.block.BlockTFMagicLeaves;

import org.apache.logging.log4j.LogManager;

import static java.lang.Math.round;


@Mod.EventBusSubscriber
public class EventHandler{

    @SubscribeEvent
    public static void ModelsFor(ModelRegistryEvent event){
        final Item Dye = ItemRegister.ANOTHER_DYE;
        for (another_dye_please_dont_blame_me.dyeTypes type : another_dye_please_dont_blame_me.dyeTypes.values()){
            ModelLoader.setCustomModelResourceLocation(Dye, type.ordinal(), new ModelResourceLocation(Dye.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        }
        final Item flint = ItemRegister.MAGIC_FLINTS;
        for(flints.flintTypes type : flints.flintTypes.values()){
            ModelLoader.setCustomModelResourceLocation(flint, type.ordinal(), new ModelResourceLocation(flint.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        }
    }

    @SubscribeEvent
    public static void registerSupercuringCraft(RegistryEvent.Register<IRecipe> event){
        tipped_crafting a = new tipped_crafting();
        a.setRegistryName(ammpdbm_mod.MODID,"supercuregrass");
        event.getRegistry().register(a);

        dragonBreathFix b = new dragonBreathFix();
        b.setRegistryName(ammpdbm_mod.MODID,"tippedarrow");
        event.getRegistry().register(b);
    }

    @SubscribeEvent
    public static void fuel(FurnaceFuelBurnTimeEvent e) {
        if (e.getItemStack().getItem() == Item.getItemFromBlock(BlockRegister.BLOCK_CHARCOAL))
            e.setBurnTime(2000);
        if (e.getItemStack().getItem() == Items.EGG)
            e.setBurnTime(1);
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent e){
        /*
        if(e.crafting.getItem().equals(ItemRegister.ESSENCE_RAINBOW))
            for(int i=0;i<9;i++)
                if(e.craftMatrix.getStackInSlot(i).getItem().equals(ItemRegister.SPARKLES)) {
                    e.craftMatrix.getStackInSlot(i).grow(1);
                    break;
                }
                */
        if(e.crafting.getItem().equals(ItemRegister.RUNIC_STICK))
            for(int i=0;i<9;i++){
                Item ii = e.craftMatrix.getStackInSlot(i).getItem();
                if(ii.equals(Items.AIR)) continue;
                if(ii.equals(ItemRegister.MAGIC_FLINTS))
                    e.craftMatrix.getStackInSlot(i).grow(1);
            }
    }

    //0 - black
    //2 - green
    //3 - brown
    //4 - blue
    //15 - white


    @SubscribeEvent
    public static void blockBr(BlockEvent.HarvestDropsEvent e) {
        EntityPlayer player = (EntityPlayer) e.getHarvester();
        if(!e.getWorld().isRemote){
            if(player!=null){
                if(e.getState().getBlock().equals(BlockRegister.ORE_RAINBOW)){
                    e.getDrops().add(new ItemStack(Items.DYE,1,1));
                    for(int ii=5; ii<15; ii++){
                        e.getDrops().add(new ItemStack(Items.DYE,1,ii));
                    }
                    for(int ii=0; ii<5; ii++){
                        e.getDrops().add(new ItemStack(ItemRegister.ANOTHER_DYE,1,ii));
                    }
                }
                ItemStack itemstack = player.getHeldItemMainhand();
                if(itemstack!=null)
                    if(itemstack.getItem().equals(ItemRegister.BROOM)&(e.getState().getBlock().equals(Blocks.BOOKSHELF)|e.getState().getBlock().equals(Blocks.ENDER_CHEST))){
                        e.getDrops().clear();
                        e.getDrops().add(new ItemStack(e.getState().getBlock()));
                    }
            }
            if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest")){
                if(e.getState().getBlock() instanceof  BlockTFMagicLeaves)
                    if((((BlockTFMagicLeaves)e.getState().getBlock()).func_176201_c(e.getState())&3)==1){
                        if(!e.getDrops().contains(new ItemStack(Item.getItemFromBlock(e.getState().getBlock())))) {
                            e.getDrops().clear();
                            if(ammpdbm_mod.random.nextInt(4)==0)
                                e.getDrops().add(new ItemStack(ItemRegister.TRANSFORMATION_DUST));
                        }
                }
                if(e.getState().getBlock() instanceof BlockTFLeaves && ((BlockTFLeaves) e.getState().getBlock()).func_180651_a(e.getState())==9){
                    if(!e.getDrops().contains(new ItemStack(Item.getItemFromBlock(e.getState().getBlock())))) {
                        if(ammpdbm_mod.random.nextInt(3)==0)
                            e.getDrops().add(new ItemStack(ItemRegister.ESSENCE_RAINBOW));
                    }
                }
            }
        }
    }



    @SubscribeEvent
    public static void damageDeconnstructor(LivingHurtEvent e){

        EntityLivingBase victim = e.getEntityLiving();
        Entity enemy = e.getSource().getTrueSource();
        DamageSource damage_type = e.getSource();

        if(victim.getActivePotionMap().containsKey(PotionRegister.ENDERPROTECTION) &&
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
                e.setAmount(Math.round(e.getAmount()*Math.min(1-0.1*ampl,0)));
            }
        }
        if(e.getSource().isMagicDamage()){
            if(e.getEntityLiving().isPotionActive(PotionRegister.MAGICPROTECTION)){
                int a = e.getEntityLiving().getActivePotionMap().get(PotionRegister.MAGICPROTECTION).getAmplifier();
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
            } else if((net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest")&(e.getEntity() instanceof EntityPlayer))){
                EntityPlayer player = (EntityPlayer) e.getEntity();
                Item curer = ItemRegister.SPARKLES;
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
                if(e.getEntityLiving() instanceof EntityIronGolem){
                    if(!e.getEntityLiving().getEntityWorld().isRemote){
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
                if(e.getEntityLiving() instanceof EntitySnowman){
                    if(!e.getEntityLiving().getEntityWorld().isRemote){
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
                if(e.getEntityLiving() instanceof EntitySpider | e.getEntityLiving() instanceof EntitySilverfish){
                    e.getEntityLiving().hurtResistantTime = 0;
                    e.getEntityLiving().attackEntityFrom(DamageSource.GENERIC,10);
                }
            }
        }
    }




    @SubscribeEvent
    public void onLootTablesLoaded(LootTableLoadEvent e) {
        if(net.minecraftforge.fml.common.Loader.isModLoaded("twilightforest")) {
            ResourceLocation r = new ResourceLocation("twilightforest","structures/hill_1/common");
            if (e.getName().equals(r)) {
                final LootPool pool2 = e.getTable().getPool("main");
                pool2.addEntry(new LootEntryItem(ItemRegister.TRANSFORMATION_DUST, 1, 0, new LootFunction[] {new SetCount(new LootCondition[0], new RandomValueRange(1, 5))}, new LootCondition[0], "loottable:dusttras"));
            }
        }

        if(e.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)) {
            ItemStack a  = new ItemStack(Items.SPAWN_EGG);
            ItemMonsterPlacer.applyEntityIdToItemStack(a,new ResourceLocation("minecraft","cave_spider"));
            //e.getTable().getPool("main").removeEntry("minecraft:arrow");
            e.getTable().getPool("main").setRolls(new RandomValueRange(2,2));
            e.getTable().getPool("main").addEntry(new LootEntryItem(Items.TIPPED_ARROW, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(5, 10)), new SetNBT(new LootCondition[0], PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionRegister.MINDDEVOUR_TYPE_STANDARD).getTagCompound())}, new LootCondition[0], "loottable:tipped_surprise"));
            e.getTable().getPool("main").addEntry(new LootEntryItem(Items.SPAWN_EGG, 1000, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3)), new SetNBT(new LootCondition[0],  a.getTagCompound())}, new LootCondition[0], "loottable:spider_surprise"));
        }
        if(LootTableList.CHESTS_IGLOO_CHEST.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,10,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(3,7))}, new LootCondition[0],"loottable:supergrass"));
        }
        if(LootTableList.CHESTS_VILLAGE_BLACKSMITH.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,5,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(3,7))}, new LootCondition[0],"loottable:supergrass"));
        }
        if(LootTableList.CHESTS_DESERT_PYRAMID.equals(e.getName())){
            e.getTable().getPool("pool1").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,10,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1,3))}, new LootCondition[0],"loottable:supergrass"));
        }
        if(LootTableList.GAMEPLAY_FISHING_JUNK.equals(e.getName())){
            e.getTable().getPool("main").addEntry(new LootEntryItem(ItemRegister.SUPERCURING_GRASS,5,0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1,3))}, new LootCondition[0],"loottable:supergrass"));
        }
        //if (LootTableList.CHESTS_SPAWN_BONUS_CHEST.equals(e.getName()))
        //{
            //e.setTable(e.getLootTableManager().getLootTableFromLocation(new ResourceLocation("minecraft", "chests/jungle_temple_dispenser")));
        //}
    }

}