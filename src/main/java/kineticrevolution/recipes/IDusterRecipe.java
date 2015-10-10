package kineticrevolution.recipes;

import net.minecraft.world.World;

import java.util.List;

/**
 * Created by AEnterprise
 */
public interface IDusterRecipe {

	boolean validInput(World world, int x, int y, int z);

	List<DusterOutput> getOutputs();

}
