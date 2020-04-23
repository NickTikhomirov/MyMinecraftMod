package koldunec.vint.init;

import koldunec.vint.GUIs.guiChlesis;
import koldunec.vint.GUIs.store_gui;
import koldunec.vint.containers.ContainerChlesis;
import koldunec.vint.containers.container_store;
import koldunec.vint.tileentities.EntityStore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import team.chisel.common.inventory.InventoryChiselSelection;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_CHLESIS = -1;
    public static final int GUI_STORE = 0;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_STORE)
            return new container_store(player.inventory, (EntityStore)world.getTileEntity(new BlockPos(x,y,z)),player);
        if(ID==GUI_CHLESIS)
            return new ContainerChlesis(player.inventory, new InventoryChiselSelection(player.getHeldItem(EnumHand.values()[x]), 60), EnumHand.values()[x]);
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_STORE)
            return new store_gui(player.inventory, (EntityStore)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID==GUI_CHLESIS)
            return new guiChlesis(player.inventory, new InventoryChiselSelection(player.getHeldItem(EnumHand.values()[x]), 60), EnumHand.values()[x]);
        return null;
    }
}
