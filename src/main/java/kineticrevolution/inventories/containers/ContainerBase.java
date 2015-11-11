package kineticrevolution.inventories.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class ContainerBase extends Container {

	protected final InventoryPlayer inventoryPlayer;
	protected final IInventory inventory;
	private boolean canShift = true;

	public ContainerBase(InventoryPlayer inventoryPlayer, IInventory inventory) {
		this.inventoryPlayer = inventoryPlayer;
		this.inventory = inventory;
	}

	private static boolean canStacksMerge(ItemStack stack1, ItemStack stack2) {
		return !(stack1 == null || stack2 == null) && stack1.isItemEqual(stack2) && ItemStack.areItemStackTagsEqual(stack1, stack2);
	}

	public ContainerBase setCanShift(boolean canShift) {
		this.canShift = canShift;
		return this;
	}

	protected void addPlayerInventory(int x, int y) {
		if (inventoryPlayer != null) {
			for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
				for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
					addSlotToContainer(new Slot(inventoryPlayer, 9 + inventoryColumnIndex + inventoryRowIndex * 9, x + inventoryColumnIndex * 18, y + inventoryRowIndex * 18));
			for (int hotBarIndex = 0; hotBarIndex < 9; ++hotBarIndex)
				addSlotToContainer(new Slot(inventoryPlayer, hotBarIndex, 8 + hotBarIndex * 18, y + 58));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		if (inventorySlots.isEmpty())
			return null;
		int numSlots = inventorySlots.size();
		if (slotIndex < 0 || slotIndex >= numSlots)
			return null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);
		ItemStack originalStack = null;
		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			originalStack = stackInSlot.copy();
			if (slotIndex >= numSlots - 9 * 4 && tryShiftItem(stackInSlot, numSlots)) {
				// NO-OP
			} else if (slotIndex >= numSlots - 9 * 4 && slotIndex < numSlots - 9) {
				if (!shiftItemStack(stackInSlot, numSlots - 9, numSlots)) {
					return null;
				}
			} else if (slotIndex >= numSlots - 9 && slotIndex < numSlots) {
				if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots - 9)) {
					return null;
				}
			} else if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots)) {
				return null;
			}
			slot.onSlotChange(stackInSlot, originalStack);
			if (stackInSlot.stackSize <= 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (stackInSlot.stackSize == originalStack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(player, stackInSlot);
		}
		return originalStack;
	}

	private boolean tryShiftItem(ItemStack stackToShift, int numSlots) {
		for (int index = 0; index < numSlots - 9 * 4; index++) {
			Slot slot = (Slot) inventorySlots.get(index);
			if (!slot.isItemValid(stackToShift) && inventory.isItemValidForSlot(index, stackToShift)) {
				continue;
			}
			if (shiftItemStack(stackToShift, index, index + 1)) {
				return true;
			}
		}
		return false;
	}

	private boolean shiftItemStack(ItemStack stackToShift, int start, int end) {
		if (!canShift)
			return false;

		boolean changed = false;
		if (stackToShift.isStackable()) {
			for (int i = start; stackToShift.stackSize > 0 && i < end; i++) {
				Slot slot = (Slot) inventorySlots.get(i);
				ItemStack stackInSlot = slot.getStack();
				if (stackInSlot != null && canStacksMerge(stackInSlot, stackToShift)) {
					int resultingStackSize = stackInSlot.stackSize + stackToShift.stackSize;
					int max = Math.min(stackToShift.getMaxStackSize(), slot.getSlotStackLimit());
					if (resultingStackSize <= max) {
						stackToShift.stackSize = 0;
						stackInSlot.stackSize = resultingStackSize;
						slot.onSlotChanged();
						changed = true;
					} else if (stackInSlot.stackSize < max) {
						stackToShift.stackSize -= max - stackInSlot.stackSize;
						stackInSlot.stackSize = max;
						slot.onSlotChanged();
						changed = true;
					}
				}
			}
		}
		if (stackToShift.stackSize > 0) {
			for (int index = start; stackToShift.stackSize > 0 && index < end; index++) {
				Slot slot = (Slot) inventorySlots.get(index);
				ItemStack stackInSlot = slot.getStack();
				if (stackInSlot == null && inventory.isItemValidForSlot(index, stackToShift)) {
					int max = Math.min(stackToShift.getMaxStackSize(), slot.getSlotStackLimit());
					stackInSlot = stackToShift.copy();
					stackInSlot.stackSize = Math.min(stackToShift.stackSize, max);
					stackToShift.stackSize -= stackInSlot.stackSize;
					slot.putStack(stackInSlot);
					slot.onSlotChanged();
					changed = true;
				}
			}
		}
		return changed;
	}


	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
