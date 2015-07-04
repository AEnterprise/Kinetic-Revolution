package kineticrevolution.multiblocks.patterns.patternFactories.factories;

import kineticrevolution.multiblocks.patterns.patternFactories.IPatternFactory;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 4/07/2015.
 */
public class BeamFactory implements IPatternFactory {

	@Override
	public char[][][] createPattern(char a, int... size) {
		if (size.length != 3)
			throw new InvalidParameterException("Invalid amount of size arguments given");

		int x = size[0];
		int y = size[1];
		int z = size[2];

		char[][][] newPattern = new char[x][y][z];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < z; k++) {
					newPattern[i][j][k] = a;
				}
			}
		}

		return newPattern;
	}
}
