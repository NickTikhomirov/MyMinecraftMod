package koldunec.vint.compatibility.Tinker.traits;

import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.UUID;

import static koldunec.vint.utils.TechHelper.getColor;


// I just want to port this feature from random things to tic
public class Reaching extends AbstractTrait {

    HashMap<EntityEquipmentSlot, AttributeModifier> mods = new HashMap<>();

    public static UUID MOD_UUID = UUID.nameUUIDFromBytes("SpectreTiCRangeModifier".getBytes());
    public Reaching() {
        super("Reaching", getColor(200, 191, 231));
        for(EntityEquipmentSlot slot: EntityEquipmentSlot.values()){
            if(slot.equals(EntityEquipmentSlot.OFFHAND)) continue;
            mods.put(slot,
                    new AttributeModifier(
                            UUID.nameUUIDFromBytes(("SpectreTiCRangeModifier_"+slot.getName()).getBytes()),
                            "Spectral Tinkers Range Modifier",
                            3,
                            0
                    )
            );
        }
    }

    @Override
    public void getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, ItemStack stack, Multimap<String, AttributeModifier> attributeMap) {
        if(!slot.equals(EntityEquipmentSlot.OFFHAND))
            attributeMap.put(EntityPlayer.REACH_DISTANCE.getName(), mods.get(slot));
            //attributeMap.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(MOD_UUID,"Spectral Tinkers Range Modifier",3,0));
    }
}
