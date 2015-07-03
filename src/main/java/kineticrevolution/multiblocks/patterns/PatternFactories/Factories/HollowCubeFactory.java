package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class HollowCubeFactory implements IPatternFactory {
    @Override
    public Pattern createPattern(int size, char a) throws PatternException {
        char[][][] pattern = new char[size][size][size];
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                for (int k = 0; k < size ; k++) {
                    if (i == 0 || j == 0 || k == 0 || i == size-1 || j == size-1|| k == size-1 ){
                        pattern[i][j][k] = a;
                    }
                }
            }
        }

        return new Pattern(pattern);
    }
}
