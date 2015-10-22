package kineticrevolution.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import kineticrevolution.duster.TESRDuster;
import kineticrevolution.duster.TileDuster;
import kineticrevolution.render.models.RendererGenerator;
import kineticrevolution.tileEntities.TileKineticGenerator;

/**
 * Created by AEnterprise
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void setupRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileKineticGenerator.class, new RendererGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileDuster.class, new TESRDuster());
	}
}
