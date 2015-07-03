package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by Mathieu on 3/07/2015.
 */
public class PlaneFactory implements IPatternFactory {
    @Override
    public Pattern createPattern(int size, char a) throws PatternException {
        char[][][] pattern = new char[size][size][1];
        for (int i = 0; i < size;i++){
            for (int j = 0; j < size ; j++) {
                pattern[i][j][0] = a;
            }
        }
        return new Pattern(pattern);
    }
}
