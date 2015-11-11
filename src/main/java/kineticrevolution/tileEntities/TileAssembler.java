package kineticrevolution.tileEntities;

import kineticrevolution.inventories.IInventoryTE;
import kineticrevolution.inventories.Inventory;
import kineticrevolution.inventories.InventoryAssembler;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by AEnterprise
 */
public class TileAssembler extends TileSyncBase implements IInventoryTE {
	private Inventory inventory = new InventoryAssembler(11, this);

	@Override
	public void writeToCustomNBT(NBTTagCompound tag) {
		inventory.writeToNBT(tag);
	}

	@Override
	public void readFromCustomNBT(NBTTagCompound tag) {
		inventory.readFromNBT(tag);
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}
}
