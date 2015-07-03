package kineticrevolution.multiblocks.interfaces;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by AEnterprise
 */
public interface IMultiBlock {

	void formMultiBlock(int masterXoffset, int masterYoffset, int masterZoffset, int rotation);

	boolean onBlockClicked(EntityPlayer player);
}
