package kineticrevolution.util;

import java.util.List;

import com.google.common.base.Strings;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class Utils {

	public static String localize(String key) {
		return ("" + StatCollector.translateToLocal(key)).trim();
	}

	public static String localizeFormatted(String key, Object... objects) {
		return ("" + StatCollector.translateToLocalFormatted(key, objects)).trim();
	}

	public static String localizeAllFormatted(String key, String... strings) {
		Object[] objects = new Object[strings != null ? strings.length : 0];
		if (strings != null) {
			for (int i = 0; i < objects.length; i++) {
				String string = strings[i];
				if (string != null)
					objects[i] = localize(string);
			}
		}
		return localizeFormatted(key, objects);
	}

	public static String decapitalizeFirstChar(String string) {
		return !Strings.isNullOrEmpty(string) ? Character.toLowerCase(string.charAt(0)) + string.substring(1) : null;
	}


	public static void dropItemstack(World world, double x, double y, double z, ItemStack stack) {
		if (!world.isRemote && stack != null && stack.stackSize > 0 && stack.getItem() != null) {
			float rx = world.rand.nextFloat() * 0.8F + 0.1F;
			float ry = world.rand.nextFloat() * 0.8F + 0.1F;
			float rz = world.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, stack.copy());
			float factor = 0.05F;
			entityItem.motionX = (world.rand.nextGaussian() * factor);
			entityItem.motionY = (world.rand.nextGaussian() * factor + 0.2000000029802322D);
			entityItem.motionZ = (world.rand.nextGaussian() * factor);
			world.spawnEntityInWorld(entityItem);
		}
	}

	public static void dropItemstacks(World world, double x, double y, double z, List<ItemStack> stacks) {
		if (stacks != null && !stacks.isEmpty()) {
			for (ItemStack stack : stacks)
				dropItemstack(world, x, y, z, stack);
		}

	}
}
