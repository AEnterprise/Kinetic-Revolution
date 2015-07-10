package kineticrevolution.multiblocks.patterns.patternFactories;


import kineticrevolution.multiblocks.patterns.patternFactories.factories.BeamFactory;
import kineticrevolution.multiblocks.patterns.patternFactories.factories.HollowBeamFactory;
import kineticrevolution.multiblocks.patterns.patternFactories.factories.HollowCubeFactory;
import kineticrevolution.multiblocks.patterns.patternFactories.factories.LineFactory;
import kineticrevolution.multiblocks.patterns.patternFactories.factories.PlaneFactory;
import kineticrevolution.multiblocks.patterns.patternFactories.factories.SolidCubeFactory;
import kineticrevolution.multiblocks.patterns.patternFactories.factories.TriangleFactory;

/**
 * Created by AEnterprise
 */
public enum PatternTypes {

	SOLID_CUBE(new SolidCubeFactory()),
	HOLLOW_CUBE(new HollowCubeFactory()),
	LINE(new LineFactory()),
	TRIANGLE(new TriangleFactory()),
	PLANE(new PlaneFactory()),
	SOLID_BEAM(new BeamFactory()),
	HOLLOW_BEAM(new HollowBeamFactory());

	private final IPatternFactory factory;

	PatternTypes(IPatternFactory factory) {
		this.factory = factory;
	}

	public IPatternFactory getFactory() {
		return factory;
	}

	public char[][][] createPattern(char a, int... size) {
		return factory.createPattern(a, size);
	}
}
