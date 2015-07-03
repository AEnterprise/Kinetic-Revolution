package kineticrevolution.multiblocks.patterns;

import kineticrevolution.multiblocks.definitions.DefinitionTest;
import kineticrevolution.multiblocks.interfaces.IBlockDefinition;

import java.util.HashMap;

/**
 * Created by AEnterprise
 */
public class Patterns {

	public static final MultiBlockPattern TEST_PATTERN;

	static {

		char[][][] pattern = new char[][][]{
				{
						{'a', 'a', 'a'},
						{'a', 'a', 'a'},
						{'a', 'a', 'a'}
				},
				{
						{'a', 'a', 'a'},
						{'a', 'a', 'a'},
						{'a', 'a', 'a'}
				},
				{
						{'a', 'a', 'a'},
						{'a', 'a', 'a'},
						{'a', 'a', 'a'}
				}
		};

		HashMap<Character, IBlockDefinition> definitions = new HashMap<Character, IBlockDefinition>();
		definitions.put('a', new DefinitionTest());
		TEST_PATTERN = new MultiBlockPattern(pattern, definitions, 3, 3, 3);
	}
}
