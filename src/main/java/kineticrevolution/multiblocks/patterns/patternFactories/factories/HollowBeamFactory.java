package kineticrevolution.multiblocks.patterns.patternFactories.factories;

import kineticrevolution.multiblocks.patterns.patternFactories.IPatternFactory;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 4/07/2015.
 */
public class HollowBeamFactory implements IPatternFactory {
	@Override
	public char[][][] createPattern(char a, int... size) {
		if (size.length != 3) {
			throw new InvalidParameterException("Invalid amount of size arguments given");
		}

		int x = size[0];
		int y = size[1];
		int z = size[2];

		char[][][] newPattern = new char[x][y][z];

		for (int i = 0; i < x; i++) {
			newPattern[i][y - 1][z - 1] = a;
			newPattern[i][0][z - 1] = a;
			newPattern[i][y - 1][0] = a;
			newPattern[i][0][0] = a;
		}

		for (int j = 0; j < y; j++) {
			newPattern[x - 1][j][z - 1] = a;
			newPattern[0][j][z - 1] = a;
			newPattern[x - 1][j][0] = a;
			newPattern[0][j][0] = a;
		}

		for (int k = 0; k < z; k++) {
			newPattern[x - 1][y - 1][k] = a;
			newPattern[0][y - 1][k] = a;
			newPattern[x - 1][0][k] = a;
			newPattern[0][0][k] = a;
		}

		return newPattern;
	}
}
