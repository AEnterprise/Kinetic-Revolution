package info.aenterprise.kineticrevolution.items;

import info.aenterprise.kineticrevolution.utils.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class ItemCanister extends ItemBase implements IFluidContainerItem {
	private final int capacity;

	public ItemCanister(int tier, int capacity) {
		super("canisterT" + tier);
		this.capacity = capacity;
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		NBTTagCompound tag = StackUtils.getTagCompound(container);
		if (tag.hasKey("fluid")) {
			return FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluid"));
		}
		return null;
	}

	@Override
	public int getCapacity(ItemStack container) {
		return capacity;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		FluidStack stored = getFluid(container);
		int amount = 0;
		if (stored == null) {
			stored = resource.copy();
			stored.amount = 0;
		}
		if (stored.isFluidEqual(resource)) {
			amount = Math.min(capacity - stored.amount, resource.amount);
		}
		if (doFill) {
			stored.amount += amount;
			saveFluid(stored, container);
		}
		return amount;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		FluidStack stored = getFluid(container);
		if (stored == null) {
			return null;
		}
		FluidStack transfer = stored.copy();
		transfer.amount = Math.min(stored.amount, maxDrain);
		if (doDrain) {
			stored.amount -= transfer.amount;
			saveFluid(stored, container);
		}
		return transfer;
	}

	private void saveFluid(FluidStack fluid, ItemStack container) {
		NBTTagCompound tag = new NBTTagCompound();
		fluid.writeToNBT(tag);
		container.getTagCompound().setTag("fluid", tag);
	}
}
