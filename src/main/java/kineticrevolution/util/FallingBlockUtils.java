package kineticrevolution.util;

import kineticrevolution.loaders.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;

/**
 * Created by AEnterprise
 */
public class FallingBlockUtils {

	public static void onArrival(EntityFallingBlock entity) {
		Block fallingBlock = entity.func_145805_f();
		if (fallingBlock != null && (fallingBlock instanceof BlockAnvil || fallingBlock.getMaterial() == Material.anvil)) {
			Block block = entity.worldObj.getBlock((int) Math.floor(entity.posX), (int) Math.floor(entity.posY - 1), (int) Math.floor(entity.posZ));
			Block block1 = entity.worldObj.getBlock((int) Math.floor(entity.posX), (int) Math.floor(entity.posY), (int) Math.floor(entity.posZ));
			if (block1 == BlockLoader.duster && entity.worldObj.getBlockMetadata((int) Math.floor(entity.posX), (int) Math.floor(entity.posY), (int) Math.floor(entity.posZ)) == 3) {
				block1.onFallenUpon(entity.worldObj, (int) Math.floor(entity.posX), (int) Math.floor(entity.posY), (int) Math.floor(entity.posZ), entity, (float) Math.pow(2, entity.ticksExisted / 10));
			} else if (block == BlockLoader.duster) {
				block.onFallenUpon(entity.worldObj, (int) Math.floor(entity.posX), (int) Math.floor(entity.posY - 1), (int) Math.floor(entity.posZ), entity, (float) Math.pow(2, entity.ticksExisted / 10));
			}
		}
	}
}
