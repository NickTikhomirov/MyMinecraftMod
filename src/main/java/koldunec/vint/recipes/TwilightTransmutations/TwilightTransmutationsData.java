
package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.init.BlockRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TwilightTransmutationsData {
    @SuppressWarnings("ConstantConditions")
    public static void initCinder(){
        ItemStack CINDER = new ItemStack(Item.getByNameOrId("twilightforest:lamp_of_cinders"));
        Item twistone = Item.getByNameOrId("twilightforest:maze_stone");

        DefaultTransform(new ItemStack(Blocks.STONE,1,0), CINDER, new ItemStack(twistone,1,0),500);
        DefaultTransform(new ItemStack(Blocks.STONEBRICK,1,0), CINDER, new ItemStack(twistone,1,1), 500);
        DefaultTransform(new ItemStack(Blocks.STONEBRICK,1,2), CINDER, new ItemStack(twistone,1,4), 500);
        DefaultTransform(new ItemStack(Blocks.STONEBRICK,1,1), CINDER, new ItemStack(twistone,1,5), 500);
        DefaultTransform(new ItemStack(Blocks.STONEBRICK,1,3), CINDER, new ItemStack(twistone,1,3), 500);

        DefaultTransform(new ItemStack(Blocks.CHEST), CINDER, new ItemStack(BlockRegister.STORE,1), 500);
        DefaultTransform(new ItemStack(Items.ROTTEN_FLESH), CINDER, new ItemStack(Item.getByNameOrId("twilightforest:raw_meef"),1));

    }

    @SuppressWarnings("ConstantConditions")
    public static void initCarminite(){
        ItemStack CARMINITE = new ItemStack(Item.getByNameOrId("twilightforest:carminite"));
        DefaultTransform(new ItemStack(Blocks.COAL_BLOCK), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:fire_jet"),1,0));
        DefaultTransform(new ItemStack(Blocks.MAGMA), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:fire_jet"),1,3));
        DefaultTransform(new ItemStack(Blocks.DIRT,1,0), CARMINITE, new ItemStack(Blocks.DIRT,1,2));

        DefaultTransform(new ItemStack(Items.COOKED_BEEF), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:experiment_115"),1));
        DefaultTransform(new ItemStack(Items.COOKED_MUTTON), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:experiment_115"),1));
        DefaultTransform(new ItemStack(Item.getByNameOrId("twilightforest:cooked_meef")), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:experiment_115"),1));
        DefaultTransform(new ItemStack(Item.getByNameOrId("twilightforest:cooked_venison")), CARMINITE, new ItemStack(Item.getByNameOrId("twilightforest:experiment_115"),1));
    }

    @SuppressWarnings("ConstantConditions")
    public static void initTransform(){
        ItemStack TRANSFORM = new ItemStack(Item.getByNameOrId("twilightforest:magic_log_core"),1, 1);

        DefaultTransform(new ItemStack(BlockRegister.STORE),TRANSFORM, new ItemStack(Item.getByNameOrId("twilightforest:charm_of_keeping_2")));
        DefaultTransform(new ItemStack(Item.getByNameOrId("twilightforest:twilight_sapling"), 1, 2),TRANSFORM, new ItemStack(Item.getByNameOrId("twilightforest:twilight_sapling"), 1, 6), 1000);
    }



    public static void DefaultTransform(ItemStack base, ItemStack catalyst, ItemStack result){
        TransmutationsRegister.put(base,new RecipeOutput(result,100),catalyst);
    }

    public static void DefaultTransform(ItemStack base, ItemStack catalyst, ItemStack result, int time){
        TransmutationsRegister.put(base,new RecipeOutput(result,time),catalyst);
    }
}
