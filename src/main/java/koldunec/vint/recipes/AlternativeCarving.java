package koldunec.vint.recipes;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import team.chisel.api.carving.ICarvingGroup;
import team.chisel.api.carving.ICarvingRegistry;
import team.chisel.api.carving.ICarvingVariation;

import javax.annotation.Nullable;
import java.util.*;


public class AlternativeCarving implements ICarvingRegistry {

    static public HashMap<Item, HashMap<Integer, String>> groups = new HashMap<>();

    public static void add(ItemStack stack, String s){
        HashMap<Integer,String> temp = groups.get(stack.getItem());
        if(temp!=null) {
            if(temp.get(stack.getMetadata())!=null)
                return;
            temp.put(stack.getMetadata(),s);
            return;
        }
        temp = new HashMap<>();
        temp.put(stack.getMetadata(),s);
        groups.put(stack.getItem(), temp);
    }

    public AlternativeCarving(){}


    // Getters

    @Nullable
    @Override
    public ICarvingGroup getGroup(IBlockState iBlockState) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingGroup getGroup(ItemStack itemStack) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingGroup getGroup(String s) {
        return null;
    }

    @Override
    public List<ICarvingVariation> getGroupVariations(IBlockState iBlockState) {
        return Collections.emptyList();
    }

    @Override
    public List<ItemStack> getItemsForChiseling(ItemStack itemStack) {
        HashMap<Integer,String> group1 = groups.get(itemStack.getItem());
        if(group1==null)
            return Collections.emptyList();
        String group2 = group1.get(itemStack.getMetadata());
        if(group2==null)
            return Collections.emptyList();
        ArrayList<ItemStack> result = new ArrayList<>();
        for(Map.Entry<Item, HashMap<Integer, String>> triair: groups.entrySet()){
            for(Map.Entry<Integer,String> pair: triair.getValue().entrySet()){
                if(pair.getValue().equals(group2))
                    result.add(new ItemStack(triair.getKey(),1,pair.getKey()));
            }
        }
        return result;
    }

    @Override
    public List<ItemStack> getItemsForChiseling(ICarvingGroup iCarvingGroup) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getSortedGroupNames() {
        return null;
    }


    // Adders

    @Override
    public void addVariation(String s, IBlockState iBlockState, int i) {    }

    @Override
    public void addVariation(String s, ICarvingVariation iCarvingVariation) {    }

    @Override
    public void addGroup(ICarvingGroup iCarvingGroup) {    }




    /*
        UNUSED FOR NOW
    */

    @Nullable
    @Override
    public ICarvingVariation getVariation(IBlockState iBlockState) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingVariation getVariation(ItemStack itemStack) {
        return null;
    }

    @Override
    public SoundEvent getVariationSound(IBlockState iBlockState) { return null; }

    @Nullable
    @Override
    public String getOreName(IBlockState iBlockState) {
        return null;
    }

    @Override
    public SoundEvent getVariationSound(ItemStack itemStack) {
        return null;
    }

    @Override
    public SoundEvent getVariationSound(@Nullable ICarvingVariation iCarvingVariation) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingGroup removeGroup(String s) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingVariation removeVariation(IBlockState iBlockState) { return null; }

    @Nullable
    @Override
    public ICarvingVariation removeVariation(IBlockState iBlockState, String s) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingVariation removeVariation(ItemStack itemStack) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingVariation removeVariation(ItemStack itemStack, String s) {
        return null;
    }

    @Override
    public void registerOre(String s, String s1) {    }

    @Override
    public void setVariationSound(String s, SoundEvent soundEvent) {    }

    @Override
    public void setOreName(ICarvingGroup iCarvingGroup, String s) {    }
}
