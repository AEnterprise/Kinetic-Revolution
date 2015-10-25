package kineticrevolution.duster;

import kineticrevolution.lib.Reference;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by AEnterprise
 */
public class TESRDuster extends TileEntitySpecialRenderer {

	public final IModelCustom FRAME = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID, "models/duster_frame.obj"));
	public final IModelCustom DRILL = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID, "models/duster_drill.obj"));
	private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/duster.png");
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float fl) {
		if (!(entity instanceof TileDuster))
			return;
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();

		GL11.glTranslated(x + 0.5, y - 1, z + 0.5);
		bindTexture(TEXTURE);
		FRAME.renderAll();

		GL11.glTranslated(0, -0.5, 0);
		DRILL.renderAll();

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}
}

