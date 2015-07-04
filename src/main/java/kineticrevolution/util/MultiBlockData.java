package kineticrevolution.util;

import kineticrevolution.multiblocks.patterns.MultiBlockPattern;

/**
 * Created by AEnterprise
 */
public class MultiBlockData {
	private int masterXoffset, masterYoffset, masterZoffset, rotation;
	private MultiBlockPattern pattern;

	public MultiBlockData(int masterXoffset, int masterYoffset, int masterZoffset, int rotation, MultiBlockPattern pattern) {
		this.masterXoffset = masterXoffset;
		this.masterYoffset = masterYoffset;
		this.masterZoffset = masterZoffset;
		this.rotation = rotation;
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

	public MultiBlockPattern getPattern() {
		return pattern;
	}
}
