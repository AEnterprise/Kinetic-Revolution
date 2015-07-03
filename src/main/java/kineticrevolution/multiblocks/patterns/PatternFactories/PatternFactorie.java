package kineticrevolution.multiblocks.patterns.PatternFactories;

/**
 * Created by Mathieu on 3/07/2015.
 */
public final class PatternFactorie {

    public static char[][][]  createSolidCube(int size, char a){
        return new SolidCubeFactorie().createPattern(size,a);
    }

}
