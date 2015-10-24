package kineticrevolution.duster;

import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.util.Location;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class ItemBlockDuster extends ItemBlock {


	public ItemBlockDuster(Block block) {
		super(block);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean vanilla = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		for (int i = 0; i < 16; i++) {
			Location location = TileDuster.LOCATIONS[i].copy().offset(x, y, z);
			world.setBlock(location.x, location.y, location.z, BlockLoader.dusterFake, i, 3);
		}
		return vanilla;
	}
}
