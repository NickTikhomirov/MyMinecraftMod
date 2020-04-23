package koldunec.vint.compatibility;

import koldunec.vint.helpers.IntegrationHelper;
import koldunec.vint.vint;
import svenhjol.charm.crafting.feature.Composter;


public class OtherTweaker {
    public static IntegrationHelper integrationHelper = vint.integrationHelper;

    public static void composterInit(){
        if(!integrationHelper.isLoaded("charm"))
            return;

        Composter.inputs.put("minecraft:rotten_flesh", 0.1F);
    }
}
