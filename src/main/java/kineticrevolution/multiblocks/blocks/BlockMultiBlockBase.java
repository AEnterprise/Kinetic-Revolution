package kineticrevolution.multiblocks.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public abstract class BlockMultiBlockBase extends BlockContainer {
	protected final MultiBlockPattern pattern;

	protected BlockMultiBlockBase(MultiBlockPattern pattern, String name, String texture) {
		super(Material.iron);
		this.pattern = pattern;
		setBlockName(name);
		setBlockTextureName(texture);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity entity = world.getTileEntity(x, y, z);
		return entity != null && entity instanceof IMultiBlock && ((IMultiBlock) entity).onBlockClicked(player);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entiy, ItemStack stack) {
		if (pattern.isValid(world, x, y, z)) {
			pattern.formMultiblock(world, x, y, z);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (pattern.isValid(world, x, y, z)) {
			pattern.formMultiblock(world, x, y, z);
		}
	}
}
