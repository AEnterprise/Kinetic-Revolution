package kineticrevolution.recipes;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by AEnterprise
 */
public interface IDusterRecipe {

	boolean validInput(ItemStack input);

	List<DusterOutput> getOutputs();

}
