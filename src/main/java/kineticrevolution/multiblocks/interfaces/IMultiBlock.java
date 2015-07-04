package kineticrevolution.multiblocks.interfaces;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by AEnterprise
 */
public interface IMultiBlock {

	void formMultiBlock(int masterXoffset, int masterYoffset, int masterZoffset, int rotation);

	void deformMultiBlock();

	boolean onBlockClicked(EntityPlayer player);

	void blockUpdate(int x, int y, int z);

	int getMasterX();

	int getMasterY();

	int getMasterZ();
}
