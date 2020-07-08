package koldunec.vint.client.gui;

import koldunec.vint.tileentities.TileTower;
import koldunec.vint.tileentities.containers.ContainerTower;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTower extends GuiContainer {

    private static final ResourceLocation TOWER_GUI_TEXTURES = new ResourceLocation("vint","textures/gui/tower.png");
    private final InventoryPlayer playerInventory;
    private final IInventory tileTower;

    public GuiTower(InventoryPlayer playerInv, IInventory furnaceInv) {
        super(new ContainerTower(playerInv,furnaceInv));
        this.playerInventory = playerInv;
        this.tileTower = furnaceInv;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TOWER_GUI_TEXTURES);
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
        if (TileTower.isBurning(tileTower)) {
            int k = getBurnLeftScaled(13);
            drawTexturedModalRect(i + 150, j + 44 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        int l = getCookProgressScaled(24);
        drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }


    private int getCookProgressScaled(int pixels) {
        int i = tileTower.getField(2);     // cookTime
        int j = tileTower.getField(3);     // totalCookTime
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileTower.getField(1);   // BurnTime   // 0==currentBurnTime
        if (i == 0)
            i = 200;
        return tileTower.getField(0) * pixels / i;
    }


    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = tileTower.getDisplayName().getUnformattedText();
        fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        fontRenderer.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
    }
}
