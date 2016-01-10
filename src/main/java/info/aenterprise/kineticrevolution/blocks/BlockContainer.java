package info.aenterprise.kineticrevolution.blocks;

import net.minecraft.block.ITileEntityProvider;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public abstract class BlockContainer extends BlockBase implements ITileEntityProvider {

	public BlockContainer(String name) {
		super(name);
		this.isBlockContainer = true;
	}
}
