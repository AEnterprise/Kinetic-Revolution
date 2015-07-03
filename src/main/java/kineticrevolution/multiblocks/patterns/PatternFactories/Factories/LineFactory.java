package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class LineFactory implements IPatternFactory {

    @Override
    public Pattern createPattern(int size, char a) {
        char[][][] pattern = new char[1][1][size];
        for (int i = 0; i < size;i ++){
            pattern[0][0][i] = a;
        }
        return new Pattern(pattern);
    }
}
