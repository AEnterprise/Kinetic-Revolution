package info.aenterprise.kineticrevolution.gui.widgets;

import info.aenterprise.kineticrevolution.gui.GuiBase;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class WidgetBase {
	public final int x, y, width, height;
	protected final GuiBase gui;
	public int u, v;
	public ResourceLocation texture;

	public WidgetBase(int x, int y, int width, int height, int u, int v, ResourceLocation texture, GuiBase gui) {
		this(x, y, width, height, gui);
		this.u = u;
		this.v = v;
		this.texture = texture;
	}

	public WidgetBase(int x, int y, int width, int height, GuiBase gui) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gui = gui;
	}

	public void render(float partialTicks, int mouseX, int mouseY) {
		if (texture == null) return;
		gui.bindTexture(texture);
		gui.drawTexturedModalRect(gui.guiLeft + x, gui.guiTop + y, u, v, width, height);
	}

	public void onClick() {

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
