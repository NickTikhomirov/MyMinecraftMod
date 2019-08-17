package koldunec.vint.items.curinggrass;

import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class curingseeds extends ItemSeeds {

    public curingseeds(){
        super(BlockRegister.CURING_CROPS, Blocks.FARMLAND);
        setRegistryName("curingseeds");
        setUnlocalizedName("curingseeds");
        setCreativeTab(vint.magicTab);
    }
}
