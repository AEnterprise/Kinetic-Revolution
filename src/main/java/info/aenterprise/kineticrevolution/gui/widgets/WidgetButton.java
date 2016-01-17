package info.aenterprise.kineticrevolution.gui.widgets;

import info.aenterprise.kineticrevolution.gui.GuiBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class WidgetButton extends WidgetBase {
	private final Consumer<WidgetButton> handler;
	private BooleanSupplier enabled;

	public WidgetButton(int x, int y, int width, int height, int u, int v, ResourceLocation texture, GuiBase gui, Consumer<WidgetButton> handler, BooleanSupplier enabled) {
		super(x, y, width, height, u, v, texture, gui);
		this.handler = handler;
		this.enabled = enabled;
	}

	@Override
	public void render(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.pushMatrix();
		if (!enabled()) {
			GlStateManager.color(0.5f, 0.5f, 0.5f);
		}
		super.render(partialTicks, mouseX, mouseY);
		GlStateManager.popMatrix();
	}

	@Override
	public void onClick() {
		if (enabled())
			handler.accept(this);
	}

	public boolean enabled() {
		return enabled == null || enabled.getAsBoolean();
	}
}
