package koldunec.vint.compatibility.traits;


import koldunec.vint.helpers.TechHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;


public class Sixfeets extends AbstractTrait {

    public Sixfeets() {
        super("sixfeets",TextFormatting.YELLOW);
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        EntityLivingBase eb = event.getEntityLiving();
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
}
