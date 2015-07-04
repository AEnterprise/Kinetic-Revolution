package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.Pattern;
import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;

/**
 * Created by MrKickkiller on 4/07/2015.
 */
public class BeamFactory implements IPatternFactory {

    @Override
    public Pattern createPattern(char a, int... size) throws PatternException {
        if (size.length != 3){
            throw new PatternException("Invalid amount of size arguments given");
        }

        int x = size[0];
        int y = size[1];
        int z = size[2];

        char[][][] newPattern = new char[x][y][z];
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y ; j++) {
                for (int k = 0; k < z ; k++) {
                    newPattern[i][j][k] = a;
                }
            }
        }

        return new Pattern(newPattern);
    }
}
