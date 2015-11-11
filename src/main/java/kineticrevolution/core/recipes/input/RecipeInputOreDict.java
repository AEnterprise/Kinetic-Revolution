package kineticrevolution.core.recipes.input;

import kineticrevolution.util.OreDictHelper;
import kineticrevolution.util.Utils;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class RecipeInputOreDict implements IRecipeInput {
	private final String target;

	public RecipeInputOreDict(String target) {
		this.target = target;
	}

	@Override
	public boolean isValid(Object input) {
		if (!(input instanceof ItemStack))
			return false;
		for (ItemStack stack : OreDictHelper.lookup(target)) {
			if (Utils.areItemStacksEqual(stack, (ItemStack) input))
				return true;
		}
		return false;
	}
}
