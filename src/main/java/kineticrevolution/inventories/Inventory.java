package kineticrevolution.inventories;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AEnterprise
 */
public class Inventory implements IInventory {
	private final int slots;
	private final ItemStack[] stacks;
	private final String name;
	private final TileEntity tileEntity;

	public Inventory(int slots, String name, TileEntity tileEntity) {
		this.slots = slots;
		this.name = name;
		stacks = new ItemStack[slots];
		this.tileEntity = tileEntity;
	}

	@Override
	public int getSizeInventory() {
		return slots;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return stacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack original = getStackInSlot(slot);
		if (original == null)
			return null;
		if (amount > original.stackSize)
			amount = original.stackSize;
		ItemStack split = original.copy();
		original.stackSize -= amount;
		split.stackSize = amount;
		if (original.stackSize == 0)
			setInventorySlotContents(slot, null);
		return split;

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		stacks[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		if (tileEntity != null)
			tileEntity.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	public void writeToNBT(NBTTagCompound tag) {
		NBTTagCompound invTag = new NBTTagCompound();
		for (int i = 0; i < slots; i++) {
			if (getStackInSlot(i) != null) {
				invTag.setTag("slot" + i, getStackInSlot(i).writeToNBT(new NBTTagCompound()));
			}
		}
		tag.setTag(getInventoryName(), invTag);
	}

	public void readFromNBT(NBTTagCompound tag) {
		NBTTagCompound invTag = tag.getCompoundTag(getInventoryName());
		for (int i = 0; i < slots; i++) {
			if (invTag.hasKey("slot" + i))
				setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(invTag.getCompoundTag("slot" + i)));
		}
	}

	@SuppressWarnings("unchecked")
	public List<ItemStack> getContent() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.addAll(Arrays.asList(stacks));
		return list;
	}
}
