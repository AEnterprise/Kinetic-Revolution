package kineticrevolution.multiblocks.patterns.PatternFactories;

import kineticrevolution.multiblocks.patterns.PatternFactories.Factories.*;

/**
 * Created by AEnterprise
 */
public enum PatternTypes {

	SOLID_CUBE(new SolidCubeFactory()),
	HOLOW_CUBE(new HollowCubeFactory()),
	LINE(new LineFactory()),
	TRIANGLE(new TriangleFactory()),
	PLANE(new PlaneFactory()),
	BEAM(new BeamFactory()),
	HOLOW_BEAM(new HollowBeamFactory());

	private final IPatternFactory factory;

	PatternTypes(IPatternFactory factory) {
		this.factory = factory;
	}

	public IPatternFactory getFactory() {
		return factory;
	}
}
