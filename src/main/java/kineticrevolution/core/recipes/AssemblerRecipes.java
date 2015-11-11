package kineticrevolution.core.recipes;

import kineticrevolution.items.ItemResource;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.loaders.ItemLoader;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class AssemblerRecipes extends RecipeHandler<ItemStack> {
	public static final AssemblerRecipes INSTANCE = new AssemblerRecipes();

	private AssemblerRecipes() {
		super(10);
	}

	public void addRecipes() {
		addRecipe(new ItemStack(BlockLoader.duster), ItemLoader.blueprint,
				"springTin", "springTin", "springTin",
				ItemResource.Resource.SPRING_SILVER.getStack(), ItemResource.Resource.GRINDING_WHEEL.getStack(), ItemResource.Resource.SPRING_SILVER.getStack(),
				ItemResource.Resource.SPRING_SILVER.getStack(), null, ItemResource.Resource.SPRING_SILVER.getStack());
	}


}
