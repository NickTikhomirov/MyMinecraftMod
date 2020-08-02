package koldunec.vint.compatibility.Tinker.materials;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.Tinker.ConarmIntegration;
import koldunec.vint.compatibility.Tinker.ConarmProvider;
import slimeknights.tconstruct.library.materials.BowStringMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;

import static koldunec.vint.compatibility.Tinker.TinkerIntegration.BORING;
import static koldunec.vint.compatibility.Tinker.TinkerIntegration.BZZZ;

public class MaterialCarminite extends BaseMaterial {
    public MaterialCarminite(){
        super("carminite",ColorConstants.CARMINITE_COLOR);
        setCastable(false);
        setCraftable(true);
    }


    @Override
    public void registerTraits() {
        addTrait(TConstruct.twilit);
        addTrait(TConstruct.twilit, HEAD);
        addTrait(TConstruct.twilit, HANDLE);
        addTrait(BZZZ);
        addTrait(BZZZ, HEAD);
        addTrait(BORING, HEAD);
        addTrait(TinkerTraits.lightweight, HANDLE);
        addTrait(TinkerTraits.unnatural, HANDLE);
        if(IntegrationHelper.isLoadedConarm){
            ConarmProvider.addArmorTrait(this, ConarmIntegration.EXPERIMENTAL, PLATES);
        }
    }

    @Override
    public boolean allowed() {
        return IntegrationHelper.isLoadedTwilight;
    }

    @Override
    public void addParts() {
        addStat(new HeadMaterialStats(200, 11F, 7.5F, 2));
        addStat(new ExtraMaterialStats(150));
        addStat(new HandleMaterialStats(1.4F,5));
        addStat(new BowStringMaterialStats(1.4F));
        if(IntegrationHelper.isLoadedConarm){
            addStat(ConarmIntegration.getPlates(1.2F, -1, 1));
            addStat(ConarmIntegration.getCore(16, 15));
            addStat(ConarmIntegration.getTrim(20));
        }
    }
}
