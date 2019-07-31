package koldunec.ammpdbm_mod.events;

import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.another_dye_please_dont_blame_me;
import koldunec.ammpdbm_mod.broomitems.flints;
import koldunec.ammpdbm_mod.broomitems.scroll;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.recipes.dragonBreathFix;
import koldunec.ammpdbm_mod.recipes.paint_operator;
import koldunec.ammpdbm_mod.recipes.tipped_crafting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class TechEvents {

    @SubscribeEvent
    public static void ModelsFor(ModelRegistryEvent event){
        final Item Dye = ItemRegister.ANOTHER_DYE;
        for (another_dye_please_dont_blame_me.dyeTypes type : another_dye_please_dont_blame_me.dyeTypes.values()){
            ModelLoader.setCustomModelResourceLocation(Dye, type.ordinal(), new ModelResourceLocation(Dye.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        }
        final Item flint = ItemRegister.MAGIC_FLINTS;
        for(flints.flintTypes type : flints.flintTypes.values()){
            ModelLoader.setCustomModelResourceLocation(flint, type.ordinal(), new ModelResourceLocation(flint.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        }
        final Item Scroll = ItemRegister.SCROLL;
        for(scroll.scrollTypes type : scroll.scrollTypes.values()){
            ModelLoader.setCustomModelResourceLocation(Scroll,type.ordinal(), new ModelResourceLocation(Scroll.getRegistryName() + "_" + type.toString().toLowerCase(),"inventory"));
        }
    }

    @SubscribeEvent
    public static void registerSupercuringCraft(RegistryEvent.Register<IRecipe> event){
        tipped_crafting a = new tipped_crafting();
        a.setRegistryName(ammpdbm_mod.MODID,"supercuregrass");
        event.getRegistry().register(a);

        dragonBreathFix b = new dragonBreathFix();
        b.setRegistryName(ammpdbm_mod.MODID,"tippedarrow");
        event.getRegistry().register(b);

        paint_operator c = new paint_operator();
        c.setRegistryName(ammpdbm_mod.MODID,"paint_operator");
        event.getRegistry().register(c);
    }

    @SubscribeEvent
    public static void fuel(FurnaceFuelBurnTimeEvent e) {
        if (e.getItemStack().getItem() == Item.getItemFromBlock(BlockRegister.BLOCK_CHARCOAL))
            e.setBurnTime(2000);
        if (e.getItemStack().getItem() == Items.EGG)
            e.setBurnTime(1);
    }
}
