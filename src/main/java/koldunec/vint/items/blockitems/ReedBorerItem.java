package koldunec.vint.items.blockitems;

import koldunec.vint.init.BlockRegister;
import koldunec.vint.vint;
import net.minecraft.item.ItemBlockSpecial;

public class ReedBorerItem extends ItemBlockSpecial {
    public ReedBorerItem() {
        super(BlockRegister.REED_BORER);
        setUnlocalizedName("reed_borer_item");
        setRegistryName("reed_borer_item");
        this.setCreativeTab(vint.magicTab);
        this.setMaxStackSize(64);
    }
}
