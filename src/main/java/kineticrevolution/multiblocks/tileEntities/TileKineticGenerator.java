package kineticrevolution.multiblocks.tileEntities;

import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import kineticrevolution.multiblocks.patterns.Patterns;
import net.minecraft.util.AxisAlignedBB;

/**
 * Created by AEnterprise
 */
public class TileKineticGenerator extends TileMultiBlockBase {

	@Override
	public MultiBlockPattern getPattern() {
		return Patterns.KINETIC_GENERATOR;
	}

	@Override
	public int getIdentifier() {
		return 0;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord - 2, zCoord - 2, xCoord + 2, yCoord + 2, zCoord + 2);
	}
}
