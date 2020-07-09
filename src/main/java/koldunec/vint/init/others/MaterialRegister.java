package koldunec.vint.init.others;



import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
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

    public static Material FAKE_IRON = new Material(MapColor.IRON);
}
