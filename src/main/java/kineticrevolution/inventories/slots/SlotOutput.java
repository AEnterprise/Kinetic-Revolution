package kineticrevolution.inventories.slots;

import kineticrevolution.inventories.containers.ContainerCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class SlotOutput extends Slot {
	private ContainerCrafting container;

	public SlotOutput(IInventory inventory, int slot, int x, int y, ContainerCrafting container) {
		super(inventory, slot, x, y);
		this.container = container;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		super.onPickupFromSlot(player, stack);
		container.onCrafted();
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}
