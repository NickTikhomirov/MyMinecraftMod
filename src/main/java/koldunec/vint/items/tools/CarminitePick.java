package koldunec.vint.items.tools;

import koldunec.vint.IntegrationHelper;
import koldunec.vint.vint;
import koldunec.vint.init.others.MaterialRegister;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class CarminitePick extends ItemPickaxe {
    public CarminitePick() {
        super(MaterialRegister.magicCarminite);
        this.setCreativeTab(vint.magicTab);
        this.setRegistryName("carminite_pick");
        this.setUnlocalizedName("carminite_pick");
        this.setMaxStackSize(1);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(IntegrationHelper.isLoadedTwilight)
            if(state.getBlock().getRegistryName().toString().equals("twilightforest:maze_stone"))
                return 144F;
        return super.getDestroySpeed(stack, state);
    }
}
