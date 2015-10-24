package kineticrevolution.items.blueprints;

import kineticrevolution.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by AEnterprise
 */
public class BlueprintItemRenderer implements IItemRenderer {
	private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "blueprint.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-10, 135, 0, 0, 1);
		tessellator.addVertexWithUV(135, 135, 0, 1, 1);
		tessellator.addVertexWithUV(135, -10, 0, 1, 0);
		tessellator.addVertexWithUV(-10, -10, 0, 0, 1);
		tessellator.draw();
		GL11.glPopMatrix();
	}
}
