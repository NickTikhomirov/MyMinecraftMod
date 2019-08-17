package koldunec.vint.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import koldunec.vint.vint;
import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.init.ItemRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;



@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class saviour extends base_item implements IBauble{
    public saviour(){
        super("saviour",1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    public static boolean isEquiped(EntityPlayer e){
        if(e.getEntityWorld().isRemote) return false;
        if(e.getEntityWorld().getGameRules().getBoolean("keepInventory"))
            return false;
        if (vint.isLoadedBaubles)
            if (BaublesApi.isBaubleEquipped(e, ItemRegister.SAVIOUR) != -1) return true;
        return false;
    }

    public static void savePoorThing(EntityPlayer player){
        if(!vint.isLoadedBaubles) return;
        InventoryBasic endChest = player.getInventoryEnderChest();
        IBaublesItemHandler r = BaublesApi.getBaublesHandler(player);
        //armor
        for(ItemStack a: player.getArmorInventoryList()){
            ItemStack left = endChest.addItem(a.copy());
            a.setCount(left.getCount());
        }
        //hotbar
        for(int i=0;i<9;i++){
            ItemStack a = player.inventory.mainInventory.get(i);
            ItemStack left = endChest.addItem(a.copy());
            //inv.setInventorySlotContents(i,left);
            a.setCount(left.getCount());
        }
        //baubles
        for(int i=0; i<7;i++){
            ItemStack a = r.getStackInSlot(i);
            ItemStack left = endChest.addItem(a.copy());
            a.setCount(left.getCount());
        }
        //leftHand
        ItemStack a = player.inventory.offHandInventory.get(0);
        ItemStack left = endChest.addItem(a.copy());
        player.inventory.offHandInventory.set(0,left);
        a.setCount(left.getCount());
    }

    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(player.getEntityWorld().getGameRules().getBoolean("keepInventory")){
            if(!worldIn.isRemote){
                InventoryEnderChest a = player.getInventoryEnderChest();
                if(a!=null){
                    player.displayGUIChest(a);
                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
