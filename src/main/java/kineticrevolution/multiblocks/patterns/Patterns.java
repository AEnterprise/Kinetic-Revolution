package kineticrevolution.multiblocks.patterns;

import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.multiblocks.definitions.DefinitionExact;
import kineticrevolution.multiblocks.interfaces.IBlockDefinition;

import java.util.HashMap;

/**
 * Created by AEnterprise
 */
public class Patterns {

	public static MultiBlockPattern TEST_PATTERN;

	public static void compilePatterns() {

		char[][][] pattern = new char[][][]{
				{
						{'a', 'a'},
						{'a', 'a'},
						{'a', 'a'}
				},
				{
						{'a', 'a'},
						{'a', 'a'},
						{'a', 'a'}
				},
				{
						{'a', 'a'},
						{'a', 'a'},
						{'a', 'a'}
				}
		};

		HashMap<Character, IBlockDefinition> definitions = new HashMap<Character, IBlockDefinition>();
		definitions.put('a', new DefinitionExact(BlockLoader.test));
		TEST_PATTERN = new MultiBlockPattern(pattern, definitions, 3, 3, 2);
	}
}
