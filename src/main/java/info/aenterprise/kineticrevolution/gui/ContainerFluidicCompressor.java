package info.aenterprise.kineticrevolution.gui;

import info.aenterprise.kineticrevolution.gui.slots.SlotOutput;
import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class ContainerFluidicCompressor extends ContainerBase<TileEntityFluidicCompressor> {

	public ContainerFluidicCompressor(EntityPlayer player, TileEntityFluidicCompressor tile) {
		super(player, tile);

		addSlotToContainer(new Slot(tile, 0, 89, 31) {

			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack != null && stack.getItem() != null && (stack.getItem() instanceof IFluidContainerItem || FluidContainerRegistry.isContainer(stack));
			}
		});
		addSlotToContainer(new SlotOutput(tile, 1, 126, 35));
		addPlayerInventory(8, 86);
	}
}
