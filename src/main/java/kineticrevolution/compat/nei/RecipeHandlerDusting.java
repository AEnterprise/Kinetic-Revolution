package kineticrevolution.compat.nei;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import kineticrevolution.lib.Names;
import kineticrevolution.lib.Reference;
import kineticrevolution.lib.Strings;
import kineticrevolution.lib.Textures;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.util.Utils;

public class RecipeHandlerDusting extends TemplateRecipeHandler {

	public String getRecipeID() {
		return Reference.MOD_ID + ":" + Names.Recipes.DUSTING;
	}

	@Override
	public String getGuiTexture() {
		return Reference.MOD_ID + ":" + Textures.GUIs.NEI.DUSTING;
	}

	@Override
	public String getRecipeName() {
		return Utils.localize(Names.GUIs.NEI.DUSTING);
	}

	public Class<? extends GuiContainer> getGuiClass() {
		//TODO: Gui class
		return null;
	}

	@Override
	public void loadTransferRects() {
		//TODO: Transfer rect
		transferRects.add(new RecipeTransferRect(new Rectangle(71, 24, 24, 17), getRecipeID()));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(getRecipeID())) {
			for (IDusterRecipe recipe : DusterRecipeManager.getRecipeList()) {
				arecipes.add(new CachedDustingRecipe(recipe));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (IDusterRecipe recipe : DusterRecipeManager.getRecipeList()) {
			for (IChancedOutput output : recipe.getOutputs()) {
				if (NEIServerUtils.areStacksSameTypeCrafting(output.getOutput(), result)) {
					arecipes.add(new CachedDustingRecipe(recipe));
				}
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingred) {
		for (IDusterRecipe recipe : DusterRecipeManager.getRecipeList()) {
			for (ItemStack eInput : recipe.getExampleInputs()) {
				if (NEIServerUtils.areStacksSameTypeCrafting(eInput, ingred)) {
					CachedDustingRecipe cRecipe = new CachedDustingRecipe(recipe);
					cRecipe.setIngredientPermutation(Collections.singletonList(cRecipe.input), ingred);
					arecipes.add(cRecipe);
				}
			}
		}
	}

	@Override
	public List<String> handleItemTooltip(GuiRecipe guiRecipe, ItemStack stack, List<String> currenttip, int recipe) {
		super.handleItemTooltip(guiRecipe, stack, currenttip, recipe);
		CachedRecipe cachedRecipe = arecipes.get(recipe);
		if (cachedRecipe instanceof CachedDustingRecipe) {
			CachedDustingRecipe crecipe = (CachedDustingRecipe) cachedRecipe;
			if (GuiContainerManager.shouldShowTooltip(guiRecipe)) {
				Point mouse = GuiDraw.getMousePosition();
				Point offset = guiRecipe.getRecipePosition(recipe);
				Point relMouse = new Point(mouse.x - ((guiRecipe.width - 176) / 2) - offset.x, mouse.y - ((guiRecipe.height - 166) / 2) - offset.y);

				if (crecipe.getOtherStacks() != null) {
					for (PositionedStack posStack : crecipe.getOtherStacks()) {
						if (posStack instanceof PositionedStackAdv) {
							PositionedStackAdv posStackAdv = (PositionedStackAdv) posStack;
							if (new Rectangle(posStack.relx, posStackAdv.rely, 16, 16).contains(relMouse)) {
								posStackAdv.handleTooltip(guiRecipe, stack, currenttip);
							}
						}
					}
				}
			}
		}
		return currenttip;
	}

	public class CachedDustingRecipe extends CachedRecipe {

		final PositionedStack input;
		final List<PositionedStack> outputs = Lists.newArrayList();

		public CachedDustingRecipe(IDusterRecipe recipe) {
			input = new PositionedStack(recipe.getExampleInputs(), 28, 24);
			input.setMaxSize(1);
			for (int i = 0; i < recipe.getOutputs().size(); i++) {
				final IChancedOutput output = recipe.getOutputs().get(i);
				outputs.add(new PositionedStackAdv(output.getOutput(), 122 + 18 * i, 24) {
					@Override
					public void handleTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip) {
						currenttip.add(Utils.localizeFormatted(Strings.GUIs.NEI.DUSTING_CHANCE, (int) (output.getChance() * 100 + .5)));
					}
				});
			}
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, Collections.singletonList(input));
		}

		@Override
		public PositionedStack getResult() {
			return null;
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			return outputs;
		}
	}
}
