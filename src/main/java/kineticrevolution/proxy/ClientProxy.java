package kineticrevolution.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import kineticrevolution.multiblocks.tileEntities.TileKineticGenerator;
import kineticrevolution.render.models.RendererGenerator;

/**
 * Created by AEnterprise
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void setupRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileKineticGenerator.class, new RendererGenerator());
	}
}
