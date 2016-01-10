package info.aenterprise.kineticrevolution.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class GuiBase extends GuiContainer {
	public static final ResourceLocation PLAYER_INV_TEXTURE = new ResourceLocation("kineticrevolution:textures/gui/guiPlayerInv.png");
	private final ResourceLocation texture;
	private final boolean drawPlayerInv;
	private final int xSize, ySize;

	public GuiBase(Container inventorySlotsIn, boolean drawPlayerInv, int xSize, int ySize, String texture) {
		super(inventorySlotsIn);
		this.drawPlayerInv = drawPlayerInv;
		this.texture = new ResourceLocation("kineticRevolution:textures/gui/" + texture + ".png");
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (drawPlayerInv) {
			Minecraft.getMinecraft().renderEngine.bindTexture(PLAYER_INV_TEXTURE);
			drawTexturedModalRect(guiLeft, guiTop + ySize, 0, 0, 176, 100);
		}
	}
}
