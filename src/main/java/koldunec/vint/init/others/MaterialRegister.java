package koldunec.vint.init.others;


import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialRegister {
    public static Item.ToolMaterial magicCarminite =
            EnumHelper.addToolMaterial(
                    "vint:magicCarminite",
                    3,
                    250,
                    6.0F,
                    2.0F,
                    14);
    public static Item.ToolMaterial BAMBOO_MATERIAL = EnumHelper.addToolMaterial("futuristic_bamboo", 0,100,0,0,1);

    public static Item.ToolMaterial magicFlint =
            new EnumHelper().addToolMaterial(
                    "vint:magicFlint",
                    1,
                    140,
                    4.0F,
                    1.0F,
                    7);
    public static Item.ToolMaterial thaumicMix = EnumHelper.addToolMaterial(
            "vint:thaumicMix",
            1,
            511,
            12.0F,
            1.0F,
            22);
    public static Item.ToolMaterial diamondgolden_golden_diamond = EnumHelper.addToolMaterial(
            "vint:dg_gd_mat",
            3,
            1561,
            12.0F,
            3.0F,
            22);
}
