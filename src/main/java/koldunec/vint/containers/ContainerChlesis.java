package koldunec.vint.containers;

import koldunec.vint.recipes.AlternativeCarving;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import team.chisel.api.carving.ICarvingRegistry;
import team.chisel.common.inventory.ContainerChisel;
import team.chisel.common.inventory.InventoryChiselSelection;

public class ContainerChlesis extends ContainerChisel {

    private static ICarvingRegistry MyCarving;
    public static void initMyCarving(){
        MyCarving = new AlternativeCarving();
    }

    public ContainerChlesis(InventoryPlayer inventoryplayer, InventoryChiselSelection inv, EnumHand hand) {
        super(inventoryplayer,inv,hand);
    }


    @Override
    public ICarvingRegistry getCarving() {
        return MyCarving;
    }

}
