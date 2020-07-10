package koldunec.vint.items;

import electroblob.wizardry.constants.Tier;
import electroblob.wizardry.registry.WizardryItems;
import electroblob.wizardry.spell.Spell;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.vint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RerollScroll extends SimpleItems.SimpleItem {
    public RerollScroll() {
        super("scroll_reroll", 64);
    }

    public static boolean player_has_books(EntityPlayer player){
        if(!IntegrationHelper.isLoaded("ebwizardry"))
            return false;
        NonNullList<ItemStack> i = player.inventory.mainInventory;
        if(i.get(0).getItem()!=i.get(8).getItem() || i.get(0).getItem()!= WizardryItems.spell_book) return false;
        return i.get(0).getMetadata()==i.get(8).getMetadata();
    }

    public static void player_steal_book(EntityPlayer player){
        NonNullList<ItemStack> i = player.inventory.mainInventory;
        int meta = i.get(8).getMetadata();
        Tier t = Spell.byMetadata(meta).getTier();
        int ii = meta;
        while(ii==meta) {
            ii = 1+ vint.random.nextInt(Spell.getTotalSpellCount());
            if(!Spell.byMetadata(ii).getTier().equals(t))
                ii=meta;
        }
        player.inventory.mainInventory.set(8,new ItemStack(WizardryItems.spell_book,1,ii));
    }
}
