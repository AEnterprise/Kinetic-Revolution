package kineticrevolution.multiblocks.patterns;

import java.security.InvalidParameterException;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public final class Pattern {

	final char[][][] pattern;

	public Pattern(char[][][] pattern) {
		this.pattern = pattern;
	}

	public Pattern translation(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0)
			throw new InvalidParameterException("Can't translate a pattern with negative delta");

		int i = pattern.length;
		int j = pattern[0].length;
		int k = pattern[0][0].length;

		char[][][] newPattern = new char[i + x][j + y][k + z];
		for (int l = 0; l < i; l++) {
			for (int m = 0; m < j; m++) {
				System.arraycopy(pattern[l][m], 0, newPattern[l + x][m + y], z, k);
			}
		}
		return new Pattern(newPattern);

	}

	public char[][][] getPattern() {
		return pattern;
	}


	/*
	* Make a cubic array viewable when printed out.
	 */
	@Override
	public String toString() {
		String test = "";
		for (char[][] x : pattern) {
			for (char[] y : x) {
				test += "[";
				for (char z : y) {
					test += z;
				}
				test += "] ";
			}
			test += "\n";
		}
		return test;
	}

	public char getCharAt(int x, int y, int z) {
		return pattern[x][y][z];
	}


	/*
	* Could possibly need a rewrite.
	 */
	public Pattern intersect(Pattern other) {
		char[][][] patternOther = other.getPattern();
		int x = Math.max(patternOther.length, pattern.length);
		int y = Math.max(patternOther[0].length, pattern[0].length);
		int z = Math.max(patternOther[0][0].length, pattern[0][0].length);

		char[][][] newPattern = new char[x][y][z];
		char[][][] comparison = new char[x][y][z];

		//Copy all chars to the new maximized cubic array: comparison
		for (int i = 0; i < patternOther.length; i++) {
			for (int j = 0; j < patternOther[i].length; j++) {
				System.arraycopy(patternOther[i][j], 0, comparison[i][j], 0, patternOther[i][j].length);
			}
		}

		// Compare the current pattern with what is in the comparison. Equal elements get transferred.
		// Unequal elements get left out.
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[i].length; j++) {
				for (int k = 0; k < pattern[i][j].length; k++) {
					if (comparison[i][j][k] == pattern[i][j][k]) {
						newPattern[i][j][k] = pattern[i][j][k];
					}
				}
			}
		}
		return new Pattern(newPattern);
	}

	/*
	* Warning: The pattern on which the operation is called is the primary one. When conflicts happen, the primary will
	* remain.
	* TODO : Make it a list of Pattern's instead of single pattern with single pattern.
	 */
	public Pattern combine(Pattern other) {
		char[][][] patternOther = other.getPattern();
		int x = Math.max(patternOther.length, pattern.length);
		int y = Math.max(patternOther[0].length, pattern[0].length);
		int z = Math.max(patternOther[0][0].length, pattern[0][0].length);

		char[][][] newPattern = new char[x][y][z];


		//Copy all chars to the new maximized cubic array: comparison
		for (int i = 0; i < patternOther.length; i++) {
			for (int j = 0; j < patternOther[i].length; j++) {
				System.arraycopy(patternOther[i][j], 0, newPattern[i][j], 0, patternOther[i][j].length);
			}
		}


		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[i].length; j++) {
				for (int k = 0; k < pattern[i][j].length; k++) {
					if (pattern[i][j][k] != -1) {
						newPattern[i][j][k] = pattern[i][j][k];
					}
				}
			}
		}
		return new Pattern(newPattern);
	}
}
