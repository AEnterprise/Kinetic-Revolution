package kineticrevolution.render.models;

import kineticrevolution.lib.Reference;
import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by AEnterprise
 */
public class RendererGenerator extends TileEntitySpecialRenderer {
	public final IModelCustom MODEL = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID, "models/generator.obj"));
	public final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/generator.png");

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float fl) {
		if (entity instanceof IMultiBlock && ((IMultiBlock) entity).isMaster()) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			MODEL.renderAll();
			GL11.glPopMatrix();

		}
	}
}
