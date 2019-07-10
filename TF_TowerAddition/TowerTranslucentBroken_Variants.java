package koldunec.ammpdbm_mod.TF_TowerAddition;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum TowerTranslucentBroken_Variants implements IStringSerializable  {
    REAPPEARINGb_INACTIVE,
    REAPPEARINGb_ACTIVE;


    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
