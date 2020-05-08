package koldunec.vint.recipes;

import javafx.util.Pair;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class TwilightTransmutations {
    public static class RecipeInput{
        public Item base;
        public Item catalyst;
        public int basemeta = 0;
        public int catalystmeta = 0;
        public RecipeInput(ItemStack b, ItemStack cat){
            base = b.getItem();
            basemeta = b.getItemDamage();
            catalyst = cat.getItem();
            catalystmeta = cat.getItemDamage();
        }

        @Override
        public int hashCode() {
            if(catalyst==null)
                return base.hashCode()^basemeta;
            else
                return (base.hashCode()^catalyst.hashCode())|((basemeta^catalystmeta)<<2);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj==this)
                return true;
            if(obj.getClass()!=RecipeInput.class)
                return false;
            RecipeInput r = (RecipeInput)obj;
            return
                    base.equals(r.base)
                 && catalyst.equals(r.catalyst)
                 && catalystmeta==r.catalystmeta
                 && basemeta==r.basemeta;
        }
    }

    public static class RecipeOutput{
        public ItemStack result;
        public int consumeTransform = 0;
        public int consumeBorer = 1;
        public RecipeOutput(ItemStack res, int counttrans, int countborer){
            result = res;
            consumeTransform = counttrans;
            consumeBorer = countborer;
        }
        public RecipeOutput(Item i, int meta, int counttrans, int countborer){ this(new ItemStack(i,1,meta),counttrans,countborer); }
        public RecipeOutput(Block i, int meta, int counttrans, int countborer){ this(new ItemStack(i,1,meta),counttrans,countborer); }
        public RecipeOutput(Item i, int counttrans, int countborer){ this(new ItemStack(i,1),counttrans,countborer); }
        public RecipeOutput(Block i, int counttrans, int countborer){ this(new ItemStack(i,1),counttrans,countborer); }
    }

    public static ItemStack CARMINITE;




    public static HashMap<RecipeInput,RecipeOutput> recipes = new HashMap<>();

    public static RecipeOutput getResult(ItemStack base, ItemStack catalyst){
        RecipeInput input = new RecipeInput(base,catalyst);
        return recipes.get(input);
    }

    public static RecipeOutput getResult(RecipeInput input){ return recipes.get(input); }

    public static void put(ItemStack in, ItemStack out, ItemStack cata, int trans, int borer){
        if(in==null || out==null) return;
        if(in.isEmpty() || out.isEmpty()) return;
        RecipeInput input = new RecipeInput(in, cata);
        if(getResult(input)!=null) return;
        recipes.put(new RecipeInput(in,cata),new RecipeOutput(out,trans,borer));
    }


    public static void init(){
        if(!IntegrationHelper.isLoadedTwilight)
            return;

        CARMINITE = new ItemStack(Item.getByNameOrId("twilightforest:carminite"));

    }
}
