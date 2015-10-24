package kineticrevolution.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class CommonProxy {

	public void setupRenderers() {
		//NO-OP
	}

	public EntityPlayer getClientPlayer() {
		return null;
	}

	public World getClientWorld() {
		return null;
	}
}

