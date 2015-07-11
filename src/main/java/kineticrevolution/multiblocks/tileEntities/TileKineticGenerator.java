package kineticrevolution.multiblocks.tileEntities;

import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import kineticrevolution.multiblocks.patterns.Patterns;

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
}
