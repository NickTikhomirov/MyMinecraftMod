package koldunec.vint.tileentities;

import net.minecraft.tileentity.TileEntityDispenser;

public class TileEntityFlopper extends TileEntityDispenser {
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "vint.container.flopper";
    }

    public String getGuiID()
    {
        return "minecraft:dispenser";
    }
}