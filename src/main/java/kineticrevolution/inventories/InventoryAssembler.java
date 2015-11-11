package kineticrevolution.inventories;

import kineticrevolution.loaders.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by AEnterprise
 */
public class InventoryAssembler extends Inventory {

	public InventoryAssembler(int slots, TileEntity tileEntity) {
		super(slots, "inventoryAssembler", tileEntity);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (slot == 0)
			return stack != null && stack.getItem() == ItemLoader.blueprint;
		return true;
	}
}
