package kineticrevolution.client.gui;

import kineticrevolution.client.gui.base.GuiBase;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by AEnterprise
 */
public class GuiAssembler extends GuiBase {
	private static final ResourceLocation TEXTURE = new ResourceLocation("kineticrevolution", "textures/gui/assembler.png");

	public GuiAssembler(Container container) {
		super(container);
		setTitleYOffset(5);
	}

	@Override
	public ResourceLocation texture() {
		return TEXTURE;
	}

	@Override
	public int getXSize() {
		return 175;
	}

	@Override
	public int getYSize() {
		return 165;
	}

	@Override
	public String getInventoryName() {
		return "assembler";
	}

	@Override
	public void initialize() {

	}
}
