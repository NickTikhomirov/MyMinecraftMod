package koldunec.vint.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import koldunec.vint.init.IntegrationHelper;
import koldunec.vint.vint;
import koldunec.vint.items.baseItems.base_item;
import koldunec.vint.init.ItemRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

/*
  Синий:
    Страж (2-3)
  Зелёный:
    Изумрудная руда (2)
  Красный:
    Редстоуновая руда (5%)(1)
  Чёрный:
    Скелет-иссушитель (20%)(1-3)
    Угольная руда (1%)(1)
  Белый:
    Кварцевая руда (10%)(1)
    Алмазная руда (1)

  Несколько:
    Гаст:
      Б (50%)(1-2)
      К (20%)(2-3)
      Ч (20%)(1-2)
    Древний страж:
      Б (10-11)
      С (10-11)
    Чешуйница:
      Любой, но один (100%)

  Все сразу:
    Радужная руда (по 1 штуке)
    Любой босс (по 16-20)
 */



@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class  xyAmulet extends base_item implements IBauble{
    public xyAmulet(){
        super("xy_amulet",1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.CHARM;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    public static boolean isEquiped(EntityPlayer e){
        if(IntegrationHelper.isLoadedProjectX && IntegrationHelper.isLoadedSulfurTorches) {
            if (e.getHeldItemOffhand().getItem().equals(ItemRegister.xyAMULET)) return true;
            if (IntegrationHelper.isLoadedBaubles)
                if (BaublesApi.isBaubleEquipped(e, ItemRegister.xyAMULET) != -1) return true;
        }
        return false;
    }

    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }
}
