package info.aenterprise.kineticrevolution.utils;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public interface INBTSavable {
	void saveToNBT(NBTTagCompound tag);

	void readFromNBT(NBTTagCompound tag);

	String tagName();
}
