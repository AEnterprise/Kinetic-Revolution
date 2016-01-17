package info.aenterprise.kineticrevolution.gui;

import info.aenterprise.kineticrevolution.gui.widgets.WidgetBase;
import info.aenterprise.kineticrevolution.gui.widgets.WidgetButton;
import info.aenterprise.kineticrevolution.gui.widgets.WidgetLabel;
import info.aenterprise.kineticrevolution.gui.widgets.WidgetTank;
import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class GuiFluidicCompressor extends GuiBase {
	private TileEntityFluidicCompressor compressor;

	public GuiFluidicCompressor(EntityPlayer player, TileEntityFluidicCompressor tile) {
		super(new ContainerFluidicCompressor(player, tile), true, 175, 68, "guiFluidicCompressor");
		compressor = tile;
		ResourceLocation texture = new ResourceLocation("kineticrevolution:textures/gui/guiFluidicCompressor.png");

		addWidget(new WidgetTank(53, 16, 16, 51, this, tile.getTank()));
		addWidget(new WidgetBase(53, 16, 16, 51, 176, 16, texture, this));
		addWidget(new WidgetButton(20, 20, 20, 16, 175, 83, texture, this, (b) -> compressor.setMode(TileEntityFluidicCompressor.Mode.FILL), () -> compressor.getMode() == TileEntityFluidicCompressor.Mode.EMPTY));
		addWidget(new WidgetButton(20, 36, 20, 16, 175, 99, texture, this, (b) -> compressor.setMode(TileEntityFluidicCompressor.Mode.EMPTY), () -> compressor.getMode() == TileEntityFluidicCompressor.Mode.FILL));
		addWidget(new WidgetLabel(15, 70, this, () -> String.format(StatCollector.translateToLocal("fluidicCompressor.currentMode"), StatCollector.translateToLocal("mode." + compressor.getMode().name()))));
	}
}
