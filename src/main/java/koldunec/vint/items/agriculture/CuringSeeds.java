package koldunec.vint.items.agriculture;

import koldunec.vint.vint;
import koldunec.vint.init.BlockRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class CuringSeeds extends ItemSeeds {

    public CuringSeeds(){
        super(BlockRegister.CURING_CROPS, Blocks.FARMLAND);
        setRegistryName("curingseeds");
        setUnlocalizedName("curingseeds");
        setCreativeTab(vint.magicTab);
    }
}
