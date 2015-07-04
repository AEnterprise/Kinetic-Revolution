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
        factories.add(new BeamFactory());       //Factory #5
        factories.add(new HollowBeamFactory()); //Factory #6
    }

    //Create a pattern that resembles a solid cube.
    public static Pattern createSolidCube(char a, int size) throws PatternException {
        return factories.get(0).createPattern(a, size);
    }

    //Create a pattern that resembles a beam of length size and orientated around the Z-axis
    public static Pattern createLine(char a, int size) throws PatternException {
        return factories.get(1).createPattern(a,size);
    }

    public static Pattern createTriangle(char a, int size) throws PatternException {
        return factories.get(2).createPattern(a, size);
    }

    public static Pattern createHollowCube(char a,int size) throws PatternException {
        return factories.get(3).createPattern(a, size);
    }

    public static Pattern createPlane(char a, int...size)throws PatternException{
        return factories.get(4).createPattern(a,size);
    }

    public static Pattern createBeam(char a, int...size)throws PatternException{
        return factories.get(5).createPattern(a,size);
    }

    public static Pattern createHollowBeam(char a, int...size)throws PatternException{
        return factories.get(6).createPattern(a,size);
    }
}
