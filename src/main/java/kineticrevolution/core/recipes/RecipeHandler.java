package kineticrevolution.core.recipes;

import kineticrevolution.core.recipes.input.IRecipeInput;
import kineticrevolution.core.recipes.input.RecipeInputBlank;
import kineticrevolution.core.recipes.input.RecipeInputItemStack;
import kineticrevolution.core.recipes.input.RecipeInputOreDict;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AEnterprise
 */
public class RecipeHandler<T> {
	protected final int inputSize;
	protected final Map<IRecipeInput[], T> recipes = new HashMap<IRecipeInput[], T>();

	protected RecipeHandler(int inputSize) {
		this.inputSize = inputSize;
	}

	public void addRecipe(T output, Object... inputs) {
		if (inputs.length != inputSize)
			throw new InvalidParameterException(String.format("Tried to register a recipe with invalid input size! Got %d, expected %d", inputs.length, inputSize));
		IRecipeInput[] recipeInputs = new IRecipeInput[inputSize];
		for (int i = 0; i < inputSize; i++) {
			if (inputs[i] instanceof String)
				recipeInputs[i] = new RecipeInputOreDict((String) inputs[i]);
			if (inputs[i] instanceof Item)
				inputs[i] = new ItemStack((Item) inputs[i]);
			if (inputs[i] instanceof Block)
				inputs[i] = new ItemStack((Block) inputs[i]);
			if (inputs[i] instanceof ItemStack)
				recipeInputs[i] = new RecipeInputItemStack((ItemStack) inputs[i]);
			if (inputs[i] == null) {
				recipeInputs[i] = new RecipeInputBlank();
			}
		}
		recipes.put(recipeInputs, output);
	}

	public T getOutput(Object... inputs) {
		if (inputs.length != inputSize)
			return null;
		for (Map.Entry<IRecipeInput[], T> entry : recipes.entrySet()) {
			boolean found = true;
			for (int i = 0; i < inputSize; i++) {
				if (!entry.getKey()[i].isValid(inputs[i]))
					found = false;
			}
			if (found)
				return entry.getValue();
		}
		return null;
	}
}
