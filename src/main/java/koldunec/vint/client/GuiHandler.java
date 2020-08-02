package koldunec.vint.client;

import koldunec.vint.client.gui.GuiDryer;
import koldunec.vint.client.gui.GuiTower;
import koldunec.vint.client.gui.GuiChlesis;
import koldunec.vint.client.gui.GuiOre;
import koldunec.vint.tileentities.TileEntityDryer;
import koldunec.vint.tileentities.TileTower;
import koldunec.vint.tileentities.containers.ContainerChlesis;
import koldunec.vint.tileentities.containers.ContainerDryer;
import koldunec.vint.tileentities.containers.ContainerTower;
import koldunec.vint.tileentities.containers.ContainerStore;
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
    public static final int GUI_TOWER = 1;
    public static final int GUI_DRYER = 2;
    public static final int GUI_FLOPPER = 3;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_STORE)
            return new ContainerStore(player.inventory, (EntityStore)world.getTileEntity(new BlockPos(x,y,z)),player);
        if(ID==GUI_CHLESIS)
            return new ContainerChlesis(player.inventory, new InventoryChiselSelection(player.getHeldItem(EnumHand.values()[x]), 60), EnumHand.values()[x]);
        if(ID==GUI_TOWER)
            return new ContainerTower(player.inventory,(TileTower)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID==GUI_DRYER)
            return new ContainerDryer(player.inventory, (TileEntityDryer)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==GUI_STORE)
            return new GuiOre(player.inventory, (EntityStore)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID==GUI_CHLESIS)
            return new GuiChlesis(player.inventory, new InventoryChiselSelection(player.getHeldItem(EnumHand.values()[x]), 60), EnumHand.values()[x]);
        if(ID==GUI_TOWER)
            return new GuiTower(player.inventory, (TileTower)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID==GUI_DRYER)
            return new GuiDryer(player.inventory, (TileEntityDryer)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}
