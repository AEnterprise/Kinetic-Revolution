package kineticrevolution.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

import kineticrevolution.client.render.models.RendererGenerator;
import kineticrevolution.duster.TESRDuster;
import kineticrevolution.duster.TileDuster;
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

	@Override
	public EntityPlayer getClientPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getWorldClient();
	}
}
