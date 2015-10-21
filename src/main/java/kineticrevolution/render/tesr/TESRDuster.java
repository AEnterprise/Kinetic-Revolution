package kineticrevolution.render.tesr;

import kineticrevolution.tileEntities.TileDuster;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by AEnterprise
 */
public class TESRDuster extends TileEntitySpecialRenderer {


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
		bindTexture(new ResourceLocation("kineticrevlution", "non-existant texture"));

		//NORTH
		tessellator.addVertexWithUV(0, 0, 0, 1, 1);
		tessellator.addVertexWithUV(0, 1, 0, 1, 0);
		tessellator.addVertexWithUV(1, 1, 0, 0, 0);
		tessellator.addVertexWithUV(1, 0, 0, 0, 1);

		//EAST
		tessellator.addVertexWithUV(1, 0, 0, 0, 1);
		tessellator.addVertexWithUV(1, 1, 0, 0, 0);
		tessellator.addVertexWithUV(1, 1, 1, 1, 0);
		tessellator.addVertexWithUV(1, 0, 1, 1, 1);

		//SOUTH
		tessellator.addVertexWithUV(1, 0, 1, 0, 1);
		tessellator.addVertexWithUV(1, 1, 1, 0, 0);
		tessellator.addVertexWithUV(0, 1, 1, 1, 0);
		tessellator.addVertexWithUV(0, 0, 1, 1, 1);

		//WEST
		tessellator.addVertexWithUV(0, 0, 1, 1, 1);
		tessellator.addVertexWithUV(0, 1, 1, 1, 0);
		tessellator.addVertexWithUV(0, 1, 0, 0, 0);
		tessellator.addVertexWithUV(0, 0, 0, 0, 1);
		tessellator.draw();

		tessellator.startDrawingQuads();

		//BOTTOM
		tessellator.addVertexWithUV(1, 0, 0, 1, 1);
		tessellator.addVertexWithUV(1, 0, 1, 1, 0);
		tessellator.addVertexWithUV(0, 0, 1, 0, 0);
		tessellator.addVertexWithUV(0, 0, 0, 0, 1);

		//TOP
		tessellator.addVertexWithUV(0, 1, 0, 0, 1);
		tessellator.addVertexWithUV(0, 1, 1, 0, 0);
		tessellator.addVertexWithUV(1, 1, 1, 1, 0);
		tessellator.addVertexWithUV(1, 1, 0, 1, 1);


		tessellator.draw();
		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}
}
