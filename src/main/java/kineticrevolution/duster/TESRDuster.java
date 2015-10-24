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

	public final IModelCustom MODEL = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID, "models/duster_frame.obj"));
	private final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "non-existant texture");

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float fl) {
		if (!(entity instanceof TileDuster))
			return;
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();

		GL11.glTranslated(x + 0.5, y - 2 + ((TileDuster) entity).getHeight(), z + 0.5);
		bindTexture(texture);
		MODEL.renderAll();

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}
}

