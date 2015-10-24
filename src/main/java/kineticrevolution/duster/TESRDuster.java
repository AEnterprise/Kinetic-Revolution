package kineticrevolution.duster;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import kineticrevolution.lib.Reference;

/**
 * Created by AEnterprise
 */
public class TESRDuster extends TileEntitySpecialRenderer {

	private final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "non-existant texture");

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float fl) {
		if (!(entity instanceof TileDuster))
			return;
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glTranslated(x, y - 1 + ((TileDuster) entity).getHeight(), z);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		//TODO: use a model or a texture
		bindTexture(texture);

		double d = 1D / 8192;

		//NORTH
		tessellator.addVertexWithUV(0 + d, 0 + d, 0 + d, 1, 1);
		tessellator.addVertexWithUV(0 + d, 1 - d, 0 + d, 1, 0);
		tessellator.addVertexWithUV(1 - d, 1 - d, 0 + d, 0, 0);
		tessellator.addVertexWithUV(1 - d, 0 + d, 0 + d, 0, 1);

		//EAST
		tessellator.addVertexWithUV(1 - d, 0 + d, 0 + d, 0, 1);
		tessellator.addVertexWithUV(1 - d, 1 - d, 0 + d, 0, 0);
		tessellator.addVertexWithUV(1 - d, 1 - d, 1 - d, 1, 0);
		tessellator.addVertexWithUV(1 - d, 0 + d, 1 - d, 1, 1);

		//SOUTH
		tessellator.addVertexWithUV(1 - d, 0 + d, 1 - d, 0, 1);
		tessellator.addVertexWithUV(1 - d, 1 - d, 1 - d, 0, 0);
		tessellator.addVertexWithUV(0 + d, 1 - d, 1 - d, 1, 0);
		tessellator.addVertexWithUV(0 + d, 0 + d, 1 - d, 1, 1);

		//WEST
		tessellator.addVertexWithUV(0 + d, 0 + d, 1 - d, 1, 1);
		tessellator.addVertexWithUV(0 + d, 1 - d, 1 - d, 1, 0);
		tessellator.addVertexWithUV(0 + d, 1 - d, 0 + d, 0, 0);
		tessellator.addVertexWithUV(0 + d, 0 + d, 0 + d, 0, 1);

		tessellator.draw();

		tessellator.startDrawingQuads();

		//BOTTOM
		tessellator.addVertexWithUV(1 - d, 0 + d, 0 + d, 1, 1);
		tessellator.addVertexWithUV(1 - d, 0 + d, 1 - d, 1, 0);
		tessellator.addVertexWithUV(0 + d, 0 + d, 1 - d, 0, 0);
		tessellator.addVertexWithUV(0 + d, 0 + d, 0 + d, 0, 1);

		//TOP
		tessellator.addVertexWithUV(0 + d, 1 - d, 0 + d, 0, 1);
		tessellator.addVertexWithUV(0 + d, 1 - d, 1 - d, 0, 0);
		tessellator.addVertexWithUV(1 - d, 1 - d, 1 - d, 1, 0);
		tessellator.addVertexWithUV(1 - d, 1 - d, 0 + d, 1, 1);

		tessellator.draw();

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}
}

