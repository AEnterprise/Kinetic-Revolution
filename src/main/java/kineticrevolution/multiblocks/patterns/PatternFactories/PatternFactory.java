package kineticrevolution.multiblocks.patterns.PatternFactories;

import kineticrevolution.multiblocks.patterns.Pattern;
import kineticrevolution.multiblocks.patterns.PatternException;
import kineticrevolution.multiblocks.patterns.PatternFactories.Factories.*;

import java.util.ArrayList;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public final class PatternFactory {
    final static ArrayList<IPatternFactory> factories = new ArrayList<>();
    static {
        factories.add(new SolidCubeFactory());  //Factory #0
        factories.add(new LineFactory());       //Factory #1
        factories.add(new TriangleFactory());   //Factory #2
        factories.add(new HollowCubeFactory()); //Factory #3
        factories.add(new PlaneFactory());      //Factory #4
    }

    //Create a pattern that resembles a solid cube.
    public static Pattern createSolidCube(int size, char a) throws PatternException {
        return factories.get(0).createPattern(size, a);
    }

    //Create a pattern that resembles a beam of length size and orientated around the Z-axis
    public static Pattern createLine(int size, char a) throws PatternException {
        return factories.get(1).createPattern(size, a);
    }

    public static Pattern createTriangle(int size,char a) throws PatternException {
        return factories.get(2).createPattern(size, a);
    }

    public static Pattern createHollowCube(int size,char a) throws PatternException {
        return factories.get(3).createPattern(size, a);
    }

    public static Pattern createPlane(int size, char a)throws PatternException{
        return factories.get(4).createPattern(size, a);
    }
}
