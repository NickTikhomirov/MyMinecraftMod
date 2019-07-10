package koldunec.ammpdbm_mod.broomitems;


import koldunec.ammpdbm_mod.broomitems.baseItems.base_fuel;
import koldunec.ammpdbm_mod.init.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class bitcoin extends base_fuel {

    public bitcoin() {
        super("bitcoin",64, (short)800);
    }

}
