package koldunec.vint.utils;


import koldunec.vint.init.others.LootRegister;
import koldunec.vint.vint;
import net.minecraft.util.ResourceLocation;
import javax.annotation.Nonnull;

public enum EnumTwilightScrollTypes {
    HILL(LootRegister.TWILIGHT_HILL_SCROLL),
    MAZE(new ResourceLocation("twilightforest", "structures/hedge_maze/hedge_maze")),
    ISLE(LootRegister.TWILIGHT_ISLE_SCROLL);

    private ResourceLocation table;

    EnumTwilightScrollTypes(ResourceLocation _table){
        table = _table;
    }

    public String getName(){
        return toString().toLowerCase();
    }

    public ResourceLocation getLootTable(){ return table; }

    @Nonnull
    public static EnumTwilightScrollTypes getByMeta(int meta){
        for (EnumTwilightScrollTypes type : values()){
            if (type.ordinal() == meta)
                return type;
        }
        return HILL;
    }

    public static EnumTwilightScrollTypes getRandomScroll(){
        return values()[vint.random.nextInt(values().length)];
    }
}

