package info.aenterprise.kineticrevolution.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class FluidTank implements IFluidTank, INBTSavable {
	private final int capacity;
	private final TileEntity owner;
	private final String name;
	private FluidStack fluid;

	public FluidTank(TileEntity owner, String name, int capacity) {
		this.owner = owner;
		this.name = name;
		this.capacity = capacity;
	}

	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {
		return fluid.amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	public int getSpace() {
		return fluid != null ? capacity - fluid.amount : capacity;
	}

	public boolean isEmpty() {
		return fluid == null;
	}

	public boolean isFull() {
		return !isEmpty() && fluid.amount == capacity;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null || (fluid != null && !resource.isFluidEqual(fluid)) || isFull())
			return 0;
		if (!doFill)
			return Math.min(getSpace(), resource.amount);

		if (isEmpty()) {
			fluid = new FluidStack(resource, Math.min(capacity, resource.amount));
			return fluid.amount;
		}
		int filled = Math.min(getSpace(), resource.amount);
		fluid.amount += filled;
		owner.markDirty();
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null) return null;
		int drained = Math.min(maxDrain, fluid.amount);

		FluidStack stack = new FluidStack(fluid, drained);
		if (doDrain) {
			fluid.amount -= drained;
			if (fluid.amount <= 0) {
				fluid = null;
			}
			owner.markDirty();
		}
		return stack;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag) {
		if (isEmpty()) {
			tag.setBoolean("Empty", true);
		} else {
			fluid.writeToNBT(tag);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		if (!tag.hasKey("Empty")) {
			fluid = FluidStack.loadFluidStackFromNBT(tag);
		} else {
			fluid = null;
		}
	}

	@Override
	public String tagName() {
		return name;
	}
}
