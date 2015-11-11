package kineticrevolution.inventories.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

/**
 * Created by AEnterprise
 */
public abstract class ContainerCrafting extends ContainerBase {


	public ContainerCrafting(InventoryPlayer inventoryPlayer, IInventory inventory) {
		super(inventoryPlayer, inventory);
	}

	public abstract void onInputChanged();

	public abstract void onCrafted();
}
