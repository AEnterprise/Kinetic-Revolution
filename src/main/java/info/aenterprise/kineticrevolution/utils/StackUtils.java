package info.aenterprise.kineticrevolution.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class StackUtils {
	public static NBTTagCompound getTagCompound(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		return tag;
	}

	public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2) {

		return stack1 != null && stack2 != null && stack1.isItemEqual(stack2) && ItemStack.areItemStackTagsEqual(stack1, stack2);
	}
}
