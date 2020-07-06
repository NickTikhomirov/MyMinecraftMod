
package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.init.BlockRegister;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.ROutputAurora;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RepairRecipe;
import koldunec.vint.utils.routers.Sidemod_Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

@SuppressWarnings("ConstantConditions")
public class TwilightTransmutationsData {
    public static void initCinder(){
        ItemStack CINDER = new ItemStack(Item.getByNameOrId("twilightforest:lamp_of_cinders"));
        DefaultTransform(new ItemStack(Blocks.CLAY,1), CINDER, new ItemStack(Item.getByNameOrId("twilightforest:maze_stone"),1,0),500);
        DefaultTransform(new ItemStack(Blocks.CHEST), CINDER, new ItemStack(BlockRegister.STORE,1), 250);
        DefaultTransform(new ItemStack(Items.ROTTEN_FLESH), CINDER, new ItemStack(Item.getByNameOrId("twilightforest:raw_meef"),1));
        DefaultTransform(new ItemStack(Sidemod_Items.TwilightSapling(), 1, 0), CINDER, new ItemStack(Sidemod_Items.TwilightSapling(),1, 4));
        DefaultTransform(new ItemStack(ItemRegister.AURORA_CORE),CINDER, new ItemStack(Sidemod_Items.ConduitHeart(), 1), 250);
        DefaultTransform(new ItemStack(Items.DRAGON_BREATH), CINDER, new ItemStack(Sidemod_Items.FieryTears()));
    }


