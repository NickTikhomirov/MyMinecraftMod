package koldunec.vint.compatibility;

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

    public static Item Focus(){
        if(IntegrationHelper.isLoadedTwilight)
            return twilightforest.item.TFItems.maze_map_focus;
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
