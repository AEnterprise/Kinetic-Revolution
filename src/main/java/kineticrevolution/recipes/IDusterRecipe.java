package kineticrevolution.recipes;

import net.minecraft.block.Block;

import java.util.List;

/**
 * Created by AEnterprise
 */
public interface IDusterRecipe {

	boolean validInput(Block block, int meta);

	List<DusterOutput> getOutputs();

}
