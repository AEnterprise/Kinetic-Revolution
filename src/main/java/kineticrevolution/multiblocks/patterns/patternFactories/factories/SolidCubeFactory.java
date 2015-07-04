package kineticrevolution.multiblocks.patterns.patternFactories.factories;

import kineticrevolution.multiblocks.patterns.patternFactories.IPatternFactory;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class SolidCubeFactory implements IPatternFactory {

	@Override
	public char[][][] createPattern(char a, int... args) {
		if (args.length != 1)
			throw new InvalidParameterException("Invalid size list given");


		int size = args[0];

		char[][][] pattern = new char[size][size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					pattern[i][j][k] = a;
				}
			}
		}
		return pattern;
	}
}
