package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class LineFactory implements IPatternFactory {

    @Override
    public Pattern createPattern( char a,int... size) throws PatternException {
        if (size.length != 1){
            throw new PatternException("Invalid size list given");
        }

        int Isize = size[0];
        char[][][] pattern = new char[1][1][Isize];
        for (int i = 0; i < Isize;i ++){
            pattern[0][0][i] = a;
        }
        return new Pattern(pattern);
    }
}