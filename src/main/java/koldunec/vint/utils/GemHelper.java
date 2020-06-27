package koldunec.vint.utils;

import koldunec.vint.IntegrationHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class GemHelper {
    private static ArrayList<GemInfo> collectContents = new ArrayList<GemInfo>();
    private static TreeMap<Double, ItemStack> selectContents = new TreeMap<>();


    public static void insertGemInfo(GemInfo g){
        if(collectContents==null) return;
        if(g.weight<=0) return;
        collectContents.add(g);
    }

    public static void insertGemInfo(String s, int meta, double w){
        insertGemInfo(new GemInfo(s,meta,w));
    }

    public static void preInit(){
        insertGemInfo(new GemInfo(new ItemStack(Items.EMERALD),3));
        if(IntegrationHelper.isLoadedProjectRed_exploration)
            for(int i=200; i<=202;++i)
                insertGemInfo("projectred-core:resource_item",i,10);
        if(IntegrationHelper.isLoaded("biomesoplenty"))
            for(int i=0; i<8; ++i)
                insertGemInfo("biomesoplenty:gem", i, 30);
    }

    public static void postInit(){
        double sum = 0;
        // we need to change summarised weight =1
        for(GemInfo g: collectContents)
            sum+=g.weight;
        for(GemInfo g: collectContents)
            g.weight/=sum;
        sum = 0;
        // fill sum fields
        for(GemInfo g: collectContents){
            g.sum = sum;
            sum +=g.weight;
        }
        // move to new house
        for(GemInfo g: collectContents)
            selectContents.put(g.sum,g.gem);
        collectContents = null;
    }

    public static boolean isAllowed(){
        return selectContents.size()>3;
    }

    public static ItemStack getOneDrop(Random r){
        return selectContents.lowerEntry(r.nextDouble()).getValue().copy();
    }

    public static ArrayList<ItemStack> getDrops(Random r){
        if(!isAllowed())
            return new ArrayList<>();
        ItemStack mainDrop = getOneDrop(r);
        mainDrop.setCount(1+r.nextInt(4));
        ArrayList<ItemStack> result = new ArrayList<ItemStack>(){{
            add(mainDrop);
        }};
        int count = 2;
        for(int i=0; i<count; ++i){
            ItemStack sideDrop = getOneDrop(r);
            sideDrop.setCount(1+r.nextInt(2));
            result.add(sideDrop);
        }
        return result;
    }

    public static class GemInfo{
        public ItemStack gem;
        public double weight;
        public double sum;
        public GemInfo(ItemStack i, double w){
            gem = i;
            weight = w;
        }
        public GemInfo(String s, int meta, double w){
            this(new ItemStack(Item.getByNameOrId(s),1,meta),w);
        }
    }
}
