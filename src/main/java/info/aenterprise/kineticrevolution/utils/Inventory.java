package info.aenterprise.kineticrevolution.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class Inventory implements IInventory, INBTSavable {
	private final int size;
	private final ItemStack[] stacks;
	private final int stackLimit;
	private final TileEntity owner;
	private final String name;

	public Inventory(int size, int stackLimit, TileEntity owner, String name) {
		this.size = size;
		stacks = new ItemStack[size];
		this.stackLimit = stackLimit;
		this.owner = owner;
		this.name = name;
	}

	@Override
	public int getSizeInventory() {
		return size;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (stacks[index] != null) {
			if (stacks[index].stackSize > count) {
				markDirty();
				return stacks[index].splitStack(count);
			}
			return removeStackFromSlot(index);
		}
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = stacks[index];
		stacks[index] = null;
		markDirty();
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		stacks[index] = stack;
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return stackLimit;
	}

	@Override
	public void markDirty() {
		owner.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stacks[index] == null || StackUtils.canStacksMerge(stacks[index], stack);
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag) {
		for(int i = 0; i < size; i++) {
			if (stacks[i] != null)
			tag.setTag("stack" + i, stacks[i].writeToNBT(new NBTTagCompound()));
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		for (int i = 0; i < size; i++) {
			if (tag.hasKey("stack" + i)) {
				setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(tag.getCompoundTag("stack" + i)));
			}
		}
	}

	@Override
	public String tagName() {
		return name;
	}
}
