package koldunec.ammpdbm_mod.broomitems;


import koldunec.ammpdbm_mod.broomitems.baseItems.base_fuel;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
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


public class flint_base extends base_item {

    public flint_base() {
        super("flint_base",64);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        int px = playerIn.getPosition().getX();
        int pz = playerIn.getPosition().getZ();
        int py = playerIn.getPosition().getY();

        int blockcounter=0;
        for(int i=0;i<=py-1;i++){
            BlockPos b = new BlockPos(px,i,pz);
            if(worldIn.getBlockState(b).getBlock().equals(Blocks.BEDROCK)) blockcounter+=5;
            else if(!worldIn.getBlockState(b).getBlock().equals(Blocks.AIR)) blockcounter+=1;
            if(blockcounter>2) break;
        }
        if(blockcounter<3) {
            playerIn.getHeldItem(handIn).shrink(1);

            ItemStack a = new ItemStack(ItemRegister.MAGIC_FLINTS,1,1);
            if(!playerIn.inventory.addItemStackToInventory(a))
                playerIn.dropItem(a,false);
            return ActionResult.newResult(EnumActionResult.PASS,playerIn.getHeldItem(handIn));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    //@Override
    //public int getMaxItemUseDuration(ItemStack stack) {
       //return 20;
    //}


    //@Override
    //public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        //return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    //}
}
