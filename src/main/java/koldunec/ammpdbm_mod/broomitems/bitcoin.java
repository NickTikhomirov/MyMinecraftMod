package koldunec.ammpdbm_mod.broomitems;


import com.ibm.icu.util.GenderInfo;
import koldunec.ammpdbm_mod.ammpdbm_mod;
import koldunec.ammpdbm_mod.broomitems.baseItems.base_fuel;
import koldunec.ammpdbm_mod.init.BlockRegister;
import koldunec.ammpdbm_mod.init.ItemRegister;
import koldunec.ammpdbm_mod.tileentities.EntityStore;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;


public class bitcoin extends base_fuel {

    public bitcoin() {
        super("bitcoin",64, (short)800);
    }


}
