package kineticrevolution.multiblocks.patterns.patternFactories.factories;

import kineticrevolution.multiblocks.patterns.Pattern;
import kineticrevolution.multiblocks.patterns.patternFactories.IPatternFactory;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class PlaneFactory implements IPatternFactory {

	@Override
	public Pattern createPattern(char a, int... size) {
		int xSize, ySize;
		if (size.length == 1) {
			xSize = size[0];
			ySize = size[0];
		} else if (size.length == 2) {
			xSize = size[0];
			ySize = size[1];
		} else {
			throw new InvalidParameterException("Invalid amount of arguments given for size");
		}
		char[][][] pattern = new char[xSize][ySize][1];
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				pattern[i][j][0] = a;
			}
		}
		return new Pattern(pattern);
	}
}
