package koldunec.vint.recipes.CraftConditions;

import com.google.gson.JsonObject;
import koldunec.vint.IntegrationHelper;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

public class ModLoaded implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String mod = JsonUtils.getString(json,"id");
        return ()-> IntegrationHelper.isLoaded(mod);
    }
}
