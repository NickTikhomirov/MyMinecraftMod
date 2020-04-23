package koldunec.vint.GUIs;

import koldunec.vint.containers.ContainerChlesis;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumHand;
import team.chisel.client.gui.GuiChisel;
import team.chisel.common.inventory.InventoryChiselSelection;

public class guiChlesis extends GuiChisel {
    public guiChlesis(InventoryPlayer iinventory, InventoryChiselSelection menu, EnumHand hand) {
        super(iinventory, menu, hand);
        inventorySlots = new ContainerChlesis(iinventory, menu, hand);
        container = (ContainerChlesis)inventorySlots;
    }
}
