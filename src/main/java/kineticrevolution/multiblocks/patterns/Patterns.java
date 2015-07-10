package kineticrevolution.multiblocks.patterns;

import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.multiblocks.definitions.DefinitionExact;
import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import kineticrevolution.multiblocks.patterns.patternFactories.PatternTypes;

import java.util.HashMap;

/**
 * Created by AEnterprise
 */
public class Patterns {

	public static MultiBlockPattern TEST_PATTERN;
	public static MultiBlockPattern KINETIC_GENERATOR;

	public static void compilePatterns() {

		char[][][] pattern = PatternTypes.SOLID_BEAM.createPattern('a', 3, 3, 2);

		HashMap<Character, IBlockDefinition> definitions = new HashMap<Character, IBlockDefinition>();
		definitions.put('a', new DefinitionExact(BlockLoader.test));
		TEST_PATTERN = new MultiBlockPattern(pattern, definitions, 3, 3, 2);

		pattern = new char[][][]{
				{
						{'i', 'c', 'c'},
						{'i', 'c', 'c'},
						{'i', 'c', 'c'}
				},
				{
						{'i', 'c', 'c'},
						{'i', 'c', 'c'},
						{'i', 'c', 'c'}
				},
				{
						{'i', 'c', 'c'},
						{'i', 'c', 'c'},
						{'i', 'c', 'c'}
				},
		};

		definitions = new HashMap<Character, IBlockDefinition>();
		definitions.put('i', new DefinitionExact(BlockLoader.generatorInductor));
		definitions.put('c', new DefinitionExact(BlockLoader.generatorCasing));
		KINETIC_GENERATOR = new MultiBlockPattern(pattern, definitions, 3, 3, 3);
	}
}
