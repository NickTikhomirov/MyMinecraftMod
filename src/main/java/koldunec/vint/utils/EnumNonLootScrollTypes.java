package koldunec.vint.utils;


public enum EnumNonLootScrollTypes {
    MUSHROOM, REROLL;

    public static EnumNonLootScrollTypes getByMeta(int meta){
        for (EnumNonLootScrollTypes type : values()){
            if (type.ordinal() == meta)
                return type;
        }
        return MUSHROOM;
    }

    public String getName(){
        return toString().toLowerCase();
    }
}
