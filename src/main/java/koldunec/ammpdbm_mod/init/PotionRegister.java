package koldunec.ammpdbm_mod.init;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.potions.*;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.init.MobEffects.*;

@Mod.EventBusSubscriber(modid = ammpdbm_mod.MODID)
public class PotionRegister {

    public static final Potion
            MINDPROTECTION = new potionmindprotection(0xFFE4E1);
    public static final Potion
            MAGICPROTECTION = new potionmagicprotection(0xFFFF00);
    //public static final Potion
    //        EFFECT_STORAGE = effectstorage.EFFECT_S;
    public static final Potion
            ENDERPROTECTION = new potionenderprotection(0x00FF60);
    public static final Potion
            HUMANITY = new potionhumanity(0xffc8a8);
    public static final Potion
            WITHERPROTECTION = new potionwitherprotection(0xe34234);
    public static final Potion
            ACID = new potionacid(0x02ab9d);
    public static final Potion
            POISONPROTECTION = new potionpoisonprotection(0xa3481d);
    public static final Potion
            PLAGUE = new potionplague(0x0000ff);


    public static final PotionType
            HASTE_TYPE_STANDART = createPotionType(null,new PotionEffect(HASTE,3600,1)),
            HASTE_TYPE_LONG = createPotionType("long",new PotionEffect(HASTE,9600,1)),

            MINDPROTECTION_TYPE_STANDARD = createPotionType(null, new PotionEffect(MINDPROTECTION, 1200)),
            MINDPROTECTION_TYPE_STRONG = createPotionType("strong", new PotionEffect(MINDPROTECTION, 9600, 1)),

            MAGICPROTECTION_TYPE_STANDARD = createPotionType(null, new PotionEffect(MAGICPROTECTION, 1800)),
            MAGICPROTECTION_TYPE_STRONG = createPotionType("strong", new PotionEffect(MAGICPROTECTION, 6000, 1)),

            MINDDEVOUR_TYPE_STANDARD = createCompositePotionType("minddevourpotion", null,
                    new PotionEffect(BLINDNESS, 4800),
                    new PotionEffect(NAUSEA, 4800)),

            OCEANPOTION_TYPE_STANDARD = createCompositePotionType("oceanpotion", null,
                    new PotionEffect(NIGHT_VISION, 3000),
                    new PotionEffect(WATER_BREATHING, 3000)),
            OCEANPOTION_TYPE_LONG = createCompositePotionType("oceanpotionl", "long",
                    new PotionEffect(NIGHT_VISION, 9000),
                    new PotionEffect(WATER_BREATHING, 9000)),
            OCEANPOTIONCOMBAT_TYPE_LONG = createCompositePotionType("oceanpotioncombat", "long",
                    new PotionEffect(NIGHT_VISION, 8400),
                    new PotionEffect(WATER_BREATHING, 8400),
                    new PotionEffect(MINDPROTECTION, 8400,1)),

            ENDERPROTECTION_TYPE_SHORT = createPotionType("short",new PotionEffect(ENDERPROTECTION,1200,2)),
            ENDERPROTECTION_TYPE_STANDART = createPotionType(null, new PotionEffect(ENDERPROTECTION, 3600)),
            ENDERPROTECTION_TYPE_STRONG = createPotionType("strong", new PotionEffect(ENDERPROTECTION, 3600,1)),
            ENDERPROTECTION_TYPE_LONG = createPotionType("long", new PotionEffect(ENDERPROTECTION, 9600)),

            HUMANITY_TYPE_STANDART = createPotionType(null,new PotionEffect(HUMANITY,3600)),
            HUMANITY_TYPE_STRONG = createPotionType("strong",new PotionEffect(HUMANITY,3600,1)),
            HUMANITY_TYPE_LONG = createPotionType("long",new PotionEffect(HUMANITY,9600)),

            WITHERPROTECTION_TYPE_STANDART = createPotionType(null,new PotionEffect(WITHERPROTECTION,3600)),
            WITHERPROTECTION_TYPE_STRONG = createPotionType("strong",new PotionEffect(WITHERPROTECTION,2400,1)),

            ACID_TYPE_STANDART = createPotionType(null,new PotionEffect(ACID,1)),
            ACID_TYPE_STRONG = createPotionType("strong", new PotionEffect(ACID,1,1)),
            ACID_TYPE_SUPERSTRONG = createPotionType("strongP", new PotionEffect(ACID, 1, 2)),

