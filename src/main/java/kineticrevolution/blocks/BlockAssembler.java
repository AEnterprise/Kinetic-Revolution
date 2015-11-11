package kineticrevolution.blocks;

import kineticrevolution.KineticRevolution;
import kineticrevolution.lib.Names;
import kineticrevolution.tileEntities.TileAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockAssembler extends BlockBase {

	public BlockAssembler() {
		super("assembler");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		player.openGui(KineticRevolution.instance, Names.GUIIDs.ASSEMBLER, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAssembler();
	}
}
