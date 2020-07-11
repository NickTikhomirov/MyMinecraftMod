package koldunec.vint.compatibility.Tinker.traits;


import koldunec.vint.compatibility.Tinker.ColorConstants;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class Activating extends ModifierTrait {

    public Activating() {
        super("activating", ColorConstants.ACTIVATING_COLOR);
        for(int i=0; i<aspects.size(); ++i)
            if(aspects.get(i) instanceof ModifierAspect.FreeModifierAspect){
                aspects.set(i, new ModifierAspect.FreeModifierAspect(0));
                break;
            }
    }
}
