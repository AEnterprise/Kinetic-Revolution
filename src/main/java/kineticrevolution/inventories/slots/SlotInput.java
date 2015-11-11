package kineticrevolution.inventories.slots;

import kineticrevolution.inventories.containers.ContainerCrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class SlotInput extends Slot {
	private ContainerCrafting container;

	public SlotInput(IInventory inventory, int slot, int x, int y, ContainerCrafting container) {
		super(inventory, slot, x, y);
		this.container = container;
	}

	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
		container.onInputChanged();
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return inventory.isItemValidForSlot(slotNumber, stack);
	}
}
