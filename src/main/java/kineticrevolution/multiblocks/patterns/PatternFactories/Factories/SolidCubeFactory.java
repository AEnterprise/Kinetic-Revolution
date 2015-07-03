package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class SolidCubeFactory implements IPatternFactory {
    @Override
    public Pattern createPattern(int size, char a) {
        char[][][] pattern = new char[size][size][size];
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                for (int k = 0; k <size ; k++) {
                    pattern[i][j][k] = a;
                }
            }
        }
        return new Pattern(pattern);
    }
}
