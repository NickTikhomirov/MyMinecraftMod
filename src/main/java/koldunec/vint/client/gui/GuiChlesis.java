package koldunec.vint.client.gui;

import koldunec.vint.tileentities.containers.ContainerChlesis;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumHand;
import team.chisel.client.gui.GuiChisel;
import team.chisel.common.inventory.InventoryChiselSelection;

public class GuiChlesis extends GuiChisel {
    public GuiChlesis(InventoryPlayer iinventory, InventoryChiselSelection menu, EnumHand hand) {
        super(iinventory, menu, hand);
        inventorySlots = new ContainerChlesis(iinventory, menu, hand);
        container = (ContainerChlesis)inventorySlots;
    }
}
