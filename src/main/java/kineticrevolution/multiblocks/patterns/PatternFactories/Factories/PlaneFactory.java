package kineticrevolution.multiblocks.patterns.PatternFactories.Factories;

import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.IPatternFactory;
import kineticrevolution.multiblocks.patterns.Pattern;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class PlaneFactory implements IPatternFactory {
    @Override
    public Pattern createPattern(char a, int... size) throws PatternException {
        int xSize, ySize;
        if (size.length == 1){
            xSize = size[0]; ySize = size[0];
        }else if (size.length == 2){
            xSize = size[0]; ySize = size[1];
        }else {
            throw new PatternException("Invalid amount of arguments given for size");
        }
        char[][][] pattern = new char[xSize][ySize][1];
        for (int i = 0; i < xSize;i++){
            for (int j = 0; j < ySize ; j++) {
                pattern[i][j][0] = a;
            }
        }
        return new Pattern(pattern);
    }
}
