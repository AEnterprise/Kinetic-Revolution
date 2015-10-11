package kineticrevolution.blocks;

import com.google.common.collect.Lists;
import kineticrevolution.lib.Names;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockDuster extends BlockBase {
	private AxisAlignedBB[] boxes = {
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.8, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.6, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.4, 1),
	};

	public BlockDuster() {
		super(Names.Blocks.DUSTER);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 0 || meta > 3)
			meta = 0;
		AxisAlignedBB aabb = boxes[meta];
		setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 0 || meta > 3)
			meta = 0;
		return boxes[meta].copy().offset(x, y, z);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		AxisAlignedBB aabb = boxes[0];
		setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) {
		if (!world.isRemote) {
			if (distance < 0.8)
				return;
			IDusterRecipe recipe = DusterRecipeManager.getRecipe(world, x, y - 1, z);
			if (recipe == null)
				return;
			int meta = world.getBlockMetadata(x, y, z) + 1;
			if (meta >= 4) {
				//TODO: Maybe make the duster fall like an anvil?
				world.setBlockToAir(x, y - 1, z);
				world.setBlock(x, y - 1, z, this, 0, 2);
				world.setBlockToAir(x, y, z);
				handleOutputs(world, x, y, z, getOutputs(recipe, world.rand));
			} else {
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			}
		}
	}

	private List<ItemStack> getOutputs(IDusterRecipe recipe, Random random) {
		List<ItemStack> outputs = Lists.newArrayList();
		if (recipe != null && random != null) {
			for (IChancedOutput cOutput : recipe.getOutputs()) {
				if (cOutput != null) {
					ItemStack output = cOutput.getOutput();
					if (output != null && output.getItem() != null && output.stackSize > 0) {
						double chance = cOutput.getChance() + getChanceModifier();
						while (chance >= 1) {
							outputs.add(output.copy());
							chance--;
						}
						if (chance > 0 && random.nextDouble() < chance) {
							outputs.add(output.copy());
						}
					}
				}
			}
		}
		return outputs;
	}

	public double getChanceModifier() {
		return 0;
	}

	public void handleOutputs(World world, int x, int y, int z, List<ItemStack> outputs) {
		Utils.dropItemstacks(world, x + .5, y + .5, z + .5, outputs);
	}
}
