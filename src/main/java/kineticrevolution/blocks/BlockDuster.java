package kineticrevolution.blocks;

import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IDusterRecipe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockDuster extends BlockBase {
	private AxisAlignedBB[] boxes = {
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.8, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.6, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.4, 1),
	};

	public BlockDuster() {
		super("duster");
	}


	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 0 || meta > 3) {
			meta = 0;
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
		return boxes[meta].copy().offset(x, y, z);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) {
		if (distance < 0.8)
			return;
		IDusterRecipe recipe = DusterRecipeManager.getRecipe(world, x, y - 1, z);
		if (recipe == null)
			return;
		int meta = world.getBlockMetadata(x, y, z) + 1;
		if (meta == 4) {
			world.setBlockToAir(x, y - 1, z);
			world.setBlock(x, y - 1, z, this, 0, 2);
			world.setBlockToAir(x, y, z);
		}
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(this, 1, 0);
	}
}
