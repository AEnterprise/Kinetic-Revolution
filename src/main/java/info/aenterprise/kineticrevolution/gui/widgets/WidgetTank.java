package info.aenterprise.kineticrevolution.gui.widgets;

import info.aenterprise.kineticrevolution.gui.GuiBase;
import info.aenterprise.kineticrevolution.utils.FluidTank;
import info.aenterprise.kineticrevolution.utils.FluidUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class WidgetTank extends WidgetBase {
	private static final ResourceLocation MC_BLOCK_SHEET = TextureMap.locationBlocksTexture;
	private final FluidTank tank;

	public WidgetTank(int x, int y, int width, int height, GuiBase gui, FluidTank tank) {
		super(x, y, width, height, gui);
		this.tank = tank;
	}

	@Override
	public void render(float partialTicks, int mouseX, int mouseY) {
		if (!tank.isEmpty())
			drawFluid(tank.getFluid(), (int) ((tank.getFluidAmount() / (float) tank.getCapacity()) * height), x, y, width, height);
	}

	public void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height) {
		if (fluid == null || fluid.getFluid() == null) {
			return;
		}
		TextureAtlasSprite texture = FluidUtils.getFluidTexture(fluid.getFluid(), FluidUtils.FluidType.STILL);
		gui.bindTexture(MC_BLOCK_SHEET);
		int color = fluid.getFluid().getColor(fluid);
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GlStateManager.color(red, green, blue, 1.0F);
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int fullLvl = (height - level) / 16;
		for (int i = 0; i < fullX; i++) {
			for (int j = 0; j < fullY; j++) {
				if (j >= fullLvl) {
					drawCutIcon(texture, x + i * 16, y + j * 16, 16, 16);
				}
			}
		}
		for (int i = 0; i < fullX; i++) {
			drawCutIcon(texture, x + i * 16, y + fullY * 16, 16, lastY);
		}
		for (int i = 0; i < fullY; i++) {
			if (i >= fullLvl) {
				drawCutIcon(texture, x + fullX * 16, y + i * 16, lastX, 16);
			}
		}
		drawCutIcon(texture, x + fullX * 16, y + fullY * 16, lastX, lastY);
	}

	public void drawCutIcon(TextureAtlasSprite texture, int x, int y, int width, int height) {
		gui.drawTexturedModalRect(gui.guiLeft + x, gui.guiTop + y, texture, width, height);
	}
}