            POISONPROTECTION_TYPE_STANDART = createPotionType(null, new PotionEffect(POISONPROTECTION,3600,0,false,false)),
            POISONPROTECTION_TYPE_STRONG = createPotionType("strong", new PotionEffect(POISONPROTECTION,3600,1,false,false)),

            PLAGUE_TYPE_STANDART = createPotionType(null, new PotionEffect(PLAGUE,3600)),

            GOLDENAPLLE_TYPE_STANDART = createCompositePotionType("goldenapple_potion",null,
                    new PotionEffect(ABSORPTION,2400),
                    new PotionEffect(REGENERATION,100,1)),
            GOLDENAPLLE_TYPE_STRONG = createCompositePotionType("goldenapple_potion","strong",
                    new PotionEffect(ABSORPTION,2400,3),
                    new PotionEffect(REGENERATION,400,1),
                    new PotionEffect(FIRE_RESISTANCE,6000),
                    new PotionEffect(RESISTANCE,6000));






    private static PotionType createPotionType(String namePrefix, PotionEffect potionEffect) {
        ResourceLocation potionName = potionEffect.getPotion().getRegistryName();
        ResourceLocation potionTypeName ;
        if (namePrefix != null)
            potionTypeName = new ResourceLocation(potionName.getResourceDomain(), namePrefix + "_" + potionName.getResourcePath());
        else
            potionTypeName = potionName;
        if(namePrefix!=null && !namePrefix.equals("strong") && !namePrefix.equals("long"))
            potionName = potionTypeName;
        return new PotionType(potionName.toString(), potionEffect).setRegistryName(potionTypeName);
    }

    /**
     * Возвращает новый экземпляр PotionType с указанным именем. Используется для зелий с несколькими эффектами.
     *
     * @param typeName
     * @param namePrefix
     * @param potionEffects
     *
     * @return PotionType
     */
    private static PotionType createCompositePotionType(String typeName, String namePrefix, PotionEffect... potionEffects) {

        ResourceLocation potionTypeName;

        if (namePrefix != null)
            potionTypeName = new ResourceLocation(ammpdbm_mod.MODID, namePrefix + "_" + typeName);
        else
            potionTypeName = new ResourceLocation(ammpdbm_mod.MODID, typeName);

        return new PotionType(typeName, potionEffects).setRegistryName(potionTypeName);
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {

        event.getRegistry().registerAll(
                MINDPROTECTION,
                MAGICPROTECTION,
                //EFFECT_STORAGE,
                ENDERPROTECTION,
                HUMANITY,
                WITHERPROTECTION,
                ACID,
                POISONPROTECTION,
                PLAGUE
        );
    }

    @SubscribeEvent
    public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {

        event.getRegistry().registerAll(
                HASTE_TYPE_STANDART,
                HASTE_TYPE_LONG,

                MINDPROTECTION_TYPE_STANDARD,
                MINDPROTECTION_TYPE_STRONG,

                MAGICPROTECTION_TYPE_STANDARD,
                MAGICPROTECTION_TYPE_STRONG,

                OCEANPOTION_TYPE_STANDARD,
                OCEANPOTION_TYPE_LONG,
                OCEANPOTIONCOMBAT_TYPE_LONG,

                MINDDEVOUR_TYPE_STANDARD,

                ENDERPROTECTION_TYPE_SHORT,
                ENDERPROTECTION_TYPE_STANDART,
                ENDERPROTECTION_TYPE_STRONG,
                ENDERPROTECTION_TYPE_LONG,

                HUMANITY_TYPE_STANDART,
                HUMANITY_TYPE_STRONG,
                HUMANITY_TYPE_LONG,

                WITHERPROTECTION_TYPE_STANDART,
                WITHERPROTECTION_TYPE_STRONG,

                ACID_TYPE_STANDART,
                ACID_TYPE_STRONG,
                ACID_TYPE_SUPERSTRONG,

                POISONPROTECTION_TYPE_STANDART,
                POISONPROTECTION_TYPE_STRONG,

                PLAGUE_TYPE_STANDART,

                GOLDENAPLLE_TYPE_STANDART,
                GOLDENAPLLE_TYPE_STRONG
        );
    }
}