package kineticrevolution.multiblocks.patterns;

import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.multiblocks.definitions.DefinitionExact;
import kineticrevolution.multiblocks.interfaces.IBlockDefinition;

import java.util.HashMap;

/**
 * Created by AEnterprise
 */
public class Patterns {

	public static MultiBlockPattern KINETIC_GENERATOR;

	public static void compilePatterns() {
		char[][][] pattern = new char[][][]{
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

		HashMap<Character, IBlockDefinition> definitions = new HashMap<Character, IBlockDefinition>();
		definitions.put('i', new DefinitionExact(BlockLoader.generatorInductor));
		definitions.put('c', new DefinitionExact(BlockLoader.generatorCasing));
		KINETIC_GENERATOR = new MultiBlockPattern(pattern, definitions, 3, 3, 3, 1, 1, 1);
	}
}
