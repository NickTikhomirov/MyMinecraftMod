package koldunec.vint.recipes.TwilightTransmutations.RecipeResults;

import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.tileentities.TileTower;
import koldunec.vint.utils.ItemWithMeta;
import koldunec.vint.vint;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.ToolHelper;
import twilightforest.item.TFItems;

import java.util.HashMap;

public class WonderbreakingRecipe implements ITwilightResult {
    public static final HashMap<Item, Integer> VICTIMS = new HashMap<>();

    @Override
    public boolean canProcess(ItemStack base, ItemStack catalyst, TileTower tile) {
        if(!(catalyst.getItem() instanceof ToolCore))
            return false;
        if(ToolHelper.getCurrentDurability(catalyst)==ToolHelper.getMaxDurability(catalyst))
            return false;
        if(!ToolHelper.getTraits(catalyst).contains(TinkerIntegration.WONDERBREAKER))
            return false;
        if(VICTIMS.get(base.getItem())==null)
            return false;
        if(!base.isItemStackDamageable() || base.getItemDamage()==base.getMaxDamage())
            return false;
        return true;
    }

    @Override
    public ItemStack getResultStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        return new ItemStack(ItemRegister.DUST);
    }

    @Override
    public boolean emptyOutputChance(ItemStack base, ItemStack catalyst, TileTower tile) {
        return vint.random.nextInt(10)>0;
    }

    @Override
    public ItemStack getBaseStack(ItemStack base, ItemStack catalyst, TileTower tile) {
        ItemStack copy = base.copy();
        if(base.isItemStackDamageable())
            copy.setItemDamage(copy.getItemDamage()+1);
        else
            copy.shrink(1);
        return copy;
    }

    @Override
    public ItemStack getCatalystAfterTransmutation(ItemStack base, ItemStack catalyst, TileTower tile) {
        if(!(catalyst.getItem() instanceof ToolCore))
            return catalyst;
        ItemStack copy = catalyst.copy();
        ToolHelper.unbreakTool(copy);
        int amount = copy.getItemDamage()-VICTIMS.get(base.getItem());
        if(amount<0) amount = 0;
        copy.setItemDamage(amount);
        return copy;
    }

    @Override
    public int getProcessTime(ItemStack base, ItemStack catalyst, TileTower tile) {
        return 20;
    }



    public static void init(){
        int temp = 20;
        int bow_default = 75;
        fastPut(TFItems.peacock_fan, temp);
        fastPut(TFItems.phantom_helmet, temp);
        fastPut(TFItems.phantom_chestplate, temp);
        fastPut(TFItems.twilight_scepter, temp);
        fastPut(TFItems.zombie_scepter, temp);
        fastPut(TFItems.crumble_horn, temp);
        fastPut(TFItems.ore_magnet, temp);
        fastPut(TFItems.lifedrain_scepter, temp);
        fastPut(TFItems.giant_pickaxe, temp);
        fastPut(TFItems.giant_sword, temp);
        fastPut(TFItems.minotaur_axe_gold, temp);
        fastPut(TFItems.minotaur_axe, 50);
        fastPut(TFItems.ender_bow, bow_default);
        fastPut(TFItems.ice_bow, bow_default);
        fastPut(TFItems.triple_bow, bow_default);
        fastPut(TFItems.seeker_bow, bow_default);
        fastPut(TFItems.shield_scepter, 100);
        fastPut(TFItems.mazebreaker_pickaxe, 500);
        // no moonworm queen because it is a living creature!
    }

    public static void fastPut(Item i, int amount){
        VICTIMS.put(i, amount);
    }
}
