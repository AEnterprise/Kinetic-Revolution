package kineticrevolution.util;

import io.netty.buffer.ByteBuf;
import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import kineticrevolution.networking.ISyncObject;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by AEnterprise
 */
public class MultiBlockData implements ISyncObject {
	private int masterXoffset, masterYoffset, masterZoffset, rotation;
	private MultiBlockPattern pattern;
	private boolean master, valid;

	public MultiBlockData(MultiBlockPattern pattern) {
		this.pattern = pattern;
	}

	public int getMasterXoffset() {
		return masterXoffset;
	}

	public void setMasterXoffset(int masterXoffset) {
		this.masterXoffset = masterXoffset;
	}

	public int getMasterYoffset() {
		return masterYoffset;
	}

	public void setMasterYoffset(int masterYoffset) {
		this.masterYoffset = masterYoffset;
	}

	public int getMasterZoffset() {
		return masterZoffset;
	}

	public void setMasterZoffset(int masterZoffset) {
		this.masterZoffset = masterZoffset;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public MultiBlockPattern getPattern() {
		return pattern;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void saveToNBT(NBTTagCompound tag) {
		NBTTagCompound data = new NBTTagCompound();
		data.setInteger("masterXoffset", masterXoffset);
		data.setInteger("masterYoffset", masterYoffset);
		data.setInteger("masterZoffset", masterZoffset);
		data.setInteger("rotation", rotation);
		data.setBoolean("master", master);
		data.setBoolean("valid", valid);
		tag.setTag("data", data);
	}

	public void loadFromNBT(NBTTagCompound tag) {
		if (!tag.hasKey("data"))
			return;
		NBTTagCompound data = tag.getCompoundTag("data");

		masterXoffset = data.getInteger("masterXoffset");
		masterYoffset = data.getInteger("masterYoffset");
		masterZoffset = data.getInteger("masterZoffset");
		rotation = data.getInteger("rotation");
		valid = data.getBoolean("valid");
		master = data.getBoolean("master");
	}

	@Override
	public void writeToByteBuff(ByteBuf buf) {
		buf.writeBoolean(master);
		buf.writeInt(rotation);
		buf.writeBoolean(valid);
	}

	@Override
	public void readFromByteBuff(ByteBuf buf) {
		master = buf.readBoolean();
		rotation = buf.readInt();
		valid = buf.readBoolean();
	}

	@Override
	public String toString() {
		return String.format("masterXoffset: %s, masterYoffset: %s, masterZoffset: %s, rotation: %s, valid: %s, master: %s", masterXoffset, masterYoffset, masterZoffset, rotation, valid, master);
	}
}
