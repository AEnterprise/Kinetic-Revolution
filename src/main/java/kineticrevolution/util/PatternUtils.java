package kineticrevolution.util;

import java.security.InvalidParameterException;

/**
 * Created by AEnterprise
 */
public class PatternUtils {

	public static char[][][] translate(char[][][] pattern, int x, int y, int z) {
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
		return newPattern;
	}

	public static char[][][] combine(char[][][] first, char[][][] second) {
		int x = Math.max(second.length, first.length);
		int y = Math.max(second[0].length, first[0].length);
		int z = Math.max(second[0][0].length, first[0][0].length);

		char[][][] newPattern = new char[x][y][z];


		//Copy all chars to the new maximized cubic array: comparison
		for (int i = 0; i < second.length; i++) {
			for (int j = 0; j < second[i].length; j++) {
				System.arraycopy(second[i][j], 0, newPattern[i][j], 0, second[i][j].length);
			}
		}


		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first[i].length; j++) {
				for (int k = 0; k < first[i][j].length; k++) {
					if (first[i][j][k] != -1) {
						newPattern[i][j][k] = first[i][j][k];
					}
				}
			}
		}
		return newPattern;
	}

	public static char[][][] intersect(char[][][] first, char[][][] second) {
		int x = Math.max(second.length, first.length);
		int y = Math.max(second[0].length, first[0].length);
		int z = Math.max(second[0][0].length, first[0][0].length);

		char[][][] newPattern = new char[x][y][z];
		char[][][] comparison = new char[x][y][z];

		//Copy all chars to the new maximized cubic array: comparison
		for (int i = 0; i < second.length; i++) {
			for (int j = 0; j < second[i].length; j++) {
				System.arraycopy(second[i][j], 0, comparison[i][j], 0, second[i][j].length);
			}
		}

		// Compare the current pattern with what is in the comparison. Equal elements get transferred.
		// Unequal elements get left out.
		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first[i].length; j++) {
				for (int k = 0; k < first[i][j].length; k++) {
					if (comparison[i][j][k] == first[i][j][k]) {
						newPattern[i][j][k] = first[i][j][k];
					}
				}
			}
		}
		return newPattern;
	}

}
