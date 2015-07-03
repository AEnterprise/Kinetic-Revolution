package kineticrevolution.multiblocks.patterns.PatternFactories;

import kineticrevolution.multiblocks.patterns.Pattern;
import kineticrevolution.multiblocks.patterns.PatternException;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public interface IPatternFactory {

    public Pattern createPattern(int size, char a) throws PatternException;
}
