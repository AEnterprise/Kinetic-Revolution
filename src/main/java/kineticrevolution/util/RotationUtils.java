package kineticrevolution.util;

/**
 * Created by AEnterprise
 */
public class RotationUtils {

	public static char[][] rotate(char[][] array, int amount) {
		char[][] result = array;
		for (int i = 0; i < amount; i++) {
			result = rotate(result);
		}
		return result;
	}

	public static char[][] rotate(char[][] array) {
		final int i = array.length;
		final int j = array[0].length;
		char[][] result = new char[j][i];
		for (int r = 0; r < i; r++) {
			for (int c = 0; c < j; c++) {
				result[c][i - 1 - r] = array[r][c];
			}
		}
		return result;
	}
}
