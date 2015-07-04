package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.Pattern;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class TriangleFactory implements IPatternFactory {

	// Create a triangular pattern with height and width size. Depth = 1
	// Can be cleaner. Tested
	@Override
	public Pattern createPattern(char a, int... args) {
		if (args.length != 1)
			throw new InvalidParameterException("Invalid size list given");

		int size = args[0];

		if (size % 2 != 1)
			throw new InvalidParameterException("Could not create a triangular pattern with even size");

		char[][][] pattern = new char[size][(size + 1) / 2][1];
		for (int i = (size - 1) / 2; i >= -1; i--) {
			for (int j = 0; j < i; j++) {
				pattern[i][j][0] = a;
				pattern[size - i - 1][j][0] = a;
			}
		}
		for (int p = 0; p < (size + 1) / 2; p++) {
			pattern[p][p][0] = a;
			pattern[size - p - 1][p][0] = a;
		}
		return new Pattern(pattern);
	}
}
