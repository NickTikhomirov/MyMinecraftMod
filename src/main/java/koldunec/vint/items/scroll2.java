package koldunec.vint.items;

import koldunec.vint.utils.EnumNonLootScrollTypes;
import koldunec.vint.utils.NeighbourChecker;
import koldunec.vint.vint;
import koldunec.vint.items.baseItems.base_item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;


public class scroll2 extends base_item {

    public scroll2(){
        super("scroll2",64);
        this.hasSubtypes=true;
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + EnumNonLootScrollTypes.getByMeta(i).getName();
    }


    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == vint.magicTab){
            for (EnumNonLootScrollTypes type : EnumNonLootScrollTypes.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(     worldIn.isRemote ||
                playerIn==null ||
                !playerIn.isSneaking())
            return EnumActionResult.PASS;
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(stack.getMetadata()==0){
            if(worldIn.provider.getDimension()==0) {
                if (playerIn.posY < 101) {
                    BlockPos bp = playerIn.getPosition();
                    WorldServer w = (WorldServer) worldIn;
                    TemplateManager t = w.getStructureTemplateManager();
                    Template template = t.get(w.getMinecraftServer(), new ResourceLocation(vint.MODID + ":mush2"));
                    BlockPos size = template.getSize();
                    bp = bp.add(-(size.getX() / 2), 140 - bp.getY(), -(size.getZ() / 2));
                    if (NeighbourChecker.SuitableZone(worldIn, bp, size)) {
                        template.addBlocksToWorld(worldIn, bp, new PlacementSettings());
                        playerIn.getHeldItemMainhand().shrink(1);
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        } else {
            if(RerollScroll.player_has_books(playerIn)){
                RerollScroll.player_steal_book(playerIn);
                stack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
        }
        return super.onItemUse(playerIn,worldIn,pos,handIn,facing,hitX,hitY,hitZ);
    }

}
