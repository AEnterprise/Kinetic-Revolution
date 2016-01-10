package info.aenterprise.kineticrevolution.gui;

import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class GuiFluidicCompressor extends GuiBase {

	public GuiFluidicCompressor(EntityPlayer player, TileEntityFluidicCompressor tile) {
		super(new ContainerFluidicCompressor(player, tile), true, 175, 68, "guiFluidicCompressor");
	}
}
