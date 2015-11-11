package kineticrevolution.core.recipes.input;

import kineticrevolution.util.Utils;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class RecipeInputItemStack implements IRecipeInput {
	private final ItemStack target;

	public RecipeInputItemStack(ItemStack target) {
		this.target = target;
	}

	@Override
	public boolean isValid(Object input) {
		return input instanceof ItemStack && Utils.areItemStacksEqual((ItemStack) input, target);
	}
}
