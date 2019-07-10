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
            MINDPROTECTION = new potionmindprotection("mindprotection", false, 0xFFE4E1);
    public static final Potion
            MAGICPROTECTION = new potionmagicprotection("magicprotection", false, 0xFFFF00);
    public static final Potion
            EFFECT_STORAGE = effectstorage.EFFECT_S;
    public static final Potion
            ENDERPROTECTION = new potionenderprotection("enderprotection",false, 0x00FF60);


    public static final PotionType
            HASTE_TYPE_STANDART = createPotionType(null,new PotionEffect(HASTE,3600,1)),
            HASTE_TYPE_LONG = createPotionType("long",new PotionEffect(HASTE,9600,1)),

            MINDPROTECTION_TYPE_STANDARD = createPotionType(null, new PotionEffect(MINDPROTECTION, 1200)),
            MINDPROTECTION_TYPE_STRONG = createPotionType("strong", new PotionEffect(MINDPROTECTION, 9600, 1)),

            MAGICPROTECTION_TYPE_STANDARD = createPotionType(null, new PotionEffect(MAGICPROTECTION, 1800)),
            MAGICPROTECTION_TYPE_STRONG = createPotionType("strong", new PotionEffect(MAGICPROTECTION, 6000, 1)),

            MINDDEVOUR_TYPE_STANDARD = createCompositePotionType("minddevourpotion", null, new PotionEffect(BLINDNESS, 4800), new PotionEffect(NAUSEA, 4800)),

            OCEANPOTION_TYPE_STANDARD = createCompositePotionType("oceanpotion", null, new PotionEffect(Potion.getPotionById(13), 3000),new PotionEffect(Potion.getPotionById(16), 3000)),
            OCEANPOTION_TYPE_LONG = createCompositePotionType("oceanpotionl", "long", new PotionEffect(Potion.getPotionById(13), 9000),new PotionEffect(Potion.getPotionById(16), 9000)),
            OCEANPOTIONCOMBAT_TYPE_LONG = createCompositePotionType("oceanpotioncombat", "long", new PotionEffect(Potion.getPotionById(13), 8400),new PotionEffect(Potion.getPotionById(16), 8400),new PotionEffect(MINDPROTECTION, 8400,1)),

            ENDERPROTECTION_TYPE_STANDART = createPotionType(null, new PotionEffect(ENDERPROTECTION, 3600));
            //OCEANPOTIONCOMBAT_TYPE_STANDARD = createCompositePotionType("oceanpotioncombat", null, new PotionEffect(Potion.getPotionById(13), 3000),new PotionEffect(Potion.getPotionById(16), 3000),new PotionEffect(MINDPROTECTION, 3000,1)),
            //MINDPROTECTION_TYPE_LONG = createPotionType("long", new PotionEffect(MINDPROTECTION, 9600)),



    private static PotionType createPotionType(String namePrefix, PotionEffect potionEffect) {
        ResourceLocation potionName = potionEffect.getPotion().getRegistryName();
        ResourceLocation potionTypeName;
        if (namePrefix != null)
            potionTypeName = new ResourceLocation(potionName.getResourceDomain(), namePrefix + "_" + potionName.getResourcePath());
        else
            potionTypeName = potionName;
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
                EFFECT_STORAGE,
                ENDERPROTECTION
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

                ENDERPROTECTION_TYPE_STANDART
        );
    }
}