package koldunec.vint.compatibility.Tinker.materials;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.compatibility.Tinker.ColorConstants;
import slimeknights.tconstruct.library.materials.ArrowShaftMaterialStats;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;

import static koldunec.vint.compatibility.Tinker.TinkerIntegration.ICE_QUEEN;

public class MaterialFrozen extends BaseMaterial {
    public MaterialFrozen() {
        super("frozen", ColorConstants.FROZEN_COLOR);
        setCastable(false);
        setCraftable(true);
    }

    @Override
    public void registerTraits() {
        addTrait(TConstruct.twilit, HEAD);
        addTrait(TConstruct.twilit, BOW);
        addTrait(TConstruct.twilit, SHAFT);
        addTrait(TinkerTraits.coldblooded, HEAD);
        addTrait(TinkerTraits.coldblooded, BOW);
        addTrait(TinkerTraits.freezing, SHAFT);
        addTrait(ICE_QUEEN, BOW);
        addTrait(TinkerTraits.momentum, HEAD);
    }

    @Override
    public boolean allowed() {
        return IntegrationHelper.isLoadedTwilight;
    }

    @Override
    public void addParts() {
        addStat(new HeadMaterialStats(444,9F,2F, 2));
        addStat(new BowMaterialStats(1.8F, 1F, 4));
        addStat(new ArrowShaftMaterialStats(1, 10));
    }
}
