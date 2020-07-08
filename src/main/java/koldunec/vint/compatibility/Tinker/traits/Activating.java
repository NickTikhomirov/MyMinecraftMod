package koldunec.vint.compatibility.Tinker.traits;


import koldunec.vint.utils.TechHelper;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class Activating extends ModifierTrait {

    public Activating() {
        super("activating", TechHelper.getColor(34,117,76));
        addAspects(new ModifierAspect.FreeModifierAspect(-1));
    }
}
