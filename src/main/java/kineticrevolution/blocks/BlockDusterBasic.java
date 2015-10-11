package kineticrevolution.blocks;

import kineticrevolution.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by AEnterprise
 */
public class BlockDusterBasic extends BlockDuster {
	public BlockDusterBasic() {
		super("basic");
	}

	@Override
	public double getChanceModifier() {
		return 0;
	}

	@Override
	public void handleOutputs(World world, int x, int y, int z, List<ItemStack> outputs) {
		Utils.dropItemstacks(world, x + .5, y + .5, z + .5, outputs);
	}
}
