package koldunec.vint.recipes.TwilightTransmutations;

import koldunec.vint.recipes.TwilightTransmutations.RecipeResults.RecipeOutputConsumeCatalyst;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DefaultConsumer extends Defaulter {

    @Override
    public boolean isSimple() {
        return false;
    }

    public DefaultConsumer(Item _cata, int _meta){ super(_cata, _meta); }
    public DefaultConsumer(Block _cata, int _meta){ this(Item.getItemFromBlock(_cata), _meta); }
    public DefaultConsumer(Item _cata){ this(_cata, 0); }
    public DefaultConsumer(Block _cata){ this(Item.getItemFromBlock(_cata), 0); }

    @Override
    public void register(Item base, int base_meta, Item result, int result_meta){
        TransmutationsRegister.put(
                new RecipeInput(base, base_meta, catalyst, meta),
                new RecipeOutputConsumeCatalyst(new ItemStack(result, 1, result_meta), time));
    }

    public void update(Item i, int _meta){
        catalyst = i;
        meta = _meta;
        time = 100;
        result_amount = 1;
    }

    public void update(Item i){ update(i, 0); }
}