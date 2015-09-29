package kineticrevolution.compat.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.TemplateRecipeHandler;
import kineticrevolution.lib.Reference;

public class NEIConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		registerRecipeHandler(new RecipeHandlerDusting());
	}

	public void registerRecipeHandler(TemplateRecipeHandler handler) {
		API.registerRecipeHandler(handler);
		API.registerUsageHandler(handler);
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.MOD_VERSION;
	}

}
