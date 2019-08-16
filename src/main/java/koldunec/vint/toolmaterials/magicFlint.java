package koldunec.vint.toolmaterials;


import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class magicFlint {
    public static Item.ToolMaterial magicFlint =
            new EnumHelper().addToolMaterial(
                    "vint:magicFlint",
                    1,
                    140,
                    4.0F,
                    1.0F,
                    22);
}
