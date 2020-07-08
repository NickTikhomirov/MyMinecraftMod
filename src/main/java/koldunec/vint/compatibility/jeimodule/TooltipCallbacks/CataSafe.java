package koldunec.vint.compatibility.jeimodule.TooltipCallbacks;


import mezz.jei.api.gui.ITooltipCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.List;

public class CataSafe implements ITooltipCallback<ItemStack> {
    private boolean safe;

    public CataSafe(boolean is_safe){safe = is_safe;}

    @Override
    public void onTooltip(int i, boolean b, @Nonnull ItemStack o, @Nonnull List<String> list) {
        if(i!=1)
            return;
        TextFormatting formatting = safe? TextFormatting.GREEN : TextFormatting.RED;
        list.add(formatting + I18n.format("vint.jei.tooltip.catalyst." + (safe ? "safe" : "dang")));
    }
}
