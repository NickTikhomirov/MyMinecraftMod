package koldunec.ammpdbm_mod.broomitems.curinggrass;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.init.BlockRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class curingseeds extends ItemSeeds {

    public curingseeds(){
        super(BlockRegister.CURING_CROPS, Blocks.FARMLAND);
        setRegistryName("curingseeds");
        setUnlocalizedName("curingseeds");
        setCreativeTab(ammpdbm_mod.magicTab);
    }
}
