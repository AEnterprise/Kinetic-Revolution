package kineticrevolution.multiblocks.patterns.patternFactories.factories;

import kineticrevolution.multiblocks.patterns.patternFactories.IPatternFactory;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class LineFactory implements IPatternFactory {

	@Override
	public char[][][] createPattern(char a, int... args) {
		if (args.length != 1)
			throw new InvalidParameterException("Invalid size list given");

		int size = args[0];
		char[][][] pattern = new char[1][1][size];
		for (int i = 0; i < size; i++) {
			pattern[0][0][i] = a;
		}
		return pattern;
	}
}
