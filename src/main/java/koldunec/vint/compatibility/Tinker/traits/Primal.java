package koldunec.vint.compatibility.Tinker.traits;


import koldunec.vint.utils.TechHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ToolCore;

public class Primal extends ModifierTrait {

    public Primal() {
        super("primal", TechHelper.getColor(255, 255,0));
        addAspects(ModifierAspect.harvestOnly);
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        if(event.getPos().getY()==-1)
            return;
        boolean isY = TechHelper.isPrime(event.getPos().getY());
        boolean isX = TechHelper.isPrime(event.getPos().getX());
        boolean isZ = TechHelper.isPrime(event.getPos().getZ());
        if(isX&&isY&&isZ)
            event.setNewSpeed(event.getNewSpeed()*10);
        else if(isX||isZ)
            event.setNewSpeed(event.getNewSpeed()*3);
        else if(isY)
            event.setNewSpeed(event.getNewSpeed()*2F);
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        return ((ToolCore) stack.getItem()).hasCategory(Category.HARVEST);
    }
}
