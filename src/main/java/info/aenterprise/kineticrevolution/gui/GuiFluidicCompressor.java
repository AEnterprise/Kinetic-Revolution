package info.aenterprise.kineticrevolution.gui;

import info.aenterprise.kineticrevolution.gui.widgets.WidgetBase;
import info.aenterprise.kineticrevolution.gui.widgets.WidgetTank;
import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class GuiFluidicCompressor extends GuiBase {

	public GuiFluidicCompressor(EntityPlayer player, TileEntityFluidicCompressor tile) {
		super(new ContainerFluidicCompressor(player, tile), true, 175, 68, "guiFluidicCompressor");
		addWidget(new WidgetTank(0, 53, 16, 16, 51, this, tile.getTank()));
		addWidget(new WidgetBase(1, 53, 16, 16, 51, 176,16, new ResourceLocation("kineticrevolution:textures/gui/guiFluidicCompressor.png"), this));
	}
}
