package kineticrevolution.multiblocks.patterns;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * Created by AEnterprise
 */
public class MultiBlockPattern {
	private final char[][][] pattern;
	private final HashMap<Character, IBlockDefinition> definitions;
	private final int xSize, ySize, zSize;

	public MultiBlockPattern(char[][][] pattern, HashMap<Character, IBlockDefinition> definitions, int xSize, int ySize, int zSize) {
		if (pattern == null)
			throw new InvalidParameterException("A pattern is required!");
		if (definitions == null)
			throw new InvalidParameterException("Definitions are required!");
		this.pattern = pattern;
		this.definitions = definitions;
		if (ySize != pattern.length)
			throw new InvalidParameterException("ySize and pattern y length don't match!");
		for (int y = 0; y < ySize; y++) {
			if (xSize != pattern[y].length)
				throw new InvalidParameterException("xSize and pattern x length don't match!");
			for (int x = 0; x < xSize; x++) {
				if (zSize != pattern[y][x].length)
					throw new InvalidParameterException("zSize and pattern z length don't match!");
				for (int z = 0; z < zSize; z++) {
					if (!definitions.containsKey(pattern[y][x][z]))
						throw new InvalidParameterException("Missing block definition for char (" + pattern[y][x][z] + "). Pattern invalid");
				}
			}
		}
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
	}

	public boolean isValid(World world, int startX, int startY, int startZ) {
		//TODO: implement rotation
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				for (int z = 0; z < zSize; z++) {
					if (!definitions.get(pattern[y][x][z]).matches(world, startX + x, startY + y, startZ + z))
						return false;
				}
			}
		}
		return true;
	}

	public void formMultiblock(World world, int startX, int startY, int startZ) {
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				for (int z = 0; z < zSize; z++) {
					TileEntity entity = world.getTileEntity(startX, startY, startZ);
					if (entity instanceof IMultiBlock) {
						((IMultiBlock) entity).formMultiBlock(x, y, z, 0);//TODO: implement rotation
					}
				}

			}
		}
	}
}
