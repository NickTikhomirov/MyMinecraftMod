package koldunec.vint.recipes;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import team.chisel.api.carving.ICarvingGroup;
import team.chisel.api.carving.ICarvingRegistry;
import team.chisel.api.carving.ICarvingVariation;
import team.chisel.common.carving.GroupList;

import javax.annotation.Nullable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlternativeCarving implements ICarvingRegistry {

    GroupList groups = new GroupList();
    private final Multimap<String, ICarvingGroup> oreLookup = HashMultimap.create();

    public AlternativeCarving(){}

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
    public List<ICarvingVariation> getGroupVariations(IBlockState iBlockState) {
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public String getOreName(IBlockState iBlockState) {
        return null;
    }

    @Override
    public List<ItemStack> getItemsForChiseling(ItemStack itemStack) {
        ArrayList<ItemStack> items = new ArrayList<>();
        for(int i=0; i<15; ++i)
           items.add(new ItemStack(Blocks.WOOL,1,i));
        return items;
        //return Collections.emptyList();
    }

    @Override
    public List<ItemStack> getItemsForChiseling(ICarvingGroup iCarvingGroup) {
        return Collections.emptyList();
    }

    @Override
    public SoundEvent getVariationSound(IBlockState iBlockState) {
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

    @Override
    public List<String> getSortedGroupNames() {
        return null;
    }

    @Override
    public void addVariation(String s, IBlockState iBlockState, int i) {

    }

    @Override
    public void addVariation(String s, ICarvingVariation iCarvingVariation) {

    }

    @Override
    public void addGroup(ICarvingGroup iCarvingGroup) {

    }

    @Nullable
    @Override
    public ICarvingGroup removeGroup(String s) {
        return null;
    }

    @Nullable
    @Override
    public ICarvingVariation removeVariation(IBlockState iBlockState) {
        return null;
    }

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
    public void registerOre(String s, String s1) {

    }

    @Override
    public void setVariationSound(String s, SoundEvent soundEvent) {

    }

    @Override
    public void setOreName(ICarvingGroup iCarvingGroup, String s) {

    }
}
