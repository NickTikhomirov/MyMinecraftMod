package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class scroll_of_isle extends base_item {
    public scroll_of_isle(){
        super("scroll_isle",64);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote
                && worldIn.provider.getDimension()==0
                && playerIn!=null
                && playerIn.getHeldItemMainhand().equals(playerIn.getHeldItem(handIn))){
            if(playerIn.posY<101 && playerIn.posY>49){
                BlockPos bp = playerIn.getPosition();
                WorldServer w = (WorldServer) worldIn;
                TemplateManager t = w.getStructureTemplateManager();
                Template template = t.get(w.getMinecraftServer(),new ResourceLocation(ammpdbm_mod.MODID+":mush2"));
                BlockPos size = template.getSize();
                bp = bp.add(-(size.getX()/2),140-bp.getY(),-(size.getZ()/2));
                if(nice_zone(worldIn,bp,size)){
                    template.addBlocksToWorld(worldIn,bp,new PlacementSettings());
                    playerIn.getHeldItemMainhand().shrink(1);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static boolean nice_zone(World w, BlockPos start, BlockPos size){
        int x = start.getX();
        int y = start.getY();
        int z = start.getZ();
        for(int ix=0;ix<size.getX();ix++)
            for(int iz=0;iz<size.getZ();iz++)
                for(int iy=0;iy<size.getY();iy++)
                    if(!w.getBlockState(new BlockPos(x+ix,y+iy,z+iz)).getBlock().equals(Blocks.AIR)) return false;
        return true;
    }
}
