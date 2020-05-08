package koldunec.vint.events;

import koldunec.vint.init.IntegrationHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.MaterialEvent;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import twilightforest.compat.TConstruct;

public class TinkerEventHandler {

    @SubscribeEvent
    public static void headStatFix(MaterialEvent.StatRegisterEvent<HeadMaterialStats> e){
        if(!IntegrationHelper.isLoadedTwilight)
            return;
        Material mat = e.material;
        if(mat.equals(TConstruct.nagascale)) {
            e.overrideResult(new HeadMaterialStats(461, 9F, 7.2F, 2));
        }
    }
}
