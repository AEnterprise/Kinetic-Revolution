package kineticrevolution.multiblocks.tileEntities;

import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import kineticrevolution.multiblocks.patterns.Patterns;
import kineticrevolution.util.MultiBlockData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by AEnterprise
 */
public class TileMultiBlockBase extends TileEntity implements IMultiBlock {
	private MultiBlockData data;

	@Override
	public void formMultiBlock(int masterXoffset, int masterYoffset, int masterZoffset, int rotation) {
		System.out.println(String.format("Forming multiblock, master location (x:%s, y:%s, z:%s), rotation:%s, own location(x:%s, y:%s,z:%s)", xCoord + masterXoffset, yCoord + masterYoffset, zCoord + masterZoffset, rotation, xCoord, yCoord, zCoord));
		data = new MultiBlockData(masterXoffset, masterYoffset, masterZoffset, rotation, Patterns.TEST_PATTERN);
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
	}

	@Override
	public void deformMultiBlock() {
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
	}

	@Override
	public boolean onBlockClicked(EntityPlayer player) {
		return data != null;
	}

	@Override
	public void blockUpdate(int x, int y, int z) {

	}

	@Override
	public int getMasterX() {
		return xCoord + data.getMasterXoffset();
	}

	@Override
	public int getMasterY() {
		return yCoord + data.getMasterYoffset();
	}

	@Override
	public int getMasterZ() {
		return zCoord + data.getMasterZoffset();
	}
}
