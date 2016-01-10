package info.aenterprise.kineticrevolution.gui;

import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import info.aenterprise.kineticrevolution.utils.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID) {
			case GuiIDs.FLUIDIC_COMPRESSOR: if (entity instanceof TileEntityFluidicCompressor) return new ContainerFluidicCompressor(player, (TileEntityFluidicCompressor) entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID) {
			case GuiIDs.FLUIDIC_COMPRESSOR: if (entity instanceof TileEntityFluidicCompressor) return new GuiFluidicCompressor(player, (TileEntityFluidicCompressor) entity);
		}
		return null;
	}
}
