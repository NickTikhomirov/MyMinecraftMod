package koldunec.vint.utils;


import koldunec.vint.vint;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nonnull;

public enum EnumScrollTypes {
    DESERT(LootTableList.CHESTS_DESERT_PYRAMID),
    MINE(LootTableList.CHESTS_ABANDONED_MINESHAFT),
    DUNGEON(LootTableList.CHESTS_SIMPLE_DUNGEON),
    JUNGLE(LootTableList.CHESTS_JUNGLE_TEMPLE),
    NETHER(LootTableList.CHESTS_NETHER_BRIDGE);

    private ResourceLocation table;

    EnumScrollTypes( ResourceLocation _table){
        table = _table;
    }

    public String getName(){
        return toString().toLowerCase();
    }

    public ResourceLocation getLootTable(){ return table; }

    @Nonnull
    public static EnumScrollTypes getByMeta(int meta){
        for (EnumScrollTypes type : values()){
            if (type.ordinal() == meta)
                return type;
        }
        return DESERT;
    }

    public static EnumScrollTypes getRandomScroll(){
        return values()[vint.random.nextInt(values().length)];
    }
}

