package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.compatibility.jeimodule.RecipeLimbo;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutput;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutputConsumeCatalyst;
import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.ThunderRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DefaultConsumer extends Defaulter {

    @Override
    protected RecipeOutput getOutput(Item i, int i_meta) {
        ItemStack result = new ItemStack(i, result_amount, i_meta);
        return isThunder?
                new ThunderRecipe(result, true):
                new RecipeOutputConsumeCatalyst(result, time);
    }

    @Override
    protected void saveJEI(RecipeInput i, RecipeOutput o) {
        new RecipeLimbo.ConsumeRecipe(i, o, isThunder?ThunderRecipe.THUNDER_MESSAGE:"");
        // no need to register, because recipe auto-registers itself in its constructor
    }

    public DefaultConsumer(Item _cata, int _meta){ super(_cata, _meta); }
    public DefaultConsumer(Block _cata, int _meta){ this(Item.getItemFromBlock(_cata), _meta); }
    public DefaultConsumer(Item _cata){ this(_cata, 0); }
    public DefaultConsumer(Block _cata){ this(Item.getItemFromBlock(_cata), 0); }

    public void update(Item i, int _meta){
        catalyst = i;
        meta = _meta;
        time = 100;
        result_amount = 1;
        isThunder = false;
    }

    public void update(Item i){ update(i, 0); }

    public void update(Block b){ update(Item.getItemFromBlock(b), 0);}
}