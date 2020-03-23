package koldunec.vint.init;

import koldunec.vint.GUIs.store_gui;
import koldunec.vint.containers.container_store;
import koldunec.vint.tileentities.EntityStore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_STORE = 0;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_STORE)
            return new container_store(player.inventory, (EntityStore)world.getTileEntity(new BlockPos(x,y,z)),player);
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_STORE)
            return new store_gui(player.inventory, (EntityStore)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
