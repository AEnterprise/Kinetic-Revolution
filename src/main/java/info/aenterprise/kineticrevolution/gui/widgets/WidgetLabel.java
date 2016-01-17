package info.aenterprise.kineticrevolution.gui.widgets;

import info.aenterprise.kineticrevolution.gui.GuiBase;

import java.util.function.Supplier;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class WidgetLabel extends WidgetBase {
	private final Supplier<String> supplier;

	public WidgetLabel(int x, int y, GuiBase gui, Supplier<String> supplier) {
		super(x, y, 0, 0, gui);
		this.supplier = supplier;
	}

	@Override
	public void render(float partialTicks, int mouseX, int mouseY) {
		gui.getFontRenderer().drawString(supplier.get(), x + gui.guiLeft, y + gui.guiTop, 0x000000);
	}
}
