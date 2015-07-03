package kineticrevolution.multiblocks.patterns.PatternFactories;

/**
 * Created by Mathieu on 3/07/2015.
 */
public class SolidCubeFactorie implements IPatternFactorie {
    @Override
    public char[][][] createPattern(int size, char a) {
        char[][][] pattern = new char[size][size][size];
        for (char[][] x: pattern){
            for (char[] y: x){
                for (char z: y){
                    z = a;
                }
            }
        }
        return pattern;
    }
}
