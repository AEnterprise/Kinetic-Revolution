package kineticrevolution.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.core.CTabs;
import kineticrevolution.inventories.IInventoryTE;
import kineticrevolution.lib.Reference;
import kineticrevolution.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public abstract class BlockBase extends BlockContainer {

	public BlockBase(String name, String registryName, String texture, CreativeTabs tab, Class<? extends ItemBlock> itemBlock, Material material) {
		super(material);
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + texture);
		setCreativeTab(tab);
		if (itemBlock == null) {
			GameRegistry.registerBlock(this, registryName);
		} else {
			GameRegistry.registerBlock(this, itemBlock, registryName);
		}
		setHardness(5);
		setResistance(5);
	}

	public BlockBase(String name, String registryName, String texture, CreativeTabs tab) {
		this(name, registryName, texture, tab, null, Material.iron);
	}

	public BlockBase(String name, String texture) {
		this(name, name, texture, CTabs.MAIN_TAB);
	}

	public BlockBase(String name) {
		this(name, name);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof IInventoryTE)
			Utils.dropItemstacks(world, x, y, z, ((IInventoryTE) entity).getInventory().getContent());
		super.breakBlock(world, x, y, z, block, meta);
	}
}