    public static void initCarminite(){
        ItemStack CARMINITE = new ItemStack(Item.getByNameOrId("twilightforest:carminite"));
        DefaultTransform(new ItemStack(Blocks.COAL_BLOCK), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:fire_jet"),1,0));
        DefaultTransform(new ItemStack(Blocks.MAGMA), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:fire_jet"),1,3));
        DefaultTransform(new ItemStack(Blocks.DIRT,1,0), CARMINITE, new ItemStack(Blocks.DIRT,1,2));

        DefaultTransform(new ItemStack(Items.COOKED_BEEF), CARMINITE, new ItemStack(Sidemod_Items.Experiment_115(),1));
        DefaultTransform(new ItemStack(Items.COOKED_MUTTON), CARMINITE, new ItemStack(Sidemod_Items.Experiment_115(),1));
        DefaultTransform(new ItemStack(Item.getByNameOrId("twilightforest:cooked_meef")), CARMINITE, new ItemStack(Sidemod_Items.Experiment_115(),1));
        DefaultTransform(new ItemStack(Sidemod_Items.VenisonCook()), CARMINITE, new ItemStack(Sidemod_Items.Experiment_115(),1));

        DefaultTransform(new ItemStack(Blocks.WOOL, 1, 0), CARMINITE, new ItemStack(BlockRegister.FAKE_IRON));
        DefaultTransform(new ItemStack(Blocks.WOOL, 1, 4), CARMINITE, new ItemStack(BlockRegister.FAKE_GOLD));
        DefaultTransform(new ItemStack(Blocks.WOOL, 1, 3), CARMINITE, new ItemStack(BlockRegister.FAKE_DIAMOND));
        DefaultTransform(new ItemStack(Blocks.WOOL, 1, 7), CARMINITE, new ItemStack(BlockRegister.FAKE_NETHERITE));
        DefaultTransform(new ItemStack(Blocks.WOOL, 1, 5), CARMINITE, new ItemStack(BlockRegister.FAKE_EMERALD));
    }


    public static void initTransform(){
        ItemStack TRANSFORM = new ItemStack(Item.getByNameOrId("twilightforest:magic_log_core"),1, 1);

        DefaultTransform(new ItemStack(Items.COOKED_BEEF), TRANSFORM, new ItemStack(Sidemod_Items.VenisonCook(), 1));
        DefaultTransform(new ItemStack(BlockRegister.STORE),TRANSFORM, new ItemStack(Item.getByNameOrId("twilightforest:charm_of_keeping_2")));
        DefaultTransform(new ItemStack(TFItems.ironwood_ingot), TRANSFORM, new ItemStack(TFItems.steeleaf_ingot));
        DefaultTransform(new ItemStack(Blocks.WATERLILY), TRANSFORM, new ItemStack(TFBlocks.huge_lilypad));
        DefaultTransform(new ItemStack(TFBlocks.huge_lilypad), TRANSFORM, new ItemStack(TFBlocks.huge_waterlily));

        DefaultTransform(new ItemStack(Blocks.STONEBRICK, 1, 0), TRANSFORM, new ItemStack(TFBlocks.naga_stone, 1, 1));
        DefaultTransform(new ItemStack(Blocks.STONEBRICK, 1, 3), TRANSFORM, new ItemStack(TFBlocks.naga_stone,1,0));
        DefaultTransform(new ItemStack(Blocks.STONE_BRICK_STAIRS, 1), TRANSFORM, new ItemStack(TFBlocks.nagastone_stairs,1,0));

        DefaultTransform(new ItemStack(Blocks.SAPLING, 1, 0), TRANSFORM, new ItemStack(TFBlocks.twilight_sapling, 1, 0));   //oak to sickly oak
        DefaultTransform(new ItemStack(Blocks.SAPLING, 1, 2), TRANSFORM, new ItemStack(TFBlocks.twilight_sapling, 1, 1));   //birch to canopy
        DefaultTransform(new ItemStack(Blocks.SAPLING, 1, 3), TRANSFORM, new ItemStack(TFBlocks.twilight_sapling, 1, 1));   //jungle to canopy
        DefaultTransform(new ItemStack(Blocks.SAPLING, 1, 4), TRANSFORM, new ItemStack(TFBlocks.twilight_sapling, 1, 2));   //acacia to mangrove
        DefaultTransform(new ItemStack(TFBlocks.twilight_sapling, 1, 2), TRANSFORM, new ItemStack(TFBlocks.twilight_sapling, 1, 6), 1000);
    }


    public static void initDragonBreath(){
        DefaultTransform(new ItemStack(Blocks.STONE), new ItemStack(Items.DRAGON_BREATH), new ItemStack(Blocks.END_STONE), 200);
        DefaultTransform(new ItemStack(Sidemod_Items.Fan(), 1, 0), new ItemStack(Items.DRAGON_BREATH), new ItemStack(Items.ELYTRA), 200);
        if(IntegrationHelper.isLoadedQuark)
            DefaultTransform(new ItemStack(Items.QUARTZ), new ItemStack(Items.DRAGON_BREATH), new ItemStack(Item.getByNameOrId("quark:biotite")));
//            TransmutationsRegister.put(
//                    new ItemStack(TFItems.naga_scale),
//                    new RecipeOutputConsumeCatalyst(new ItemStack(Item.getByNameOrId("quark:enderdragon_scale")), 100),
//                    new ItemStack(Items.DRAGON_BREATH)
//            );
    }


    public static void initIce(){
        ItemStack ICE_CATALYST = new ItemStack(ItemRegister.AURORA_CORE);
        DefaultTransform(new ItemStack(Blocks.SNOW), ICE_CATALYST, new ItemStack(Blocks.PACKED_ICE, 1));
        DefaultTransform(new ItemStack(Blocks.STONEBRICK, 1, 3), ICE_CATALYST, new ItemStack(Sidemod_Items.Aurora(true)));
        DefaultTransform(new ItemStack(Blocks.GLASS), ICE_CATALYST, new ItemStack(Sidemod_Items.Aurora(false)));
        DefaultTransform(new ItemStack(Items.FIRE_CHARGE), ICE_CATALYST, new ItemStack(TFItems.ice_bomb));
    }

    public static void initConduit(){
        ItemStack CONDUIT = new ItemStack(Sidemod_Items.ConduitHeart());

        DefaultTransform(new ItemStack(Items.GLOWSTONE_DUST), CONDUIT, new ItemStack(Items.PRISMARINE_CRYSTALS));
        DefaultTransform(new ItemStack(Items.QUARTZ), CONDUIT, new ItemStack(Items.PRISMARINE_SHARD));
        DefaultTransform(new ItemStack(Blocks.NETHER_WART_BLOCK), CONDUIT, new ItemStack(Blocks.SPONGE));
        DefaultTransform(new ItemStack(BlockRegister.WARPED_WART), CONDUIT, new ItemStack(Blocks.SPONGE));
        DefaultTransform(new ItemStack(Sidemod_Items.Focus()), CONDUIT, new ItemStack(Sidemod_Items.ConduitShell()));
    }


    public static void initIntegration(){
        ItemStack TRANSFORM = new ItemStack(Item.getByNameOrId("twilightforest:magic_log_core"),1, 1);

        if(IntegrationHelper.isLoadedCharm){
            TransmutationsRegister.put(
                    new RecipeInput(new ItemStack(Item.getByNameOrId("charm:charged_emerald")), new ItemStack(ItemRegister.FROZEN_CORE)),
                    new ROutputAurora()
            );
        }
        if(IntegrationHelper.isLoadedQuark){
            DefaultTransform(new ItemStack(Item.getByNameOrId("quark:root")), TRANSFORM, new ItemStack(Item.getByNameOrId("twilightforest:liveroot")));
            for(int i=0; i<3; ++i)
                DefaultTransform(new ItemStack(Item.getByNameOrId("quark:root_flower"), 1, i), TRANSFORM, new ItemStack(Item.getByNameOrId("twilightforest:liveroot")));
        }

    }

    public static void initRepairs(){
        TransmutationsRegister.RECIPES_2.add(new RepairRecipe(TFItems.crumble_horn, new ItemStack(TFBlocks.magic_log, 1, 2), 32));
    }


    public static void DefaultTransform(ItemStack base, ItemStack catalyst, ItemStack result){
        TransmutationsRegister.put(base,new RecipeOutput(result,100),catalyst);
    }

    public static void DefaultTransform(ItemStack base, ItemStack catalyst, ItemStack result, int time){
        TransmutationsRegister.put(base,new RecipeOutput(result,time),catalyst);
    }

    public static void DefaultTransform(RecipeInput input, ItemStack result){
        TransmutationsRegister.put(input,new RecipeOutput(result,100));
    }

    public static void DefaultTransform(RecipeInput input, ItemStack result, int time){
        TransmutationsRegister.put(input,new RecipeOutput(result,time));
    }
}
