package koldunec.vint.compatibility.Tinker;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.harvest.TinkerHarvestTools;
import slimeknights.tconstruct.tools.melee.item.FryPan;
import slimeknights.tconstruct.tools.modifiers.ModReinforced;

import java.util.List;

public class ToolRebuilder {
    public enum TYPE{
        PICKAXE,
        AXE,
        SHOVEL;

        String low_name;
        TYPE(){ low_name = this.toString().toLowerCase(); }


        public ToolCore builder(boolean complex){
            if(this.equals(PICKAXE))
                return complex? TinkerHarvestTools.hammer : TinkerHarvestTools.pickaxe;
            if(this.equals(SHOVEL))
                return complex? TinkerHarvestTools.excavator : TinkerHarvestTools.shovel;
            if(this.equals(AXE))
                return complex? TinkerHarvestTools.lumberAxe : TinkerHarvestTools.hatchet;
            return new FryPan();
        }

        public static TYPE getByState(IBlockState state){
            Block b = state.getBlock();
            for(TYPE t: values())
                if(t.low_name.equals(b.getHarvestTool(state)) || t.builder(false).isEffective(state))
                    return t;
            return null;
        }
    }

    public static ItemStack rebuildSimple(ItemStack base, TYPE t){
        if(!(base.getItem() instanceof ToolCore))
            return base;
        List<Material> materials = TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(base).copy());
        ItemStack result = t.builder(false).buildItem(materials);
        NBTTagCompound tag = TagUtil.getTagSafe(base);
        try{ToolBuilder.rebuildTool(tag, (ToolCore) result.getItem());}
        catch(Exception e){return base;}
        result.setTagCompound(tag);
        return silentDamage(result, ToolHelper.getMaxDurability(base) - ToolHelper.getCurrentDurability(base));
    }

    public static ItemStack rebuildComplex(ItemStack base, TYPE t){
        if(!(base.getItem() instanceof ToolCore))
            return base;
        List<Material> materials = TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(base).copy());
        ItemStack result = t.builder(true).buildItem(materials);
        NBTTagCompound tag = TagUtil.getTagSafe(base);
        try{ToolBuilder.rebuildTool(tag, (ToolCore) result.getItem());}
        catch(Exception e){return base;}
        result.setTagCompound(tag);
        return silentDamage(result, ToolHelper.getMaxDurability(base) - ToolHelper.getCurrentDurability(base));
    }

    public static ItemStack silentDamage(ItemStack i, int damage){
        if(damage == 0 || ToolHelper.isBroken(i))
            return i;
        int actualAmount = damage;
        if(actualAmount > 0 && TagUtil.getTagSafe(i).getBoolean(ModReinforced.TAG_UNBREAKABLE))
            actualAmount = 0;
        actualAmount = Math.min(actualAmount, ToolHelper.getCurrentDurability(i));
        i.setItemDamage(i.getItemDamage() + actualAmount);
        if(ToolHelper.getCurrentDurability(i) == 0)
            ToolHelper.breakTool(i, null);
        return i;
    }
}
