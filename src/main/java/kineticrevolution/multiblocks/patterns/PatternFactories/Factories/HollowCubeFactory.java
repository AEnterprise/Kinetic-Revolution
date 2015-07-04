package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class HollowCubeFactory implements IPatternFactory {
    @Override
    public Pattern createPattern(char a,int... size) throws PatternException {
        if (size.length != 1){
            throw new PatternException("Invalid size list given");
        }

        int Isize = size[0];
        char[][][] pattern = new char[Isize][Isize][Isize];
        for (int i = 0; i < Isize ; i++) {
            for (int j = 0; j < Isize ; j++) {
                for (int k = 0; k < Isize ; k++) {
                    if (i == 0 || j == 0 || k == 0 || i == Isize-1 || j == Isize-1|| k == Isize-1 ){
                        pattern[i][j][k] = a;
                    }
                }
            }
        }

        return new Pattern(pattern);
    }
}
