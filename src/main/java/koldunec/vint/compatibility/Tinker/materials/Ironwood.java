package koldunec.vint.compatibility.Tinker.materials;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.Tinker.ConarmIntegration;
import koldunec.vint.compatibility.Tinker.ConarmProvider;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;


public class Ironwood extends BaseMaterial {
    public Ironwood() {
        super("ironwood", ColorConstants.IRONWOOD_COLOR);
        setCraftable(false);
        setCastable(true);
    }

    @Override
    public void registerTraits() {
        addTrait(TConstruct.twilit);
        addTrait(TinkerTraits.ecological);
        addTrait(TinkerTraits.magnetic);
        addTrait(TConstruct.twilit, HEAD);
        addTrait(TinkerTraits.ecological, HEAD);
        addTrait(TinkerTraits.magnetic2, HEAD);
        if(IntegrationHelper.isLoadedConarm){
            ConarmProvider.addArmorTrait(this, ConarmProvider.get(TinkerTraits.ecological));
        }
    }

    @Override
    public boolean allowed() {
        return IntegrationHelper.isLoadedTwilight;
    }

    @Override
    public void addParts() {
        addStat(new HeadMaterialStats(204, 6F, 4F, 2));
        addStat(new ExtraMaterialStats(50));
        addStat(new HandleMaterialStats(1, 60));
        addStat(new BowMaterialStats(0.5F, 1.5F,7));
        if(IntegrationHelper.isLoadedConarm){
            addStat(ConarmIntegration.getCore(12, 15));
            addStat(ConarmIntegration.getPlates(1, 5, 0));
            addStat(ConarmIntegration.getTrim(3.5F));
        }
    }
}
