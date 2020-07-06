package koldunec.vint.utils.routers;

import koldunec.vint.IntegrationHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Sidemod_Items {
    public static Item Camodye(){
        if(IntegrationHelper.isLoadedPrimitive)
            return net.daveyx0.primitivemobs.core.PrimitiveMobsItems.CAMOUFLAGE_DYE;
        return Items.AIR;
    }

    public static Item TransformPowder(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.transformation_powder;
        return Items.AIR;
    }

    public static Item Borer(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.borer_essence;
        return Items.AIR;
    }

    public static Item Experiment_115(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.experiment_115;
        return Items.AIR;
    }

    public static Item TwilightSapling(){
        if(IntegrationHelper.isLoadedTwilight)
            return Item.getItemFromBlock(twilightforest.block.TFBlocks.twilight_sapling);
        return Items.AIR;
    }

    public static Item VenisonCook(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.cooked_venison;
        return Items.AIR;
    }

    public static Item Fan(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.peacock_fan;
        return Items.AIR;
    }

    public static Item Aurora(boolean not_glass){
        if(IntegrationHelper.isLoadedTwilight)
            if(not_glass)
                return Item.getItemFromBlock(twilightforest.block.TFBlocks.aurora_block);
            else
                return Item.getItemFromBlock(twilightforest.block.TFBlocks.auroralized_glass);
        return Items.AIR;
    }

    public static Item Focus(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.maze_map_focus;
        return Items.AIR;
    }

    public static Item FieryTears(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.fiery_tears;
        return Items.AIR;
    }

    public static Item ConduitHeart(){
        if(IntegrationHelper.isLoaded("conduit"))
            return Item.getByNameOrId("conduit:heart_of_the_sea");
        return Items.AIR;
    }

    public static Item ConduitShell(){
        if(IntegrationHelper.isLoaded("conduit"))
            return Item.getByNameOrId("conduit:nautilus_shell");
        return Items.AIR;
    }
}
