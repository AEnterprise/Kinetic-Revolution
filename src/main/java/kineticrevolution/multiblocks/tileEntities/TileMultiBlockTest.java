package kineticrevolution.multiblocks.tileEntities;

import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by AEnterprise
 */
public class TileMultiBlockTest extends TileEntity implements IMultiBlock {

	@Override
	public void formMultiBlock(int masterXoffset, int masterYoffset, int masterZoffset, int rotation) {
		System.out.println("Forming multiblock");
	}

	@Override
	public boolean onBlockClicked(EntityPlayer player) {
		System.out.println("Click Event Recieved from" + player.getDisplayName());
		return true;
	}
}
