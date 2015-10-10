package kineticrevolution.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IDusterRecipe {

	boolean isValidInput(World world, int x, int y, int z);

	List<IChancedOutput> getOutputs();

	List<ItemStack> getExampleInputs();

}
