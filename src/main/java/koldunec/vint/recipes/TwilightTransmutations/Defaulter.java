package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.compatibility.jeimodule.RecipeLimbo;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Defaulter {

    // for correct JEI support
    public boolean isSimple(){
        return true;
    }

    public Item catalyst;
    public int meta;
    public int time = 100;
    public int result_amount = 1;

    public Defaulter(Item _cata, int _meta){
       catalyst = _cata;
       meta = _meta;
    }

    public Defaulter(Block _cata, int _meta){ this(Item.getItemFromBlock(_cata), _meta); }

    public Defaulter(Item _cata){ this(_cata, 0); }

    public Defaulter(Block _cata){ this(Item.getItemFromBlock(_cata), 0); }



    /**
     * BLOCK TO BLOCK recipes
     */

    public void register(Block base, int base_meta, Block result, int result_meta){
        register(Item.getItemFromBlock(base), base_meta, Item.getItemFromBlock(result), result_meta);
    }

    public void register(Block base, Block result, int result_meta){
        register(base, 0, result, result_meta);
    }

    public void register(Block base, int base_meta, Block result){
        register(base, base_meta, result, 0);
    }

    public void register(Block base, Block result){
        register(base, 0, result, 0);
    }

    /**
     * ITEM TO ITEM recipes
     */

    public void register(Item base, int base_meta, Item result, int result_meta){
        RecipeInput input = new RecipeInput(base, base_meta, catalyst, meta);
        RecipeOutput output = new RecipeOutput(new ItemStack(result, result_amount, result_meta), time);
        TransmutationsRegister.put(input, output);
        if(isSimple()) {
            new RecipeLimbo.DefaultRecipe(input, output);
            // no need to register, because recipe auto-registers itself in its constructor
        }
    }

    public void register(Item base, Item result, int result_meta){
        register(base, 0, result, result_meta);
    }

    public void register(Item base, int base_meta, Item result){
        register(base, base_meta, result, 0);
    }

    public void register(Item base, Item result){
        register(base, 0, result, 0);
    }

    /**
     * other recipes
     */

    public void register(Block base, int base_meta, Item result, int result_meta){
        register(Item.getItemFromBlock(base), base_meta, result, result_meta);
    }

    public void register(Block base, Item result){
        register(Item.getItemFromBlock(base), 0, result, 0);
    }

    public void register(Block base, Item result, int result_meta){
        register(Item.getItemFromBlock(base), 0, result, result_meta);
    }
}
