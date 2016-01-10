package info.aenterprise.kineticrevolution.items;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class MachineConfigurator extends ItemBase {

	public MachineConfigurator() {
		super("machineConfigurator");
		setMaxStackSize(1);
		setFull3D();
		setHarvestLevel("wrench", 0);
	}

}
