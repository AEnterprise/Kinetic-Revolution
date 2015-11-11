package kineticrevolution.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import kineticrevolution.client.gui.GuiAssembler;
import kineticrevolution.inventories.containers.ContainerAssembler;
import kineticrevolution.lib.Names;
import kineticrevolution.tileEntities.TileAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {
			case Names.GUIIDs.ASSEMBLER:
				if (te instanceof TileAssembler)
					return new ContainerAssembler(player.inventory, ((TileAssembler) te).getInventory());
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {
			case Names.GUIIDs.ASSEMBLER:
				if (te instanceof TileAssembler)
					return new GuiAssembler(new ContainerAssembler(player.inventory, ((TileAssembler) te).getInventory()));
				break;
		}
		return null;
	}
}
