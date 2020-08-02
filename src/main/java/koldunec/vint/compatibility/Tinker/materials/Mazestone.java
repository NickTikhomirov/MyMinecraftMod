package koldunec.vint.compatibility.Tinker.materials;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import koldunec.vint.compatibility.Tinker.ConarmIntegration;
import koldunec.vint.compatibility.Tinker.ConarmProvider;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;

import static koldunec.vint.compatibility.Tinker.TinkerIntegration.LEFT_HAND_RULE;
import static koldunec.vint.compatibility.Tinker.TinkerIntegration.MAZEY;

public class Mazestone extends BaseMaterial {
    public Mazestone() {
        super("mazestone", ColorConstants.MAZESTONE_COLOR);
        setCraftable(true);
        setCastable(false);
    }

    @Override
    public void registerTraits() {
        addTrait(LEFT_HAND_RULE, HEAD);
        addTrait(TinkerTraits.duritos, EXTRA);
        addTrait(MAZEY, HEAD);
        addTrait(MAZEY, EXTRA);
        addTrait(TConstruct.twilit, HEAD);
        addTrait(TConstruct.twilit, EXTRA);
        addTrait(TinkerTraits.heavy, EXTRA);
        if(IntegrationHelper.isLoadedConarm){
            ConarmProvider.addArmorTrait(this, ConarmProvider.getSubterranean(), CORE);
            ConarmProvider.addArmorTrait(this, ConarmProvider.getPetravidity(), CORE);
            ConarmProvider.addArmorTrait(this, ConarmProvider.getSubterranean(), PLATES);
            ConarmProvider.addArmorTrait(this, ConarmProvider.getPetravidity(), PLATES);
        }
    }

    @Override
    public boolean allowed() {
        return IntegrationHelper.isLoadedTwilight;
    }

    @Override
    public void addParts() {
        addStat(new HeadMaterialStats(255, 3F, 2F, 4));
        addStat(new ExtraMaterialStats(340));
        if(IntegrationHelper.isLoadedConarm){
            addStat(ConarmIntegration.getCore(25, 17));
            addStat(ConarmIntegration.getPlates(0.5F, 13, 3F));
            addStat(ConarmIntegration.getTrim(0.1F));
        }
    }
}
