package koldunec.vint.items;

import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.utils.NeighbourChecker;
import koldunec.vint.vint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class MushroomScroll extends SimpleItems.SimpleItem {

    public static ResourceLocation STRUCTURE_LOCATION = new ResourceLocation(vint.MODID + ":mush2");

    public MushroomScroll() {
        super("scroll_shroom", 64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(     worldIn.isRemote ||
                player==null ||
                !player.isSneaking())
            return EnumActionResult.PASS;
        if(worldIn.provider.getDimension()!=0)
            return EnumActionResult.PASS;
        if (player.posY > 100)
            return EnumActionResult.PASS;
        if(!BuildIsleWithOffset((WorldServer) worldIn, player.getPosition()))
            return EnumActionResult.PASS;
        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }

    public static boolean BuildIsleWithOffset(WorldServer w, BlockPos pos){
        TemplateManager manager = w.getStructureTemplateManager();
        Template template = manager.get(w.getMinecraftServer(), STRUCTURE_LOCATION);
        BlockPos size = template.getSize();
        pos = pos.add(-(size.getX() / 2), 140 - pos.getY(), -(size.getZ() / 2));
        if(!NeighbourChecker.SuitableZone(w, pos, size))
            return false;
        template.addBlocksToWorld(w, pos, new PlacementSettings());
        return true;
    }
}
