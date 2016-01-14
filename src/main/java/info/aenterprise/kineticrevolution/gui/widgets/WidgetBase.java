package info.aenterprise.kineticrevolution.gui.widgets;

import info.aenterprise.kineticrevolution.gui.GuiBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class WidgetBase {
	public final int id, x, y, width, height;
	public int u, v;
	public ResourceLocation texture;
	protected final GuiBase gui;

	public WidgetBase(int id, int x, int y, int width, int height, int u, int v, ResourceLocation texture, GuiBase gui) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.u = u;
		this.v = v;
		this.texture = texture;
		this.gui = gui;
	}

	public WidgetBase(int id, int x, int y, int width, int height, GuiBase gui) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gui = gui;
	}

	public void render(float partialTicks, int mouseX, int mouseY) {
		if (texture == null) return;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		gui.bindTexture(texture);
		gui.drawTexturedModalRect(gui.guiLeft + x, gui.guiTop + y, u, v, width, height);
	}
}
