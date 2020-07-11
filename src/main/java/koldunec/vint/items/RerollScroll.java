package koldunec.vint.items;

import electroblob.wizardry.constants.Tier;
import electroblob.wizardry.registry.WizardryItems;
import electroblob.wizardry.spell.Spell;
import koldunec.vint.IntegrationHelper;
import koldunec.vint.objectbuilders.SimpleItems;
import koldunec.vint.vint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RerollScroll extends SimpleItems.SimpleItem {
    public RerollScroll() {
        super("scroll_reroll", 64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(     worldIn.isRemote ||
                player==null ||
                !player.isSneaking())
            return EnumActionResult.PASS;
        if(player_has_books(player)){
            player_steal_book(player);
            player.getHeldItem(hand).shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    private static boolean player_has_books(EntityPlayer player){
        if(!IntegrationHelper.isLoaded("ebwizardry"))
            return false;
        NonNullList<ItemStack> i = player.inventory.mainInventory;
        if(i.get(0).getItem()!=i.get(8).getItem() || i.get(0).getItem()!= WizardryItems.spell_book) return false;
        return i.get(0).getMetadata()==i.get(8).getMetadata();
    }

    private static void player_steal_book(EntityPlayer player){
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
