package info.aenterprise.kineticrevolution.utils;

import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class RFBattery implements IEnergyStorage, INBTSavable {
	private final int capacity, maxIn, maxOut;
	private int storedEnergy;

	public RFBattery(int capacity, int maxIn, int maxOut) {
		this.capacity = capacity;
		this.maxIn = maxIn;
		this.maxOut = maxOut;
	}

	public int getRemainingStorage() {
		return capacity - storedEnergy;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int received = Math.min(Math.min(maxReceive, getRemainingStorage()), maxIn);
		if (!simulate)
			storedEnergy += received;
		return received;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int extracted = Math.min(Math.min(storedEnergy, maxExtract), maxOut);
		if (!simulate)
			storedEnergy -= extracted;
		return extracted;
	}

	@Override
	public int getEnergyStored() {
		return storedEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag) {
		tag.setInteger("storedEnergy", storedEnergy);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		storedEnergy = tag.getInteger("storedEnergy");
	}

	@Override
	public String tagName() {
		return "RFBattery";
	}
}
