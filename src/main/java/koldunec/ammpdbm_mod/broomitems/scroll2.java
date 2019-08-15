package koldunec.ammpdbm_mod.broomitems;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_item;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.tileentities.EntityStore;
import koldunec.ammpdbm_mod.utils.Lootgen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;


public class scroll2 extends base_item {
    public enum scroll2Types{
        MUSHROOM, NETHER;

        public static scroll2.scroll2Types getByMeta(int meta){
            for (scroll2.scroll2Types type : values()){
                if (type.ordinal() == meta)
                    return type;
            }
            return null;
        }

        public String getName(){
            return toString().toLowerCase();
        }
    }

    public scroll2(){
        super("scroll2",64);
        this.hasSubtypes=true;
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + scroll2.scroll2Types.getByMeta(i).getName();
    }


    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == ammpdbm_mod.magicTab){
            for (scroll2.scroll2Types type : scroll2.scroll2Types.values()){
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(     worldIn.isRemote ||
                playerIn==null ||
                !playerIn.getHeldItemMainhand().getItem().equals(this) ||
                !playerIn.isSneaking())
            return EnumActionResult.PASS;
        ItemStack s = playerIn.getHeldItemMainhand();
        if(Lootgen.is(s.getMetadata())){
            if(!worldIn.getBlockState(pos).getBlock().equals(BlockRegister.STORE)) return EnumActionResult.PASS;
            EntityStore p1 = (EntityStore) worldIn.getTileEntity(pos);
            if(!s.getItem().equals(this)) return EnumActionResult.FAIL;
            if(p1==null || !p1.isEmpty()) return EnumActionResult.FAIL;
            Lootgen.fill_store(playerIn,p1,worldIn,Lootgen.get2(s.getMetadata()));
            s.shrink(1);
            return EnumActionResult.SUCCESS;
        } else if(worldIn.provider.getDimension()==0){
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
                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return super.onItemUse(playerIn,worldIn,pos,handIn,facing,hitX,hitY,hitZ);
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
