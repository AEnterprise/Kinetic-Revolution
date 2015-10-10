package kineticrevolution.compat.nei;

import java.util.List;

import net.minecraft.item.ItemStack;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;


public class PositionedStackAdv extends PositionedStack {

	public PositionedStackAdv(Object object, int x, int y, boolean genPerms) {
		super(object, x, y, genPerms);
	}

	public PositionedStackAdv(Object object, int x, int y) {
		super(object, x, y);
	}

	public void handleTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip) {
		//NO-OP
	}


}
