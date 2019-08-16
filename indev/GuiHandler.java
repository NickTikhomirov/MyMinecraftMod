package koldunec.vint.init;

import koldunec.vint.tileentities.ContainerTower;
import koldunec.vint.tileentities.TileEntityTowerFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_GHASTING_FURNACE = 0;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_GHASTING_FURNACE)
            return new ContainerTower(player.inventory, (TileEntityTowerFurnace)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_GHASTING_FURNACE)
            return new ContainerTower(player.inventory, (TileEntityTowerFurnace)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
