package koldunec.vint.events;

import koldunec.vint.vint;
import koldunec.vint.items.another_dye_please_dont_blame_me;
import koldunec.vint.items.gunpowder_reed.block_gunreed;
import koldunec.vint.items.scroll2;
import koldunec.vint.init.ItemRegister;
import koldunec.vint.recipes.dragonBreathFix;
import koldunec.vint.recipes.paint_operator;
import koldunec.vint.recipes.tipped_crafting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class TechEvents {

    @SubscribeEvent
    public static void ModelsFor(ModelRegistryEvent event){
        final Item Dye = ItemRegister.ANOTHER_DYE;
        for (another_dye_please_dont_blame_me.dyeTypes type : another_dye_please_dont_blame_me.dyeTypes.values()){
            ModelLoader.setCustomModelResourceLocation(Dye, type.ordinal(), new ModelResourceLocation(Dye.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        }
        final Item Scroll = ItemRegister.SCROLL;
        for(koldunec.vint.items.Scroll.scrollTypes type : koldunec.vint.items.Scroll.scrollTypes.values()){
            ModelLoader.setCustomModelResourceLocation(Scroll,type.ordinal(), new ModelResourceLocation(Scroll.getRegistryName() + "_" + type.toString().toLowerCase(),"inventory"));
        }
        final Item Reed = ItemRegister.POWDER_REED;
        for(block_gunreed.reedTypes type: block_gunreed.reedTypes.values()){
            ModelLoader.setCustomModelResourceLocation(Reed,type.ordinal(),new ModelResourceLocation(Reed.getRegistryName()+"_"+type.toString().toLowerCase(),"inventory"));
        }
        final Item Scroll2 = ItemRegister.SCROLL_TRIVIA;
        for(scroll2.scroll2Types type : scroll2.scroll2Types.values()){
            ModelLoader.setCustomModelResourceLocation(Scroll2,type.ordinal(), new ModelResourceLocation(Scroll2.getRegistryName() + "_" + type.toString().toLowerCase(),"inventory"));
        }
    }

    @SubscribeEvent
    public static void registerCraft(RegistryEvent.Register<IRecipe> event){
        HashMap<String, IRecipe> thingsToRegister = new HashMap<>();

        thingsToRegister.put("supercuregrass", new tipped_crafting());
        thingsToRegister.put("tippedarrow", new dragonBreathFix());
        thingsToRegister.put("paint_operator", new paint_operator());

        for(String name: thingsToRegister.keySet()){
            IRecipe recipe = thingsToRegister.get(name);
            recipe.setRegistryName(new ResourceLocation(vint.MODID + ":" + name));
            event.getRegistry().register(recipe);
        }
    }



    @SubscribeEvent
    public static void fuel(FurnaceFuelBurnTimeEvent e) {
        if (e.getItemStack().getItem() == Items.EGG)
            e.setBurnTime(1);
    }
}
