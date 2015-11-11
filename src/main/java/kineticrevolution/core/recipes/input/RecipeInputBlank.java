package kineticrevolution.core.recipes.input;

/**
 * Created by AEnterprise
 */
public class RecipeInputBlank implements IRecipeInput {

	@Override
	public boolean isValid(Object input) {
		//no input
		return input == null;
	}
}
