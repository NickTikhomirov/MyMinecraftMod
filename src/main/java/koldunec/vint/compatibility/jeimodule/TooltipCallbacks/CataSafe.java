package koldunec.vint.compatibility.jeimodule.TooltipCallbacks;


import mezz.jei.api.gui.ITooltipCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.List;

public class CataSafe implements ITooltipCallback<ItemStack> {

    private static final CataSafe cataSafe_true = new CataSafe(true);
    private static final CataSafe cataSafe_false = new CataSafe(false);

    public static CataSafe getInstance(boolean is_safe){
        if(is_safe)
            return cataSafe_true;
        return cataSafe_false;
    }

    private boolean safe;
    private int _32767 = 0;

    public CataSafe(boolean is_safe){safe = is_safe;}

    public CataSafe(boolean is_safe, int _anydurability){safe = is_safe; _32767 = _anydurability;}

    @Override
    public void onTooltip(int i, boolean b, @Nonnull ItemStack o, @Nonnull List<String> list) {
        if(i!=1)
            return;
        TextFormatting formatting = safe? TextFormatting.GREEN : TextFormatting.RED;
        String msg = "vint.jei.tooltip.catalyst.";
        if(!safe && _32767!=0)
            msg+= "dmg" + _32767;
        else if(!safe)
            msg+= "dang";
        else
            msg+= "safe";
        list.add(formatting + I18n.format(msg));
        if(safe && _32767!=0)
            list.add(TextFormatting.GOLD + I18n.format("vint.jei.tooltip.catalyst.anydur"));
    }
}
